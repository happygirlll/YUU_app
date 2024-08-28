package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapters.ChatAdapter;
import com.example.myapplication.helpers.SendMessageInBg;
import com.example.myapplication.interfaces.BotReply;
import com.example.myapplication.models.Message;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.common.collect.Lists;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatBotActivity extends AppCompatActivity implements BotReply {

    RecyclerView chatView;
    ChatAdapter chatAdapter;
    List<Message> messageList = new ArrayList<>();
    EditText editMessage;
    ImageButton btnSend;
    Button feedback;

    //dialogFlow
    private SessionsClient sessionsClient;  // 세션 클라이언트
    private SessionName sessionName;    // 세션 이름
    private String uuid = UUID.randomUUID().toString(); //식별자
    private String TAG = "ChatBotActivity";    // Tag 명

    private String command = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        setTitle("YU 봇");
        chatView = findViewById(R.id.chatView);
        editMessage = findViewById(R.id.editMessage);
        btnSend = findViewById(R.id.btnSend);
        feedback = findViewById(R.id.feedback);

        feedback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatBotActivity.this, MainActivity_chatbot.class);
                startActivity(intent);
            }
        });

        //퍼미션 체크( 인터넷, 오디오 )
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.INTERNET"}, 1);
        }

        chatAdapter = new ChatAdapter(messageList, this);
        chatView.setAdapter(chatAdapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editMessage.getText().toString();
                if (!message.isEmpty()) {
                    messageList.add(new Message(new SpannableString(message), false));
                    editMessage.setText("");
                    sendMessageToBot(message);
                    Objects.requireNonNull(chatView.getAdapter()).notifyDataSetChanged();
                    Objects.requireNonNull(chatView.getLayoutManager()).scrollToPosition(messageList.size() - 1);
                } else {
                    Toast.makeText(ChatBotActivity.this, "메시지를 입력하세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // dialogflowAgent key 정보 setup
        setUpBot();
    }

    // credential(GoogleService 자격 증명서) 파일을 통해 session 설정
    private void setUpBot() {
        try {
            InputStream stream = this.getResources().openRawResource(R.raw.credential);
            GoogleCredentials credentials = GoogleCredentials.fromStream(stream)
                    .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
            String projectId = ((ServiceAccountCredentials) credentials).getProjectId();

            SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
            SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(
                    FixedCredentialsProvider.create(credentials)).build();
            sessionsClient = SessionsClient.create(sessionsSettings);
            sessionName = SessionName.of(projectId, uuid);

            Log.d(TAG, "projectId : " + projectId);
        } catch (Exception e) {
            Log.d(TAG, "setUpBot: " + e.getMessage());
        }
    }

    // dialogflow로 message를 보내는 메서드
    private void sendMessageToBot(String message) {
        QueryInput input = QueryInput.newBuilder()
                .setText(TextInput.newBuilder().setText(message).setLanguageCode("ko-Korean")).build();
        new SendMessageInBg(this, sessionName, sessionsClient, input).execute();
    }

    @Override
    public void callback(DetectIntentResponse returnResponse) {
        if (returnResponse != null) {
            String botReply = returnResponse.getQueryResult().getFulfillmentText();
            if (!botReply.isEmpty()) {
                // Extracting the hyperlinks and the associated words
                Pattern pattern = Pattern.compile("\\[(.*?)\\]\\((.*?)\\)");
                Matcher matcher = pattern.matcher(botReply);

                // create a new spannable string without urls
                String replyWithoutUrls = botReply.replaceAll("\\(.*?\\)", "");
                SpannableString spannableString = new SpannableString(replyWithoutUrls);

                while (matcher.find()) {
                    String word = matcher.group(1);
                    String url = matcher.group(2);

                    int start = replyWithoutUrls.indexOf(word);
                    if (start != -1) {
                        int end = start + word.length();

                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(View textView) {
                                // Open the URL when the word is clicked
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(intent);
                            }
                        };

                        spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else {
                        Log.d(TAG, "Word not found in the reply: " + word);
                    }
                }
                // Add the message with the clickable words to the message list
                messageList.add(new Message(spannableString, true));

                chatAdapter.notifyDataSetChanged();
                Objects.requireNonNull(chatView.getLayoutManager()).scrollToPosition(messageList.size() - 1);
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Failed to connect!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        int id = item.getItemId();

        if (id == R.id.back_btn) { //map
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        return true;
    }
}
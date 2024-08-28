package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import android.text.method.ScrollingMovementMethod;
public class DetailActivity extends AppCompatActivity {

    TextView detail_id_text, detail_name_text, detail_info_text,detail_call_text;
    ImageView detail_image;
    LinearLayout detail_major_layout;
    String id, name, major, imageUrl, links, info, call;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maplist_detail);

        detail_id_text = findViewById(R.id.detail_id_text);
        detail_name_text = findViewById(R.id.detail_name_text);
        detail_major_layout = findViewById(R.id.detail_major_layout);
        detail_call_text = findViewById(R.id.detail_call_text);
        detail_info_text = findViewById(R.id.detail_info_text);
        detail_image = findViewById(R.id.AppImage);
        detail_image.setClipToOutline(true);


        // Get the data from MainActivity
        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        name = intent.getExtras().getString("name");
        major = intent.getExtras().getString("major");
        imageUrl = intent.getExtras().getString("image");
        links = intent.getExtras().getString("url");
        info = intent.getExtras().getString("info");
        call = intent.getExtras().getString("call");

        detail_id_text.setText(id);
        detail_name_text.setText(name);
        detail_info_text.setText(info);

        if (detail_info_text != null && !detail_info_text.getText().toString().trim().equals("")) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    convertDpToPx(375), // width
                    LinearLayout.LayoutParams.WRAP_CONTENT // height
            );
            detail_info_text.setLayoutParams(params);
        }

        boolean isCallTextViewAdded = false;

        if(major != null && links != null && call != null) {
            String[] majors = major.split(",");
            String[] linkArray = links.split(",");
            String[] phoneArray = call.split(",");

            for (int i = 0; i < majors.length; i++) {
                String majorName = majors[i].trim();
                String majorLink = (i < linkArray.length) ? linkArray[i].trim() : null;
                String majorPhone = (i < phoneArray.length) ? phoneArray[i].trim() : null;

                if (majorLink != null && !majorLink.isEmpty()) {
                    // Create and add major button
                    Button majorButton = new Button(this);
                    majorButton.setBackgroundResource(R.drawable.rounded_main);
                    majorButton.setTextSize(13);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    layoutParams.setMargins(10, 0, 0, 10);  // Setting right margin of 10dp
                    majorButton.setLayoutParams(layoutParams);
                    majorButton.setText(majorName);
                    majorButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(majorLink));
                            startActivity(browserIntent);
                        }
                    });
                    detail_major_layout.addView(majorButton);

                    if (majorPhone != null && !majorPhone.isEmpty()) {
                        TextView phoneTextView = new TextView(this);
                        phoneTextView.setText(" CALL : "+majorPhone);
                        phoneTextView.setTextSize(11);

                        LinearLayout.LayoutParams phoneTextParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        int bottomMargin = convertDpToPx(10); // adjust this value as per your requirement
                        phoneTextParams.setMargins(0, 0, 0, bottomMargin);
                        phoneTextView.setLayoutParams(phoneTextParams);

                        detail_major_layout.addView(phoneTextView);
                        isCallTextViewAdded = true;
                    }
                    // Create and add space view
                    View space = new View(this);
                    LinearLayout.LayoutParams spaceParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            20  // Set the height of the space view
                    );
                    space.setLayoutParams(spaceParams);
                    detail_major_layout.addView(space);  // add the space view to the layout
                }
            }
        }
        if (call != null && !call.trim().equals("") && !isCallTextViewAdded) {
            if (detail_call_text != null) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        convertDpToPx(375), // width
                        LinearLayout.LayoutParams.WRAP_CONTENT // height
                );
                detail_call_text.setLayoutParams(params);
            }
            detail_call_text.setText(" CALL\n"+call);
            detail_call_text.setVisibility(View.VISIBLE); // Make it visible if call is not null
        } else {
            detail_call_text.setVisibility(View.GONE); // Hide it if call is null
        }
        Picasso.with(this).load(imageUrl).fit().centerInside().into(detail_image);
    }

    public static int convertDpToPx(int dp){
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
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
            Intent intent = new Intent(this, MapListActivity.class);
            startActivity(intent);
        }
        return true;
    }
}


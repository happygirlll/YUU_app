package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity_chatbot extends AppCompatActivity {
    ChatDB helper2;
    SQLiteDatabase db2;
    MyAdapter adapter;
    Cursor cursor;
    ListView list;
    String userEmail;
    public static final int REQUEST_UPDATE = 1;  // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chatbot);
        getSupportActionBar().setTitle(" ");

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        helper2 = new ChatDB(this);
        db2 = helper2.getReadableDatabase();

        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        userEmail = sharedPreferences.getString("email", "");

        String sql = "select * from chatbot where user_email = ?";
        Log.d("MainActivity_chatbot", "SQL: " + sql);
        Log.d("MainActivity_chatbot", "User email: " + userEmail);
        cursor = db2.rawQuery(sql,new String[] {userEmail});

        cursor = db2.rawQuery("select * from chatbot", null);
        list = findViewById(R.id.list);
        adapter = new MyAdapter(this, cursor);
        list.setAdapter(adapter);

        Log.d("MainActivity_chatbot", "Cursor count: " + cursor.getCount());

        FloatingActionButton btnwrite = findViewById(R.id.btnwrite);
        btnwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity_chatbot.this, ChatBotInsertActivity.class);
                startActivity(intent);
            }
        });

    }


    class MyAdapter extends CursorAdapter {
        public MyAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return getLayoutInflater().inflate(R.layout.item2, parent, false);
        }

        @Override
        public void bindView(View view, Context context, final Cursor cursor) {
            TextView txtcontent = view.findViewById(R.id.txtcontent);
            txtcontent.setText(cursor.getString(1));
            TextView txtUserEmail = view.findViewById(R.id.txtUserEmail);
            TextView btndel = view.findViewById(R.id.btndel);
            final String user_email = cursor.getString(2);
            if (user_email != null && !user_email.isEmpty()) {
                String visiblePart = user_email.substring(0, Math.min(user_email.length(), 3));
                String hiddenPart = new String(new char[Math.max(0, user_email.length() - 3)]).replace("\0", "*");
                txtUserEmail.setText("작성자 : "+visiblePart +"******");
            }
            final int _id = cursor.getInt(0);
            Log.d("MyAdapter", "Content: " + cursor.getString(1));
            //삭제버튼
            btndel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (user_email != null && user_email.equals(userEmail)) {
                        AlertDialog.Builder box = new AlertDialog.Builder(MainActivity_chatbot.this);
                        box.setMessage("해당 글을 삭제하시겠습니까?");
                        box.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String sql = "delete from chatbot where _id=" + _id;
                                db2.execSQL(sql);

                                onRestart();
                            }
                        });
                        box.setNegativeButton("닫기", null);
                        box.show();
                    }
                    else {
                        Toast.makeText(MainActivity_chatbot.this, "해당 글을 작성한 사용자만 삭제할 수 있습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_UPDATE && resultCode == RESULT_OK) {
            cursor = db2.rawQuery("select * from chatbot", null);
            adapter.changeCursor(cursor);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cursor = db2.rawQuery("select * from chatbot", null);
        adapter.changeCursor(cursor);
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
            Intent intent = new Intent(this, ChatBotActivity.class);
            startActivity(intent);
        }

        return true;
    }

    @Override
    protected void onRestart() {

        String sql = "select * from chatbot where user_email = ?";
        Log.d("MainActivity_chatbot", "SQL: " + sql);
        Log.d("MainActivity_chatbot", "User email: " + userEmail);
        cursor = db2.rawQuery(sql,new String[] {userEmail});

        cursor = db2.rawQuery("select * from chatbot", null);
        adapter.changeCursor(cursor);
        super.onRestart();
    }

}
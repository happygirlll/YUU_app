package com.example.myapplication;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ChatBotInsertActivity extends AppCompatActivity {
    ChatDB helper2;
    SQLiteDatabase db2;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot_insert);

        getSupportActionBar().setTitle("내용 입력");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper2 = new ChatDB(this);
        db2 = helper2.getWritableDatabase();
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        userEmail = sharedPreferences.getString("email", "");


        FloatingActionButton btnsave=findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtcontent = findViewById(R.id.edtcontent);
                String strcontent = edtcontent.getText().toString();

                String sql = "insert into chatbot(user_email, content) values(?,?)";
                db2.execSQL(sql, new String[] { userEmail, strcontent });

                Toast.makeText(ChatBotInsertActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChatBotInsertActivity.this, MainActivity_chatbot.class);
                startActivity(intent);
                finish();
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
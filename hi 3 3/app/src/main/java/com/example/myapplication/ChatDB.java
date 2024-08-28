package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class ChatDB extends SQLiteOpenHelper {
    public ChatDB(@Nullable Context context) {
        super(context, "chatbot.db", null, 2);
    }
    //테이블 생성
    @Override
    public void onCreate(SQLiteDatabase db2) {
        db2.execSQL("create table chatbot(_id integer primary key autoincrement,content text, user_email text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db2.execSQL("ALTER TABLE chatbot ADD COLUMN user_email TEXT");
        }
    }
}
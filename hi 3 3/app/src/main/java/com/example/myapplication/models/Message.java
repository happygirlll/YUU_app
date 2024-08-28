package com.example.myapplication.models;

import android.text.SpannableString;

public class Message {
    private SpannableString message;
    private boolean isReceived;

    public Message(SpannableString message, boolean isReceived) {
        this.message = message;
        this.isReceived = isReceived;
    }

    public SpannableString getMessage() {
        return message;
    }

    public boolean getIsReceived() {
        return isReceived;
    }
}
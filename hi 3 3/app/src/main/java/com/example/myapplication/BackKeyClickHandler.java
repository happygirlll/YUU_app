package com.example.myapplication;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Toast;

public class BackKeyClickHandler {
    private long backKeyClickTime = 0;
    private Activity activity;

    public BackKeyClickHandler(Activity activity)
    {
        this.activity = activity;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyClickTime + 2000) {
            backKeyClickTime = System.currentTimeMillis(); showToast(); return; }
        if (System.currentTimeMillis() <= backKeyClickTime + 2000) {
            SharedPreferences sharedPreferences = activity.getSharedPreferences("appData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("app_terminated", true);
            editor.apply();

//            activity.finish();
            activity.finishAffinity();
        }
    }


    public void showToast()
    {
        Toast.makeText(activity, "뒤로 가기를 한 번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
    }


}

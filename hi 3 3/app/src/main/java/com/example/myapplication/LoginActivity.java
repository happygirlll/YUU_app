package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private boolean saveLoginData;
    private TextView join;
    private Button login;
    private EditText email_login;
    private EditText pwd_login;
    private CheckBox login_chk;

    private SharedPreferences appData;
    private String id;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(" ");

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();

        join = (TextView) findViewById(R.id.main_join_btn);
        login = (Button) findViewById(R.id.main_login_btn);
        email_login = (EditText) findViewById(R.id.main_email);
        pwd_login = (EditText) findViewById(R.id.main_pwd);
        login_chk = (CheckBox) findViewById(R.id.login_chk);
        firebaseAuth = firebaseAuth.getInstance();//firebaseAuth의 인스턴스를 가져옴

        SharedPreferences sharedPreferences = getSharedPreferences("appData", MODE_PRIVATE);
        boolean wasAppTerminated = sharedPreferences.getBoolean("app_terminated", false);

        // 앱이 완전히 종료된 상태에서 시작된 경우, 자동 로그인 코드를 실행
        if (wasAppTerminated) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if ( user != null) {
                Intent login_intent = new Intent(this, MainActivity.class);
                startActivity(login_intent);
                Toast.makeText(this, "자동 로그인" + user.getEmail(), Toast.LENGTH_SHORT).show();
            }

            // 플래그를 재설정
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("app_terminated", false);
            editor.apply();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_login.getText().toString().trim();
                String pwd = pwd_login.getText().toString().trim();
                //String형 변수 email.pwd(edittext에서 받오는 값)으로 로그인하는것
                firebaseAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {//성공했을때 이메일 저장

                                    SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("email", email);
                                    editor.apply();

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }


                                else {//실패했을때
                                    Toast.makeText(LoginActivity.this, "로그인 오류가 났습니다.", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
                save();
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });



    }
    // 설정값을 저장하는 함수
    private void save() {
        // SharedPreferences 객체만으론 저장 불가능 Editor 사용
        SharedPreferences.Editor editor = appData.edit();

        // 에디터객체.put타입( 저장시킬 이름, 저장시킬 값 )
        // 저장시킬 이름이 이미 존재하면 덮어씌움
        editor.putBoolean("SAVE_LOGIN_DATA", login_chk.isChecked());
        editor.putString("ID", email_login.getText().toString().trim());

        // apply, commit 을 안하면 변경된 내용이 저장되지 않음
        editor.apply();
    }

    // 설정값을 불러오는 함수
    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
        id = appData.getString("ID", "");
    }
}
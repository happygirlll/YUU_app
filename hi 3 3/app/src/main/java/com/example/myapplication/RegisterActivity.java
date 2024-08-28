package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText email_join;
    private EditText pwd1, pwd2;
    private EditText number_join,name_join;
    private Button check_btn, sign_btn, cancel_btn;
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle(" ");

        email_join = (EditText) findViewById(R.id.sign_up_email);
        pwd1 = (EditText) findViewById(R.id.sign_up_pwd1);
        pwd2 = (EditText) findViewById(R.id.sign_up_pwd2);
        number_join=(EditText)findViewById(R.id.sign_up_number);
        name_join=(EditText)findViewById(R.id.sign_up_name);
        check_btn = (Button) findViewById(R.id.check_pwd);
        sign_btn = (Button) findViewById(R.id.sign_up_btn);
        cancel_btn = (Button) findViewById(R.id.cancel_btn);

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pwd1.getText().toString().equals(pwd2.getText().toString())){
                    check_btn.setText("일치합니다.");
                }
                else{
                    Toast.makeText(RegisterActivity.this, "비밀번호가 다릅니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();

        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = email_join.getText().toString().trim();
                final String pwd = pwd2.getText().toString().trim();
                //final String pwd_2 = pwd2.getText().toString().trim();
                final String number = number_join.getText().toString().trim();
                final String name = name_join.getText().toString().trim();
                //공백인 부분을 제거하고 보여주는 trim();



                firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(RegisterActivity.this, "다시 입력하세요.", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
            }
        });


        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this, "입력이 취소됐습니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

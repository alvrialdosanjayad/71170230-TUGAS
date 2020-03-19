package com.example.tugas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Database db;
    Button button;
    EditText loginEmail,loginPass;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences("masuk",MODE_PRIVATE);
        String cek = preferences.getString("ingat","");

        if(cek.equals("true")){
            Intent intent = new Intent(LoginActivity.this, PageUtamaActivity.class);
            startActivity(intent);
        }

        db = new Database(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.btn_Login);
        loginEmail = findViewById(R.id.txt_loginEmail);
        loginPass = findViewById(R.id.txt_loginPass);
        textView = findViewById(R.id.daftar);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString();
                String pass = loginPass.getText().toString();
                broadcaster();

                Boolean checkMail = db.checkLogin(email,pass);
                if(checkMail==true){
//                  Toast.makeText(getApplicationContext(),"Login Berhasil",Toast.LENGTH_SHORT).show();

                    SharedPreferences preferen = getSharedPreferences("masuk",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferen.edit();
                    editor.putString("ingat","true");
                    editor.apply();

                    Bundle extras = new Bundle();
                    extras.putString("KEY",email);
                    Intent intent = new Intent(LoginActivity.this, PageUtamaActivity.class);
                    intent.putExtras(extras);
                    startActivity(intent);
                    loginEmail.getText().clear();
                    loginPass.getText().clear();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Email/Password is Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void broadcaster(){
        Intent broadcasterIntent = new Intent("My_ACTION");
        broadcasterIntent.setComponent(new ComponentName(getPackageName(),"com.example.tugas.MyBroadcastReciver"));
        getApplicationContext().sendBroadcast(broadcasterIntent);
    }
}

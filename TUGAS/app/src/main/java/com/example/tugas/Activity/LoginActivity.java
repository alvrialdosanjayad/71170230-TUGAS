package com.example.tugas.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button button;
    private EditText loginEmail;
    private EditText loginPass;
    private TextView textView;
    private ProgressBar progressBar;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        SharedPreferences preferences = getSharedPreferences("masuk", MODE_PRIVATE);
//        String cek = preferences.getString("ingat", "");
//
//
//        if (cek.equals("true")) {
//            Intent intent = new Intent(LoginActivity.this, PageUtamaActivity.class);
//            startActivity(intent);
//            finish();
//        }
        mFirebaseAuth = FirebaseAuth.getInstance();
        if(mFirebaseAuth.getCurrentUser() != null){
//            Bundle setData = new Bundle();
            Intent sendData2 = new Intent(getApplicationContext(), PageUtamaActivity.class);
//            setData.putString("DataSaya",  mFirebaseAuth.getCurrentUser().getEmail());
//            sendData2.putExtras(setData);
            startActivity(sendData2);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.btn_Login);
        loginEmail = findViewById(R.id.txt_loginEmail);
        loginPass = findViewById(R.id.txt_loginPass);
        textView = findViewById(R.id.daftar);
        progressBar = findViewById(R.id.progressBar2);



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

                if (email.isEmpty()) {
                    loginEmail.setError("Silahkan masukkan Email");
                    return;
                }
                if (pass.isEmpty()) {
                    loginPass.setError("Silahkan masukkan Password");
                    return;
                }
                if (pass.length() < 6) {
                    loginPass.setError("Password harus lebih dari 6 karakter");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                mFirebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loginEmail.getText().clear();
                            loginPass.getText().clear();
                            startActivity(new Intent(getApplicationContext(), PageUtamaActivity.class));
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

//                SharedPreferences preferen = getSharedPreferences("masuk", MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferen.edit();
//                editor.putString("ingat", "true");
//                editor.apply();

            }
        });
    }
}

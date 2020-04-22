package com.example.tugas.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tugas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisActivity extends AppCompatActivity {
    private Button buttonRegis,buttonCancel;
    private ProgressBar progressBar;
    private EditText txtEmail,txtPass, txtnama;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore firebaseFirestoreDb;
    private String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        txtEmail = findViewById(R.id.txt_email);
        txtPass = findViewById(R.id.txt_pass);
        txtnama = findViewById(R.id.txt_nama);
        buttonRegis = findViewById(R.id.btn_Regis);
        buttonCancel = findViewById(R.id.btn_Batal);
        progressBar = findViewById(R.id.progressBar);

        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestoreDb = FirebaseFirestore.getInstance();

//        if(mFirebaseAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(),PageUtamaActivity.class));
//            finish();
//        }
        buttonRegis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String pass = txtPass.getText().toString();
                final String nama = txtnama.getText().toString();
                if (email.isEmpty()) {
                    txtEmail.setError("Silahkan masukkan Email");
                    return;
                }
                if (pass.isEmpty()) {
                    txtPass.setError("Silahkan masukkan Password");
                    return;
                }
                if (pass.length() < 6) {
                    txtPass.setError("Password harus lebih dari 6 karakter");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                mFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserID = mFirebaseAuth.getCurrentUser().getUid();
                            Map<String,Object> user = new HashMap<>();
                            user.put("nama",nama);
                            firebaseFirestoreDb.collection("Users").document(UserID).set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("TAG", "Berhasil : "+UserID);
                                            FirebaseAuth.getInstance().signOut();
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("TAG", e.toString());
                                        }
                                    });
                        } else {
                            Toast.makeText(RegisActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

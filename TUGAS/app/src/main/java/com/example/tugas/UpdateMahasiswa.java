package com.example.tugas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateMahasiswa extends AppCompatActivity {
    private String data1;
    private String data2;
    private String data3;
    private String id;
    private EditText noMhs;
    private EditText namaMhs;
    private EditText phone;
    private Button upload;
    private FirebaseFirestore firebaseFirestoreDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mahasiswa);

        firebaseFirestoreDb = FirebaseFirestore.getInstance();

        noMhs = findViewById(R.id.editTextUploadNoMhs);
        namaMhs = findViewById(R.id.editTextUploadNamaMhs);
        phone = findViewById(R.id.editTextUploadPhone);
        upload = findViewById(R.id.buttonUploadMhs);

        getData();
        setData();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!noMhs.getText().toString().isEmpty() && !namaMhs.getText().toString().isEmpty()) {
                    updateMahasiswa();
                } else {
                    Toast.makeText(getApplicationContext(), "No dan Nama Mhs tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getData() {
        if (getIntent().hasExtra("NoMhs") && getIntent().hasExtra("NamaMhs") && getIntent().hasExtra("Phone") && getIntent().hasExtra("KUNCI")) {
            data1 = getIntent().getStringExtra("NoMhs");
            data2 = getIntent().getStringExtra("NamaMhs");
            data3 = getIntent().getStringExtra("Phone");
            id = getIntent().getStringExtra("KUNCI");
        } else {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        noMhs.setText(data1);
        namaMhs.setText(data2);
        phone.setText(data3);
    }

    private void updateMahasiswa() {
        Mahasiswa mhs = new Mahasiswa(noMhs.getText().toString(), namaMhs.getText().toString(),phone.getText().toString());
        firebaseFirestoreDb.collection("DaftarMhs").document(id).set(mhs)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Mahasiswa berhasil diupdate", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "ERROR" + e.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });
    }
}

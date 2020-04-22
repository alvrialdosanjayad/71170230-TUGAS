package com.example.tugas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TambahMahasiswa extends AppCompatActivity {

    private EditText noMhs;
    private EditText namaMhs;
    private EditText phoneMhs;
    private Button tambah;

    private RecyclerView mtampil;
    private FirebaseFirestore firebaseFirestoreDb;
    private FirestoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mahasiswa);

        noMhs = findViewById(R.id.txt_noMahasiswa);
        namaMhs = findViewById(R.id.txt_namaMahasiswa);
        phoneMhs = findViewById(R.id.txt_phoneMahasiswa);
        tambah = findViewById(R.id.tambahMahasiswaButton);
        firebaseFirestoreDb = FirebaseFirestore.getInstance();

        mtampil = findViewById(R.id.recycleData1);

        FirestoreRecyclerOptions<Mahasiswa> options = new FirestoreRecyclerOptions.Builder<Mahasiswa>()
                .setLifecycleOwner(this)
                .setQuery(firebaseFirestoreDb.collection("DaftarMhs"), new SnapshotParser<Mahasiswa>() {
                    @NonNull
                    @Override
                    public Mahasiswa parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        Mahasiswa mahasiswa = snapshot.toObject(Mahasiswa.class);
                        String itemId = snapshot.getId();
                        mahasiswa.setItem_id(itemId);
                        return mahasiswa;
                    }
                })
                .build();

        adapter = new FirestoreAdapter(options,getApplicationContext());
        mtampil.setHasFixedSize(true);
        mtampil.setLayoutManager(new LinearLayoutManager(this));
        mtampil.setAdapter(adapter);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!noMhs.getText().toString().isEmpty() && !namaMhs.getText().toString().isEmpty()) {
                    tambahMahasiswa();
                } else {
                    Toast.makeText(getApplicationContext(), "No dan Nama Mhs tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void tambahMahasiswa() {
        Mahasiswa mhs = new Mahasiswa(noMhs.getText().toString(), namaMhs.getText().toString(),phoneMhs.getText().toString());
        firebaseFirestoreDb.collection("DaftarMhs").document().set(mhs)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(TambahMahasiswa.this, "Mahasiswa berhasil didaftarkan", Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "Mahasiswa berhasil didaftarkan");
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

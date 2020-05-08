package com.example.tugas.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.tugas.Activity.Lokasi;
import com.example.tugas.Activity.TambahMahasiswa;
import com.example.tugas.R;

public class Status extends Fragment {
    private Button tambah,lokasi;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntancesState){
        View view = inflater.inflate(R.layout.fragment_status,container,false);
        tambah = view.findViewById(R.id.tambahMahasiswaAwal);
        lokasi = view.findViewById(R.id.lokasi);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TambahMahasiswa.class);
                startActivity(intent);

            }
        });
        lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Lokasi.class);
                startActivity(intent);

            }
        });
        return view;
    }
}

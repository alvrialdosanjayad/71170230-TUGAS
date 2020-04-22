package com.example.tugas.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas.R;
import com.example.tugas.RecyclerviewHomeAdapter;

public class Home extends Fragment {
     private RecyclerView recyclerView;
     private String[] s1;
     private String[] s2;
     private int[] images = {R.drawable.a_quiet_place,R.drawable.annabelle,R.drawable.chucky,R.drawable.dilan,R.drawable.onward,R.drawable.jane_doe,R.drawable.pengabdi_setan,R.drawable.train_to_busan,R.drawable.venom_poster};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        s1 = getResources().getStringArray(R.array.nama_film);
        s2 = getResources().getStringArray(R.array.Deskripsi);
        recyclerView = view.findViewById(R.id.recycler);
        RecyclerviewHomeAdapter recyclerviewHomeAdapter = new RecyclerviewHomeAdapter(getContext(),s1,s2,images);
        recyclerView.setAdapter(recyclerviewHomeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}

package com.example.tugas.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas.R;
import com.example.tugas.RecyclerviewAdapter;

public class Home extends Fragment {
    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.a_quiet_place,R.drawable.annabelle,R.drawable.chucky,R.drawable.dilan,R.drawable.onward,R.drawable.jane_doe,R.drawable.pengabdi_setan,R.drawable.train_to_busan,R.drawable.venom_poster};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        s1 = getResources().getStringArray(R.array.nama_film);
        s2 = getResources().getStringArray(R.array.Deskripsi);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        RecyclerviewAdapter recyclerviewAdapter = new RecyclerviewAdapter(getContext(),s1,s2,images);
        recyclerView.setAdapter(recyclerviewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}

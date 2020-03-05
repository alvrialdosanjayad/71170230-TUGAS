package com.example.tugas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class StatusFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntancesState){
        View view = inflater.inflate(R.layout.layout_status,container,false);
        return view;
    }
}

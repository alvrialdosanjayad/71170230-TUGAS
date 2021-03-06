package com.example.tugas.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.tugas.Activity.LoginActivity;
import com.example.tugas.R;
import com.google.firebase.auth.FirebaseAuth;

public class Panggilan extends Fragment {
    private Button logout,mulai,berhenti;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View view = inflater.inflate(R.layout.fragment_setting,container,false);
        logout = view.findViewById(R.id.button);
        mulai = view.findViewById(R.id.mulai);
        berhenti = view.findViewById(R.id.berhenti);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SharedPreferences preferen = Objects.requireNonNull(getActivity()).getSharedPreferences("masuk", MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferen.edit();
//                editor.putString("ingat","false");
//                editor.apply();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }

}

package com.example.tugas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas.R;

public class DetailFilm extends AppCompatActivity {
    private String data1;
    private String data2;
    private int images;
    private ImageView mainImageView;
    private TextView judul;
    private TextView deskripsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        mainImageView = findViewById(R.id.gambarDetail);
        judul = findViewById(R.id.judulFilmDetail);
        deskripsi = findViewById(R.id.deskripsiFilmDetail);

        getData();
        setData();
    }

    private void getData(){
        if(getIntent().hasExtra("images") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2")){
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            images = getIntent().getIntExtra("images",1);
        }else{
            Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        judul.setText(data1);
        deskripsi.setText(data2);
        mainImageView.setImageResource(images);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            // Toast.makeText(getActivity(),"PORTRAIT",Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Potrait", Toast.LENGTH_SHORT).show();
            //add your code what you want to do when screen on PORTRAIT MODE
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            //Toast.makeText(getActivity(),"LANDSCAPE",Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Lanscape", Toast.LENGTH_SHORT).show();
            //add your code what you want to do when screen on LANDSCAPE MODE
        }
    }
}

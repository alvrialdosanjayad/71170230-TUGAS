package com.example.tugas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas.Activity.DetailFilm;

public class RecyclerviewHomeAdapter extends RecyclerView.Adapter<RecyclerviewHomeAdapter.MyViewHolder> {

    private String[] data1;
    private String[] data2;
    private int[] images;
    private Context context;

    public RecyclerviewHomeAdapter(Context context, String s[], String s2[], int img[]){
        this.context = context;
        this.data1 = s;
        this.data2 = s2;
        this.images = img;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.myText.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myImage.setImageResource(images[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailFilm.class);
                intent.putExtra("data1",data1[position]);
                intent.putExtra("data2",data2[position]);
                intent.putExtra("images",images[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText, myText2;
        ImageView myImage;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText = itemView.findViewById(R.id.judulFilm);
            myText2 = itemView.findViewById(R.id.deskripsiFilm);
            myImage = itemView.findViewById(R.id.gambarFilm);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}

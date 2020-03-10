package com.example.tugas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences("masuk",MODE_PRIVATE);
        String cek = preferences.getString("ingat","");

        if(cek.equals("true")){
            Intent intent = new Intent(MainActivity.this, PageHome.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_Login);
        editText1 = findViewById(R.id.txt_edit);
        editText2 = findViewById(R.id.txt_edit2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText1.getText().toString();
                String pass = editText2.getText().toString();
                SharedPreferences preferen = getSharedPreferences("masuk",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferen.edit();
                editor.putString("ingat","true");
                editor.apply();
                Toast.makeText(getApplicationContext(),"Email: " +email+ " Password :" + pass,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, PageHome.class);
                startActivity(intent);
            }
        });
    }
}

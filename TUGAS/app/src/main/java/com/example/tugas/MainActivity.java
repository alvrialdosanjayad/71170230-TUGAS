package com.example.tugas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                Toast.makeText(getApplicationContext(),"Email: " +email+ " Password :" + pass,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, halamanHome.class);
                startActivity(intent);
            }
        });
    }
}

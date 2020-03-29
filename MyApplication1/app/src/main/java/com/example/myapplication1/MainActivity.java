package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onClick(View v){
        EditText et = (EditText) findViewById(R.id.editText);
        SharedPreferences sp = getSharedPreferences("ip", MODE_PRIVATE);
        sp.edit().putString("ipNumber", et.getText().toString()).commit();
    }

}



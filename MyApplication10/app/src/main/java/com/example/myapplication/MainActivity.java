package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyAsyncTask myAsyncTask=new MyAsyncTask();
        myAsyncTask.execute();
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a= new Intent(MainActivity.this,image.class);
                startActivity(a);
            }
        });
        Button button1=findViewById(R.id.btn_2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b=new Intent(MainActivity.this,jindutiao.class);
                startActivity(b);
            }
        });
    }

}

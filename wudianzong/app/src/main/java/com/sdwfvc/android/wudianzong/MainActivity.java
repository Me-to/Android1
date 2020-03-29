package com.sdwfvc.android.wudianzong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonzhu,buttondeng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        buttondeng=findViewById(R.id.btn_denglu);
        buttonzhu=findViewById(R.id.btn_zhuce1);
        buttonzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,zhuce.class);
                startActivity(intent);
            }
        });
        buttondeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });
    }
}

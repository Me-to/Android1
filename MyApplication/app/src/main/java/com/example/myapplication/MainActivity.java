package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button btn_1,btn_2;
EditText et_1;
TextView tv_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);

        btn_2=findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
            }
        });
    }

    private void init() {

        final AlertDialog ad=new AlertDialog.Builder(MainActivity.this).create();
        View view=View.inflate(getApplicationContext(),R.layout.activity_main,null);
        btn_1=findViewById(R.id.btn_queding);
        et_1=findViewById(R.id.et_xinxi);
   //     tv_1=findViewById(R.id.tv_1);
        ad.setView(view);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=et_1.getText().toString().trim();
             //   tv_1.setText(a);
                ad.dismiss();
            }
        });
        ad.show();

    }
}

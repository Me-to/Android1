package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
Button btn_dain,btn_a;
EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_dain=findViewById(R.id.btn_dian);
        btn_dain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
tancahugn();
            }
        });
    }
    public void tancahugn(){
        final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        View view= View.inflate(getApplicationContext(),R.layout.a,null);
        editText=view.findViewById(R.id.et_xinxi);
        btn_a=view.findViewById(R.id.btn_a);
        alertDialog.setView(view);
        btn_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=editText.getText().toString().trim();
                Log.e("------------", ""+a );
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}

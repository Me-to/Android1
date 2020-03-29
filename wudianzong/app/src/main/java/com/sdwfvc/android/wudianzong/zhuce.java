package com.sdwfvc.android.wudianzong;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class zhuce extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText name,password;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        init();
    }

    private void init() {
        name=findViewById(R.id.et_zhucename);
        password=findViewById(R.id.et_zhucemiam);
        //
        button=findViewById(R.id.button);
        sharedPreferences=getSharedPreferences("A",MODE_PRIVATE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.length()>0&&password.length()>0){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("name",name.getText().toString().trim());
                    editor.putString("password",password.getText().toString().trim());
                    editor.apply();
                    Toast.makeText(zhuce.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(zhuce.this, "请输入账号和密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

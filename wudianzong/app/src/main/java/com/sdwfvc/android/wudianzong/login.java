package com.sdwfvc.android.wudianzong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    Button button;
    EditText et_name, et_password;
    boolean name = false, password = false;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
    }

    private void init() {
        button = findViewById(R.id.btn_login);
        et_name = findViewById(R.id.et_usename);
        et_password = findViewById(R.id.et_password);
 sharedPreferences=getSharedPreferences("A",MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_name.getText().toString().equals(sharedPreferences.getString("name","null"))) {
                    name = true;
                }
                if (et_password.getText().toString().equals(sharedPreferences.getString("password","null"))) {
                    password = true;
                }
                if (name == true && password == true) {
                    Intent intent = new Intent(login.this, presentation.class);
                    startActivity(intent);
                    et_password.setText("");
                    et_name.setText("");
                    name = false;
                    password = false;
                } else {
                    Toast.makeText(login.this, "账号或密码输入错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

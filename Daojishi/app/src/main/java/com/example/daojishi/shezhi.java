package com.example.daojishi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class shezhi extends AppCompatActivity {
    Button button;
    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi);
        init();
    }

    private void init() {
        button = findViewById(R.id.buttonshezhi);
        editText = findViewById(R.id.editTextshezhishijian);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer a = Integer.valueOf(editText.getText().toString());
                if (a < 100000 && a > 0) {
                    Intent intent = new Intent();
                    intent.putExtra("shijian", a);
                    setResult(RESULT_OK, intent);
                    finish();
                    Toast.makeText(shezhi.this, "时间设置成功", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(shezhi.this, "请输入的值为0到100000", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


}

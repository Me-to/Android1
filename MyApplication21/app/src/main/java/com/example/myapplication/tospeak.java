package com.example.myapplication;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.TooManyListenersException;

public class tospeak extends AppCompatActivity {

    TextToSpeech textToSpeech;
    Button button;
    EditText editText;
    boolean a = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaker);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        init();
    }
    private void init() {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.CHINA);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(tospeak.this, "数据丢失或者不支持", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a == true) {
                    //设置音调，值越大声音越尖（女生），值越小则变成男声，1.0是常规
                    textToSpeech.setPitch(0.5f);
                    //设置语速，默认1.0正常语速
                    textToSpeech.setSpeechRate(1.5f);
                    //朗读,括号里面三个参数的added in API level 4                   四个参数的added in API level 21
                    textToSpeech.speak(editText.getText().toString(), TextToSpeech.LANG_AVAILABLE, null);
                    button.setText("关闭朗读");
                    a = false;
                } else {
                    textToSpeech.stop();
                    button.setText("开始朗读");
                    a = true;
                }
            }
        });
    }


}

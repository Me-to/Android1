package com.example.tts1_menjin;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class c extends AppCompatActivity {
    TextToSpeech textToSpeech;
    TextView textView;
    String TAG="-----------";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c);
        init();
    }
    private void init() {
        textView  =findViewById(R.id.textView);
        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i==TextToSpeech.SUCCESS){
                    textToSpeech.setLanguage(Locale.CHINESE);
                    // textToSpeech.setPitch(0.5f);
                    //  textToSpeech.setSpeechRate(1.3f);
                    textToSpeech.speak("本系统适用于安装在财务室、仓库、工厂围墙、厂房大门口，车间内、生产线位等。入侵报警感测则是以财务室、仓库、围墙等区域为主，而且能够让入侵报警与监控。\n" +
                            "   此设备模拟的是一个现代化的仓库，当晚上人走后安防设备开启，检测到烟雾时或者检测到应该封闭的仓库有人偷偷进入时，进行预警，并手机上进行提醒。\n" +
                            "",TextToSpeech.LANG_AVAILABLE,null);
                }
                else {
                    Log.d(TAG, "onInit: -------------");
                }

            }
        });

    }
}

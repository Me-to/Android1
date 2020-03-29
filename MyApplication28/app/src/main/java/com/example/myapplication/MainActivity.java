package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                textToSpeech.setLanguage(Locale.CHINESE);
                textToSpeech.speak("环境监测可避免危险及提升生产质量，很多工厂的制作往往对环境要求苛刻 如石化产品的制作过程，     往往都必须要在高温、高压、高噪音、有机挥发物浓度高的环境下进行，且每小时生产的化学品动辄以百吨计，只要稍有控制不慎或无法及时操控，同时避免人员可能遭到的威胁，环境安全监测就显得更加重要。   这个系统通过检测这些数据进行分析，当传感器获取的值高于或者低于安全的界限时，可以打开相应设备，或者进行报警提醒注意。",TextToSpeech.LANG_AVAILABLE,null);
            }
        });
    }
}

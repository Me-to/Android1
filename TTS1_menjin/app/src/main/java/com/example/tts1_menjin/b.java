package com.example.tts1_menjin;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class b extends AppCompatActivity {
    TextToSpeech textToSpeech;
    TextView textView;
    String TAG="-----------";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b);
        init();
    }
    private void init() {
        textView  =findViewById(R.id.b);
        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i==TextToSpeech.SUCCESS){
                    textToSpeech.setLanguage(Locale.CHINESE);
                    textToSpeech.setSpeechRate(1.0f);

                    textToSpeech.speak("环境监测可避免危险及提升生产质量，很多工厂的制作往往对环境要求苛刻 如石化产品的制作过程，"+
                           " 往往都必须要在高温、高压、高噪音、有机挥发物浓度高的环境下进行，且每小时生产的化学品动辄以百吨计，只要稍有控制不慎或无法及时操控，同时避免人员可能遭到的威胁，环境安全监测就显得更加重要。   这个系统通过检测这些数据进行分析，当传感器获取的值高于或者低于安全的界限时，可以打开相应设备，或者进行报警提醒注意。"
                            ,TextToSpeech.LANG_AVAILABLE,null);
                    Log.d(TAG, "onInit: "+String.valueOf(R.string.two));
                }
                else {
                    Log.d(TAG, "onInit: -------------");
                }

            }
        });

    }
}

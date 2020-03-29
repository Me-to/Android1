package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Boolean b=true;
    MediaPlayer mediaPlayer;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   button=findViewById(R.id.btn);
   init();
    }

    private void init() {
         mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.p3);
         mediaPlayer.setLooping(false);
         mediaPlayer.start();
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (b){
                     b=false;
                     mediaPlayer.pause();
                     button.setText("停止");
                 }else {
                     b=true;
                     mediaPlayer.start();
                     button.setText("播放");
                 }
             }
         });
    }


}
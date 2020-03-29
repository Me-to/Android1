package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btn_start, btn_pause, btn_stop, btn_resler;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onclick();
    }

    private void onclick() {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp!=null&&mp.isPlaying()){

                } else {

                    mp.start();

                }

            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp!=null&&mp.isPlaying()){
                    mp.stop();
                    try {
                        mp.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp!=null&&mp.isPlaying()){
                    mp.pause();
                }

            }
        });
        btn_resler.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                if (mp!=null&&mp.isPlaying()) {
                    mp.release();
                }
            }
        });

    }

    private void init() {
        btn_start = findViewById(R.id.btn_1);
        btn_pause = findViewById(R.id.btn_2);
        btn_stop = findViewById(R.id.btn_3);
        btn_resler = findViewById(R.id.btn_4);
        mp = MediaPlayer.create(getApplication(), R.raw.p1);
        mp.setLooping(false);
    }
}
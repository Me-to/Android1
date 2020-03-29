package com.example.daojishi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int time;
    boolean a = true;
    ImageView imageView;
    Button buttonstart, buttonstop;
    TextView textView;
    String TAG = "-------------";

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                textView.setText(msg.obj + "");
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onclick();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (time >= 0) {
                Message message = new Message();
                int hours = time / 3600;
                int minutes = (time % 3600) / 60;
                int secs = time % 60;
                String shijian = String.format("%d:%02d:%02d", hours, minutes, secs);
                message.what = 1;
                message.obj = shijian;
                time = time - 1;
                Log.e(TAG, "time" + message.obj.toString());
                Log.e(TAG, "******" + time);
                handler.sendMessage(message);
                handler.postDelayed(runnable, 1000);
            } else {
                imageView.setVisibility(View.VISIBLE);
            }

        }

    };

    private void onclick() {
        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a) {
                    handler.post(runnable);
                    a = false;
                    imageView.setVisibility(View.GONE);
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), shezhi.class);
                handler.removeCallbacks(runnable);
                startActivityForResult(intent, 1);
            }
        });
        buttonstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
                a = true;
                imageView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void init() {
        buttonstart = findViewById(R.id.buttonstart);
        buttonstop = findViewById(R.id.buttonstop);
        textView = findViewById(R.id.tv_shijian);
        imageView = findViewById(R.id.imageView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            a = true;
            time = data.getExtras().getInt("shijian");
            Log.i("-------------", "onActivityResult: " + time);
            String a = String.valueOf(time);
            int hours = time / 3600;
            int minutes = (time % 3600) / 60;
            int secs = time % 60;
            String shijian = String.format("%d:%02d:%02d", hours, minutes, secs);
            textView.setText(shijian);
        }
    }
}

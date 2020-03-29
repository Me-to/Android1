package com.example.zuopin4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;

public class c extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c);
        Button button=findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }







}

package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class time extends AppCompatActivity {
    String TAG="----------";

    TextView tv_1;
    TextView tv_2;
    TextView tv_3;
    TextView tv_4;
    long time_1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        start();
    }

    private void start() {
        Log.e(TAG, "System.currentTimeMillis()"+System.currentTimeMillis() );
        Log.e(TAG, "Calendar.getInstance().getTimeInMillis()"+ Calendar.getInstance().getTimeInMillis());
        long a=  new Date().getTime();
        Log.e(TAG, "最后的时间"+a/1000 );
    }

    private void init() {
        tv_1=findViewById(R.id.textView);
        tv_2=findViewById(R.id.textView2);
        tv_3=findViewById(R.id.textView3);
        time_1=System.currentTimeMillis();
    }
}

package com.sdwfvc.android.wudianzong;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class kaijijiemian extends AppCompatActivity {
    TextView textView;
    int i=5;
    String TAG="-----------";
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    textView.setText(msg.obj.toString()+" 秒");
                    break;
                case 2:
                    handler.removeCallbacks(runnable);
                    Intent intent=new Intent(kaijijiemian.this,MainActivity.class);
                    startActivity(intent);

                    break;
            }

            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaijijiemian1);
        init();
    }

    private void init() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            //将actionBar隐藏
            actionBar.hide();
        }

        textView = findViewById(R.id.tv_shijian);
        handler.post(runnable);
    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (i>0) {
               i=--i;
                Log.e(TAG, "run: "+i);
                Message message = new Message();
                message.what = 1;
                message.obj = i;
                handler.sendMessage(message);
            }else if (i==0){
                Message message=new Message();
                message.what=2;
                handler.sendMessage(message);
                Log.e(TAG, "run: "+"fasdfsdafsdafsdf" );

            }
            handler.postDelayed(runnable,1000);
        }
    };
}

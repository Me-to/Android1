package com.example.tongzhi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    String TAG = "----------";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void tongzhi() {

        /**
         * 使用PendingIntent进行通知点击跳转功能。
         * PendingIntent的用法：
         * （1）、通过getActivity()、getBroadcast()、getService()方法获取实例
         * （2）、参数(Context context, int requestCode, Intent intent, int flags)
         * 第一个参数：Context
         * 第二个参数：requestCode 一般用不到 ，通常设置为0
         * 第三个参数：intent
         * 第四个参数：flags 用于确定PendingIntent的行为。这里传0就行
         */

        //跳转
        Intent intent = new Intent(MainActivity.this, a.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Log.e(TAG, "1");
        /**
         * 需要使用一个Builder构造器来创建Notification对象，由于API不同会造成不同版本的通知出现不稳定的问题，
         * 所以这里使用NotificationCompat类来兼容各个版本
         */
        //通知栏的显示
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE); //获取这个服务
        Notification notification = new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle("我是标题")     //设置标题
                .setContentText("我是内容")      //设置内容
                .setWhen(System.currentTimeMillis())   //设时间，获取当前时间
                .setSmallIcon(R.drawable.p2)           //设置小图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.p1))   //设置大图标

                .setContentIntent(pi) ///跳转
                .setAutoCancel(true)  //自动取消通知栏

                /**
                 * 通知的三种类型
                 * 1. 提示音
                 * 2. 震动
                 * 3. LED灯闪烁
                 * 4. 根据当前环境进行选择
                 */


                .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))  //设置提示音
                .setVibrate(new long[]{0, 1000, 1000, 1000})   //设置震动，需要添加权限，   <uses-permission android:name="android.permission.VIBRATE"/>
                .setLights(Color.GREEN, 1000, 1000)  //设置前置LED进行闪烁，第一个为颜色值，第二个为亮的时长，第三个为暗是时长
                .setDefaults(NotificationCompat.DEFAULT_ALL)   //使用默认效果，会很具手机当前环境播放铃声，是否震动
                .build();
        Log.e(TAG, "2");
        manager.notify(1, notification);
    }

    private void init() {
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tongzhi();
            }
        });
    }
}

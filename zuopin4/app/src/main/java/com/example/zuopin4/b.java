package com.example.zuopin4;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.app.Notification.BADGE_ICON_SMALL;

public class b extends AppCompatActivity {
    int PROGRESS_MAX = 100;
    int PROGRESS_CURRENT = 0;
    String Chanel_ID = "这是Channel_ID";
    int notificationId = 1;
    String name = "这是姓名";
    String description = "这是描述";
    MediaSession.Token mediaSession = null;
    String TAG = "---------";
    NotificationCompat.Builder builder;
    NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b);
        init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(Chanel_ID, name, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(description);
            channel.enableLights(false);//是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.GREEN); //小红点颜色
            channel.setShowBadge(true);//是否在久按桌面图标时显示此渠道的通知
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            Log.e(TAG, "");

        }
    }

    @SuppressLint("WrongConstant")
    private void init() {


        String GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL";

//
        Intent intent = new Intent(getApplicationContext(), c.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
        stackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

//        Intent intent = new Intent(this, c.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap bitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p1s);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.door_1);
        notificationManager = NotificationManagerCompat.from(getApplicationContext());
        builder = new NotificationCompat.Builder(this, Chanel_ID)
                .setContentText("内容")
                .setContentTitle("标题")
                .setSmallIcon(R.drawable.p2)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap)
                )
                .setDefaults(~0)
                .setFullScreenIntent(pendingIntent, true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setLargeIcon(bitmap1)
                .setBadgeIconType(BADGE_ICON_SMALL)
                .setNumber(1)
                .setContentIntent(pendingIntent);

        notificationManager.notify(notificationId, builder.build());
//        Notification newMessag=new NotificationCompat.Builder(this,Chanel_ID)
//                .setSmallIcon(R.drawable.p2)
//                .setContentText("Text")
//                .setContentTitle("Title")
//                .setLargeIcon(bitmap)
//                .setGroup(GROUP_KEY_WORK_EMAIL)
//                .build();
//
//
//
//        notificationManager.notify(1,newMessag);

//        Intent full = new Intent(getApplicationContext(), c.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, full, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.door_1);
//        Bitmap bitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p1s);
//        notificationManager = NotificationManagerCompat.from(getApplicationContext());


//
//        Notification notification=new Notification.Builder(this,Chanel_ID)
//                .setVisibility(Notification.VISIBILITY_PUBLIC)
//                .setSmallIcon(R.drawable.p3)
//                .addAction(R.drawable.door_1,"qian",pendingIntent)
//                .setStyle(new Notification.MediaStyle()
//                .setMediaSession(mediaSession)
//                .setShowActionsInCompactView(2))
//                .build();

//      notification.notifyAll();
//                notificationManager = NotificationManagerCompat.from(getApplicationContext());
//                builder = new NotificationCompat.Builder(getApplicationContext(), Chanel_ID);
//                builder.setContentText("Download in progress")
//                        .setContentTitle(" Picture Download")
//                        .setFullScreenIntent(pendingIntent,true)
//                        .setSmallIcon(R.drawable.p2)
//                        .setLargeIcon(bitmap1)
//                        .setStyle(new NotificationCompat.BigPictureStyle()
//                        .bigPicture(bitmap)
//                        .bigLargeIcon(null))

//                        .setStyle(new NotificationCompat.BigPictureStyle()
//                        .bigPicture(bitmap))
        //显示应用是否在锁屏可见
                        /*
                        VISIBILITY_PUBLIC 显示通知的完整内容。
                        VISIBILITY_SECRET 不在锁定屏幕上显示该通知的任何部分。
                        VISIBILITY_PRIVATE 显示基本信息，例如通知图标和内容标题，但隐藏通知的完整内容,比如Big。Text
                         */
//                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//                        .setAutoCancel(true)
//                        /**
//                         * 如果用户设备被锁定，会显示全屏 Activity，覆盖锁屏。
//                         * 如果用户设备处于解锁状态，通知以展开形式显示，其中包含用于处理或关闭通知的选项
//                         */
//                        .setPriority(NotificationCompat.PRIORITY_HIGH);


        //  notificationManager.notify(notificationId, builder.build());

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // 这几个都要设置为全局变量，否则新建的多线程没法接收到
//                // NotificationCompat.Builder builder;
//                // NotificationManagerCompat notificationManager;
//                //int PROGRESS_MAX = 100;
//                //int PROGRESS_CURRENT = 0;
//
//                for (int i = PROGRESS_CURRENT; i < PROGRESS_MAX; i++) {
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Log.e(TAG, "i的值" + i);
//                    //每次运行都进行+1
//                    PROGRESS_CURRENT = ++PROGRESS_CURRENT;
//                    builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
//                    notificationManager.notify(notificationId, builder.build());
//                    Log.e(TAG, "run: " + PROGRESS_CURRENT);
//                    //当他大于100时，下载完成并且删除此通知栏的下载按钮
//                    if (PROGRESS_CURRENT == 100) {
//                        //下载完成后出现这个
//                        builder.setContentText("Download complete")
//                                .setProgress(0, 0, false);
//                        notificationManager.notify(notificationId, builder.build());
//                    }
//                }
//
//            }
//        });

    }


}

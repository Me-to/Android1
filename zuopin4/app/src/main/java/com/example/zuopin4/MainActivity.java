package com.example.zuopin4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import android.app.Notification;
import android.app.Notification.Action;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    String Channel_ID = "a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuyao);
        init();

    }

    private void init() {
        //进行判断SDK是否 是Android 8及以上
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(Channel_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

//        Intent resultIntent =new Intent(this,c.class);
//        TaskStackBuilder stackBuilder=TaskStackBuilder.create(this);
//        stackBuilder.addNextIntentWithParentStack(resultIntent);
//        PendingIntent resultpendingIntent=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        //    Intent intent = new Intent(this, aaaa.class);
        /**
         *以上所示的 setFlags() 方法帮助保留用户在通过通知打开应用后的预期导航体验。但您是否要使用这一方法取决于您要启动的 Activity 类型，类型可能包括：
         *
         * 专用于响应通知的 Activity。用户在正常使用应用时不会无缘无故想导航到这个 Activity，因此该 Activity 会启动一个新任务，而不是添加到应用的现有任务和返回栈。这就是以上示例中创建的 Intent 类型。
         * 应用的常规应用流程中存在的 Activity。在这种情况下，启动 Activity 时应创建返回栈，以便保留用户对返回和向上按钮的预期。
         */
        //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


//        String key_text_reply="key_text_reply";
//        RemoteInput remoteInput=new RemoteInput.Builder(key_text_reply)
//                .setLabel("replyLabel")
//                .build();

//PendingIntent replyPendingIntent=PendingIntent.getBroadcast(getApplicationContext(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent = new Intent(this, aaaa.class);
        intent.setAction(Intent.ACTION_SCREEN_ON);
        intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        String key_text_reply = "key_text_reply";
        RemoteInput remoteInput = new RemoteInput.Builder(key_text_reply)
                .setLabel("replyLabel")
                .build();
//        RemoteInput remoteInput=new RemoteInput.Builder(key_text_reply)
//                .setLabel("replyLabel")
//                .build();

        PendingIntent replyPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.p1s, "replyLabel", replyPendingIntent)
                .addRemoteInput(remoteInput)
                .build();
//        NotificationCompat.Action action=new NotificationCompat.Action.Builder(R.drawable.p1s,"replyLabel",replyPendingIntent)
//                .addRemoteInput(remoteInput)
//                .build();
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification newMessage = new Notification.Builder(MainActivity.this, Channel_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("消息标题")
                .setContentText("消息文本")
//                .addAction(action)
                .build();


        //通道号，这个要和上面的一致
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Channel_ID)
                .setSmallIcon(R.drawable.p1s)
                .setContentTitle("这是标题")
                .setContentText("这是内容")
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText("这是大型通知内容。lllllllllllllllllllllllllllllllllllllllllllll" +
//                                ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;"))

                .setPriority(NotificationCompat.PRIORITY_HIGH)//设置优先级
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.p1s, "回复", pendingIntent);  //点击后自动取消通知

        NotificationManagerCompat notificationManager1 = NotificationManagerCompat.from(this);
        //这个ID不是Channel ID
        // notificationId是必须定义的每个通知的惟一int类型
        notificationManager1.notify(2, newMessage);

        // 把通知栏显示出来
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        //这个ID不是Channel ID
//        // notificationId是必须定义的每个通知的惟一int类型
//        notificationManager.notify(1, builder.build());


    }
}

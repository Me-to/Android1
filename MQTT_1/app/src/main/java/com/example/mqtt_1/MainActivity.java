package com.example.mqtt_1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.android.service.MqttService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button button1, button2;
    TextView textView;
    String TAG = "-------------";
    MqttClient client;
    MqttCallback callback;
    Handler handler;
    private ScheduledExecutorService scheduler;
    MqttConnectOptions options;
    String usename = "5atv0c4/zhangqian";
    String password = "aApOdvMA6IYGN0U2";
    String Host_Server = "tcp://5atv0c4.mqtt.iot.gz.baidubce.com:1883";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        MQTT();
        startReconnect();
        publish("zhuti", "aaaaaaaaaa");
    }

    private void init() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 1) {
                    Toast.makeText(MainActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                    //textView.setText((String) msg.obj);
                    textView.setText(msg.obj.toString().trim());
                } else if (msg.what == 2) {
                    try {
                        client.subscribe("zhuti", 0);//订阅主题“timeTopic”
                        Toast.makeText(MainActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (msg.what == 3) {
                    Toast.makeText(MainActivity.this, "连接失败，系统正在重连", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }


    private void MQTT() {

        try {
            Log.e(TAG, "MQTT: -----------");
            String imei = "1";
            //Host_Server为主机名，      imei为连接MQTT的客户端ID，     一般是客户端的唯一标示符表示，MemoryPersistence设置clientid的保存形式
            client = new MqttClient(Host_Server.trim(), imei, new MemoryPersistence());
            //MQTT的连接设置
            options = new MqttConnectOptions();
            //设置是否清空session，是这里如果设置为false表示服务器会保留客户端点击连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            //设置连接的用户名
            options.setUserName(usename.trim());
            //设置连接的密码
            options.setPassword(password.toCharArray());
            //设置连接的超时时间  单位为秒
            options.setConnectionTimeout(6);
            //设置会话心跳时间，单位为秒，  服务器每个1.5*20秒的时间向客户端发送消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            Log.e(TAG, "1: -----------");
            //核心内容************这句一定不能少
            client.connect(options);
            //设置回调
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.d(TAG, "connectionLost: ------------");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Log.d(TAG, "messageArrived: -------------");
                    Message msg = new Message();
                    msg.what = 1;   //收到消息标志位
                    msg.obj = topic + "_" + message.toString();
                    handler.sendMessage(msg);    // hander 回传
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    //subscribe后得到的消息会执行到这里面
                    Log.d(TAG, "deliveryComplete: ------------" + token.isComplete());
                }
            });
//            client.setCallback(new MqttCallback() {
//                @Override
//                public void connectionLost(Throwable throwable) {
//                    //连接丢失后，一般在这里面进行重连
//                    Log.d(TAG, "connectionLost: ------------");
//                    startReconnect();
//                }
//
//                @Override
//                public void messageArrived(String topic, MqttMessage message) throws Exception {
//                    //publish后会执行到这里
//                    Log.d(TAG, "messageArrived: -------------");
//                    Message msg = new Message();
//                    msg.what = 1;   //收到消息标志位
//                    msg.obj = topic + "_" + message.toString();
//                    handler.sendMessage(msg);    // hander 回传
//
//                }
//
//                @Override
//                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
//                    //subscribe后得到的消息会执行到这里面
//                    Log.d(TAG, "deliveryComplete: ------------" + iMqttDeliveryToken.isComplete());
//                }
//            });
            if (!client.isConnected()) {
                client.connect();
            }

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    /*
    获取手机IMEI的信息
     */
    private String getIMEI(Context context, int sloid) {

        try {
            @SuppressLint("ServiceCast") TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELECOM_SERVICE);
            Method method = manager.getClass().getMethod("getImei", int.class);
            String imei = (String) method.invoke(manager, sloid);
            return imei;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    MQTT建立连接及重连
     */
    private void startReconnect() {
        Log.e(TAG, "2: -----------");
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (!client.isConnected()) {
                    connect();
                }
            }
        }, 0, 10 * 1000, TimeUnit.MILLISECONDS);
    }


    /**
     * MQTT连接状态鉴别
     */
    private void connect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.connect(options);
                    Message msg = new Message();
                    msg.what = 2;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = 3;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    /**
     * 向Topic发送消息
     *
     * @param topic
     * @param sendMessage
     */
    public void publish(String topic, String sendMessage) {
        Integer qos = 0;
        Boolean retained = false;
        try {
            if (client != null) {
                Log.e(TAG, "" + client.isConnected());
                client.publish(topic, sendMessage.getBytes(), qos.intValue(), retained.booleanValue());

            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            scheduler.shutdown();
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
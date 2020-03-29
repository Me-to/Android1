package com.example.mqtt_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MainActivity extends AppCompatActivity {
    String usename="5atv0c4/zhangqian";
    String password="aApOdvMA6IYGN0U2";
    String Server_Host="tcp://5atv0c4.mqtt.iot.gz.baidubce.com:1883";
    String imei="1";
    String TAG="--------";
    MqttClient client;
    MqttConnectOptions options;
    MqttCallback callback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        try {
            //初始化连接诶配置
            client=new MqttClient(Server_Host,imei,new MemoryPersistence());
            options=new MqttConnectOptions();
            options.setUserName(usename);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(30);
            options.setCleanSession(true);
            options.setKeepAliveInterval(30);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅消息的主题
     */
    void subscribeMsg(String topic,int qos){
        if (client!=null){
            int [] Qos={qos};
            String[] topicl={topic};

        }
        try {
            client.subscribe(topic,qos);
            Log.e(TAG, "开始订阅"+topic );
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布消息
     */
    void publish(String topic,String msg,boolean isRetained,int qos){
        
    }
}

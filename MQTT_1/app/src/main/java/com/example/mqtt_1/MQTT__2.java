package com.example.mqtt_1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import org.eclipse.paho.client.mqttv3.MqttClient;

public class MQTT__2 extends AppCompatActivity {
    String usename="5atv0c4/zhangqian";
    String password="aApOdvMA6IYGN0U2";
    String Server_Host="tcp://5atv0c4.mqtt.iot.gz.baidubce.com:1883";



    MqttClient client;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}

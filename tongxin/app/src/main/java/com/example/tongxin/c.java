package com.example.tongxin;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class c extends AppCompatActivity {
    String TAG="-----------";

    TextView textView;
    Button button;
    EditText editText;
    DatagramSocket datagramSocket=null;
    BufferedReader bufferedReader=null;
    DatagramPacket datagramPacket=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    void kehu(){
        Log.i(TAG, "kehu: ");
        if (datagramSocket==null){
            try {
                datagramSocket=new DatagramSocket(6000);
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
        String data = "阳光沙滩：sunofbeaches.com";
        byte[] buf = data.getBytes();
        //把数据转换成字节数组
     datagramPacket = new DatagramPacket(buf,
                buf.length);
        Log.i(TAG, "kehu: *****");

    }
    private void init() {
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
       new Thread(new Runnable() {
           @Override
           public void run() {
               kehu();
               Log.i(TAG, "run: ");
           }
       }).start();
    }

}

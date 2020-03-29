package com.example.tongxin;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class biancheng extends AppCompatActivity {
    InetSocketAddress inetSocketAddress = null;
    TextView textView;
    int a=0;
    Button button;
    EditText editText;
    int port=6000;
    DatagramSocket datagramSocket=null;
    DatagramPacket datagramPacket=null;
    byte[] b=new byte[1024];
    String TAG="-----------";
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.e(TAG, "handleMessage: "+msg.what );
            switch (msg.what){

                case 1:
                    Log.e(TAG, "run: ------+" );

                    textView.setText(msg.obj.toString());
                    Log.e(TAG, "handleMessage: "+"进行加载ui" );
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        init();

    }
    void client(){
        Log.e(TAG, "client: 进入client" );

     Thread thread=   new Thread(new Runnable() {
            @Override
            public void run() {

                   try {
                       Log.e(TAG, "run: 开始"+a++ );
                       inetSocketAddress=new InetSocketAddress(port);
                       datagramSocket=new DatagramSocket(inetSocketAddress);
                       Log.e(TAG, "服务器开启");
                       datagramPacket=new DatagramPacket(b,b.length);

                       datagramSocket.receive(datagramPacket);
                       Log.e(TAG, "接收数据包长"+datagramPacket.getLength());
                       String s=new String(datagramPacket.getData(),datagramPacket.getOffset(),datagramPacket.getLength());
                       Message message=new Message();
                       message.what=1;
                       message.obj=s;
                       handler.sendMessage(message);


                   } catch (SocketException e) {
                       e.printStackTrace();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   finally {
                       if (datagramSocket!=null){
                           datagramSocket.close();
                       }
                   }

            }
        });
        thread.start();
    }
    private void init() {
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        editText=findViewById(R.id.editText);
        client();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
}

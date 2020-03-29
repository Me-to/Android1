package com.example.sock_1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class c extends AppCompatActivity {
    BufferedReader bufferedReader;
    OutputStream outputStream;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sock();
    }

    private void sock() {
        try {
            Socket socket=new Socket("172.45..6210",888);
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream=socket.getOutputStream();
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void read() {
        String s;
        while (true){
            while (true){
                try {
                    if (((s=bufferedReader.readLine())!=null)){
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message message=new Message();
                message.what=1;
                message.obj=bufferedReader.toString();
                handler.sendMessage(message);

            }
        }
    }
}

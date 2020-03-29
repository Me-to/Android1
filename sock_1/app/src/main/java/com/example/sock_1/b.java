package com.example.sock_1;

import android.net.http.SslCertificate;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class b extends AppCompatActivity {
    BufferedReader bufferedReader;
    OutputStream outputStream;
    Button button;
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
        init();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrintWriter printWriter=new PrintWriter(outputStream);
                printWriter.println("asdasdfasdf");
                printWriter.flush();

            }
        });
    }

    private void init() {
        Socket socket= null;
        try {
            socket = new Socket("12.25.6.5.4",8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream=socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                du();
            }
        }).start();
    }

    private void du() {
        String a;
        while (true){
            while (true){
                try {
                    if (((a=bufferedReader.readLine())!=null)) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            Message message=Message.obtain();
            message.obj=bufferedReader.toString();
            message.what=1;

        }
    }
}

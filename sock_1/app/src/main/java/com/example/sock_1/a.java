package com.example.sock_1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class a extends AppCompatActivity {
    BufferedReader bufferedReader;
    OutputStream outputStream;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(a.this, "登录成功", Toast.LENGTH_SHORT).show();
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
        try {
            sockey();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void init() {
        PrintWriter printWriter=new PrintWriter(outputStream);
        printWriter.println("sdafsdaf");
        printWriter.flush();

    }

    private void du() throws IOException {
        String a;
        while (true){
        while ((a=bufferedReader.readLine())!=null) {
            Log.e("----------",a );
        }
        Message message=Message.obtain();
        message.what=1;
        message.obj=bufferedReader.toString();
        handler.sendMessage(message);
        }
    }

    private void sockey() throws IOException {
        Socket socket=new Socket("172.162.153",8889);
        bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
      outputStream=socket.getOutputStream();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    du();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


}

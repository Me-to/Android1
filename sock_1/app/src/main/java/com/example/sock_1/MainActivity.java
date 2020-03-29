package com.example.sock_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
String TAG="---------";
BufferedReader bufferedReader;
OutputStream outputStream;
TextView textView;
//用来更新UI内容
Handler handler=new Handler(){
    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.what){
            case 1:
                textView.setText(msg.obj.toString());
                break;
        }
        super.handleMessage(msg);
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrintWriter writer =new PrintWriter(outputStream);
                writer.println("倩倩姐爱你呦");
                writer.flush();

            }
        });
        //新建一个多线程，让socket在里面运行
    new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                socket();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }).start();
    }

    private void socket() throws IOException {
        //新建一个socket对象 并设置好
        Socket socket=new Socket("192.168.1.108",8889);
        //bufferedReader new  一个新的InputSreamRead(socket.接收到的信息)
        bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //outputStream  =socket.   要打印的信息
        outputStream=socket.getOutputStream();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reada();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void reada() throws IOException {
        String s;
        while (true){
            while ((s=bufferedReader.readLine())!=null){
                Log.e(TAG, s);
            }
            Message message=Message.obtain();
            message.what=1;
            message.obj=bufferedReader.toString();
            handler.sendMessage(message);
        }
    }
}

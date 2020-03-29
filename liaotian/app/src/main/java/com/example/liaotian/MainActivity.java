package com.example.liaotian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Socket socket;
    Button button;
    EditText editText;
    TextView textView;
    String Client_message;
    String    Server_message;
    OutputStream os=null;
    InputStream is;


    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
     onclick();


    }

    private void onclick() {
           button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Client_message=editText.getText().toString();
                   PrintWriter pw=new PrintWriter(os);
                   pw.write(Client_message+"\n");
                   pw.flush();
                   pw.close();
                   new Thread(new Runnable() {
                       @Override
                       public void run() {

                       }
                   })
               }
           });
    }



    private void init() {
      new Thread(new Runnable() {
          @Override
          public void run() {
              PopupMenu popupMenu =new PopupMenu()
              try {
                  socket=new Socket("192.168.1.110",8887);
                  os=socket.getOutputStream();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }).start();
        button=findViewById(R.id.button);
        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);
        view=findViewById(R.id.beijing);
        view.getBackground().setAlpha(100);
    }

}

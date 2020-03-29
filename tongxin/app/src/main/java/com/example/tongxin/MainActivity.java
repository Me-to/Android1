package com.example.tongxin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.IllegalFormatCodePointException;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    EditText editText;
    DatagramSocket datagramSocket=null;
   // BufferedReader bufferedReader=null;
   InetSocketAddress inetSocketAddress = null;
    String TAG="-----------";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void client() throws IOException {
        if (datagramSocket==null) {
            datagramSocket = new DatagramSocket();
        }
        inetSocketAddress=new InetSocketAddress(10000);

//        if (bufferedReader==null){
//            InputStream inputStream=new FileInputStream(editText.getText().toString());
//          bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
//        }

         String a=editText.getText().toString();
            byte[] b=a.getBytes();
            DatagramPacket datagramPacket=new DatagramPacket(b,0,b.length,inetSocketAddress);
            datagramSocket.send(datagramPacket);

//       if (datagramSocket!=null){
//           datagramSocket.close();
//           Log.e(TAG, "client: "+"datagramSocket关闭");
//       }

    }

    private void init() {
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        editText=findViewById(R.id.editText);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         client();
                     } catch (FileNotFoundException e) {
                         e.printStackTrace();
                     } catch (SocketException e) {
                         e.printStackTrace();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
             }).start();
             }
         });
    }
}

package com.example.t_16;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nle.mylibrary.forUse.zigbee.ZigBee;
import com.nle.mylibrary.transfer.DataBusFactory;

public class MainActivity extends AppCompatActivity {

    ZigBee zigBee;
    Button button;
    shujvku s;
    double i=0.000001;
    public  String wen1,shi1,kong1,ren1,guang1;
    TextView wen, shi, kong, ren, guang;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    String[] a = msg.obj.toString().split("-");
                    wen.setText("温度：" + a[0] + " C");
                    shi.setText("湿度：" + a[1] + " %");
                    if (a[2].equals(1)) {
                        ren.setText("人体：有人");
                    } else {
                        ren.setText("人体：无人");
                    }
                    kong.setText(" 空气质量：" + a[3]);
                    guang.setText("光照：" + a[4]);

                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        s=new shujvku();

        button=findViewById(R.id.button);
        wen = findViewById(R.id.wen);
        shi = findViewById(R.id.shi);
        kong = findViewById(R.id.kong);
        ren = findViewById(R.id.ren);
        guang = findViewById(R.id.guang);
//        zigBee = new ZigBee(DataBusFactory.newSocketDataBus("192.168.16", 3), null);
        handler.post(runnable);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,shujvku.class);
                startActivity(intent);
//                handler.removeCallbacks(runnable);
                //startActivityForResult(intent,1);
            }
        });
        s.a();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            jia();
         //   du();
            handler.postDelayed(runnable, 1000);
        }
    };
    /**
     *
     */
    void jia(){

        double wen = i;
        double shi = i;
        double ren =i;
        double kong = i;
        double guang =i;

        wen1 = String.valueOf(wen).substring(0, 5);
        shi1 = String.valueOf(shi).substring(0, 5);
        ren1 = String.valueOf(ren).substring(0, 1);
        kong1 = String.valueOf(kong).substring(0, 5);
        guang1 = String.valueOf(guang).substring(0, 5);

        Message message = new Message();
        message.what = 1;
        message.obj = wen1 + "-" + shi1 + "-" + ren1 + "-" + kong1 + "-" + guang1;
        handler.sendMessage(message);
        i=i+1;
    }

    void du() {
        double[] val = new double[2];
        try {
            val = zigBee.getTmpHum();
            double wen = val[0];
            double shi = val[1];
            double ren = zigBee.getPerson();
            double kong = zigBee.getCO();
            double guang = zigBee.getLight();

            wen1 = String.valueOf(wen).substring(0, 5);
            shi1 = String.valueOf(shi).substring(0, 5);
            ren1 = String.valueOf(ren).substring(0, 1);
          kong1 = String.valueOf(kong).substring(0, 5);
             guang1 = String.valueOf(guang).substring(0, 5);

            Message message = new Message();
            message.what = 1;
            message.obj = wen1 + "-" + shi1 + "-" + ren1 + "-" + kong1 + "-" + guang1;
            handler.sendMessage(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        handler.post(runnable);
        super.onActivityResult(requestCode, resultCode, data);
    }
}

package com.example.MyApplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nle.mylibrary.enums.led.PlayType;
import com.nle.mylibrary.enums.led.ShowSpeed;
import com.nle.mylibrary.forUse.led.LedScreen;
import com.nle.mylibrary.forUse.mdbus4150.MdBus4150SensorListener;
import com.nle.mylibrary.forUse.mdbus4150.Modbus4150;
import com.nle.mylibrary.transfer.DataBusFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    LedScreen led;
    Modbus4150 modbus4150;
    Button button;
    String a, b, c, d, e, f;
    int che;
    boolean pan=true;
    SharedPreferences sharedPreferences;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    button.setText("剩余车位数量"+(Integer.valueOf(f)-che));
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

    String[] name = {"modbus IP地址", "modbus IP端口", "LED IP地址", "LED 端口", "红外对射DI口", "车位数量"};

    private void init() {
        textView=findViewById(R.id.tv1_shezhi);
        button = findViewById(R.id.btn1_剩余);
        sharedPreferences = getSharedPreferences("A", MODE_PRIVATE);
        sp();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,shezhi.class);
                handler.removeCallbacks(runnable);

                startActivity(intent);
            }
        });
        modbus4150 = new Modbus4150(DataBusFactory.newSocketDataBus(a, Integer.parseInt(b)), null);
        led = new LedScreen(DataBusFactory.newSocketDataBus(c, Integer.parseInt(d)), null);
        handler.post(runnable);
    }

    private void sp() {
        a = sharedPreferences.getString("" + 1, null);
        b = sharedPreferences.getString("" + 2, null);
        c = sharedPreferences.getString("" + 3, null);
        d = sharedPreferences.getString("" + 4, null);
        e = sharedPreferences.getString("" + 5, null);
        //车位数量
        f = sharedPreferences.getString("" + 6, null);
    }
    private void hongwai(){
        try {
            modbus4150.getDIVal(Integer.parseInt(b), new MdBus4150SensorListener() {
                @Override
                public void onVal(int i) {
            if (i==1){
               if (che>Integer.valueOf(f)){
                 che=Integer.valueOf(f);
                   try {
                       led.sendTxt("已用"+che+"剩余"+(Integer.valueOf(f)-che), PlayType.LEFT, ShowSpeed.SPEED1,0,1,null);
                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }
               }else {
                   if (pan){
                       che=che+1;
                       pan=false;
                       try {
                           led.sendTxt("已用"+che+"剩余"+(Integer.valueOf(f)-che), PlayType.LEFT, ShowSpeed.SPEED1,0,1,null);
                       } catch (Exception ex) {
                           ex.printStackTrace();
                       }
                   }
               }
            }else if (i==0){
                pan=true;
            }
                }

                @Override
                public void onFail(Exception e) {

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
       hongwai();
       xinx();
       handler.removeCallbacks(runnable,1000);
        }
    };
    private void xinx() {
          Message message=new Message();
          message.what=1;
          message.obj=che;
          handler.sendMessage(message);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        sp();
        handler.post(runnable);
    }
}

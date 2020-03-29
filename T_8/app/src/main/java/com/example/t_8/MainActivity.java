package com.example.t_8;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.nle.mylibrary.forUse.zigbee.ZigBee;
import com.nle.mylibrary.transfer.DataBusFactory;

public class MainActivity extends AppCompatActivity {
    // String[] name = {"zigbee IP地址", "zigbee 端口", "双联继电器系列号", "风扇联数", "照明灯联数", "温度通道号", "湿度通道号", "光照最高值", "光照最低值"};
    int ip, duan, xi, feng, zhao, wen, shi, guang_max, guang_mini;
    SharedPreferences sharedPreferences;
    ZigBee zigBee;
    String[] me = new String[9];
    TextView tv_guang, tv_shi;
    Switch sfeng, sdeng;
    ImageView imageViewfeng, imageViewdeng,imageViewtiao;
Handler handler=new Handler(){
    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.what){
            case 1:
                String [] data=msg.obj.toString().trim().split("-");
                double shi=Double.valueOf(data[0].substring(0,5));
                double light=Double.valueOf(data[1].substring(0,5));
                tv_guang.setText(light+"rh%");
                tv_shi.setText(shi+"rh");
                if (sharedPreferences.getString("o",null).equals("1")){
                    if (guang_max<light){
                        try {
                            zigBee.ctrlDoubleRelay(duan,zhao,false,null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if (guang_mini>light){
                        try {
                            zigBee.ctrlDoubleRelay(duan,zhao,true,null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if (shi>wen){
                        try {
                            zigBee.ctrlDoubleRelay(duan,feng,false,null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if (shi<shi){
                        try {
                            zigBee.ctrlDoubleRelay(duan,zhao,true,null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }else {

                }
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
       sdeng=findViewById(R.id.switch2deng);
       sfeng=findViewById(R.id.switch1feng);
        sharedPreferences = getSharedPreferences("A", MODE_PRIVATE);
        ip = Integer.valueOf(me[0]);
        duan = Integer.valueOf(me[0]);
        xi = Integer.valueOf(me[0]);
        feng = Integer.valueOf(me[0]);
        zhao = Integer.valueOf(me[0]);
        wen = Integer.valueOf(me[0]);
        shi = Integer.valueOf(me[0]);
        guang_max = Integer.valueOf(me[0]);
        guang_mini=Integer.valueOf(me[0]);
       zigBee=new ZigBee(DataBusFactory.newSocketDataBus(String.valueOf(ip),duan),null);
      sfeng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              try {
                  zigBee.ctrlDoubleRelay(duan,feng,isChecked,null);
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      });
        sdeng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    zigBee.ctrlDoubleRelay(duan,zhao,isChecked,null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            read();
        }
    };

    private void read() {
        try {
            double [] wen=zigBee.getTmpHum();
            Message message=new Message();
            message.what=1;
            message.obj=wen[1]+"-"+zigBee.getLight();
            handler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2){
            sharedPreferences = getSharedPreferences("A", MODE_PRIVATE);
            for (int i=0;i<me.length;i++){
                me[i]=sharedPreferences.getString(""+i,null);
            }
            ip = Integer.valueOf(me[0]);
            duan = Integer.valueOf(me[0]);
            xi = Integer.valueOf(me[0]);
            feng = Integer.valueOf(me[0]);
            zhao = Integer.valueOf(me[0]);
            wen = Integer.valueOf(me[0]);
            shi = Integer.valueOf(me[0]);
            guang_max = Integer.valueOf(me[0]);
            guang_mini=Integer.valueOf(me[0]);
            zigBee=new ZigBee(DataBusFactory.newSocketDataBus(String.valueOf(ip),duan),null);
            if (sharedPreferences.getString("o",null).equals("1")){
                sfeng.setVisibility(View.GONE);
                sdeng.setVisibility(View.GONE);
            }else if (sharedPreferences.getString("o",null).equals("2")){
                sfeng.setVisibility(View.VISIBLE);
                sdeng.setVisibility(View.VISIBLE);
            }
        }
    }
}

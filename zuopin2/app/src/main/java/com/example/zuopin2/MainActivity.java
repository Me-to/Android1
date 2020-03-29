package com.example.zuopin2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nle.mylibrary.forUse.zigbee.FourChannelValConvert;
import com.nle.mylibrary.forUse.zigbee.ZigBee;
import com.nle.mylibrary.forUse.zigbee.ZigBeeControlListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String TAG = "-------------";
    TextView textViewTem, textViewHum;
    ZigBee zigBee;
    boolean f = true, d = true;
    String[] message = new String[4];
    SharedPreferences sharedPreferences;
    ImageView imageViewfeng, imageViewdeng, imageViewfengkai, imageViewdengkai, shezhiaa;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String[] a = msg.obj.toString().split("-");
                    textViewTem.setText("湿度:" + a[1] + "%rh");
                    textViewHum.setText("温度:" + a[0] + "°C");
//                    if (Double.valueOf(a[1])>Double.valueOf(message[2])){
//                        try {
//                            zigBee.ctrlDoubleRelay(1,0,false,null);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                   else if (Double.valueOf(a[1])<Double.valueOf(message[3])){
//                        try {
//                            zigBee.ctrlDoubleRelay(1,0,true,null);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                 else    if (Double.valueOf(a[0])>Double.valueOf(message[0])){
//                        try {
//                            zigBee.ctrlDoubleRelay(1,1,true,null);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                 else    if (Double.valueOf(a[0])<Double.valueOf(message[1])){
//                        try {
//                            zigBee.ctrlDoubleRelay(1,1,false,null);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }

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
        textViewHum = findViewById(R.id.textViewhum);
        textViewTem = findViewById(R.id.textViewtem);
        imageViewdeng = findViewById(R.id.imageViewdeng);
        imageViewfeng = findViewById(R.id.imageViewfengshan);
        imageViewdengkai = findViewById(R.id.imageViewdengkaiguan);
        imageViewfengkai = findViewById(R.id.imageViewfengkaiguan);
        shezhiaa = findViewById(R.id.imageView6);
        sharedPreferences=getSharedPreferences("qian",MODE_PRIVATE);
        onclick();
        open();
        handler.post(runnable);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            du();
            handler.postDelayed(runnable, 5000);
        }
    };

    void open() {
        zigBee = new ZigBee(DataBusFactory.newSerialDataBus(1, 38400), null);
        for (int i=0;i<message.length;i++){
            message[0]=sharedPreferences.getString(""+i,"null");
        }
    }

    void close() {
        if (zigBee != null) {
            zigBee.stopConnect();
        }
    }

    void onclick() {
        imageViewfengkai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (f) {
                    imageViewfengkai.setImageResource(R.drawable.btn_list_switch_on);
                    imageViewfeng.setImageResource(R.drawable.donghua);
                    AnimationDrawable animationDrawable = (AnimationDrawable) imageViewfeng.getDrawable();
                    animationDrawable.start();
                    f = false;
                    Toast.makeText(MainActivity.this, "风扇已打开", Toast.LENGTH_SHORT).show();
                    try {
                       zigBee.ctrlDoubleRelay(1, 1, true, new ZigBeeControlListener() {
                           @Override
                           public void onCtrl(boolean b) {
                               Log.e(TAG, "zigbee1+true" );
                           }

                           @Override
                           public void onFail(Exception e) {

                           }
                       });
                      //  zigBee.ctrlSingleRelay(2,true,null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "风扇已关闭", Toast.LENGTH_SHORT).show();
                    imageViewfengkai.setImageResource(R.drawable.btn_list_switch_off);
                    imageViewfeng.setImageResource(R.drawable.pic_cartoon_fan_1);
                    f = true;
                    try {
                        // zigBee.ctrlSingleRelay(2,false,null);
                       zigBee.ctrlDoubleRelay(1, 1, false, new ZigBeeControlListener() {
                           @Override
                           public void onCtrl(boolean b) {
                               Log.e(TAG, "zigbee1+false" );
                           }

                           @Override
                           public void onFail(Exception e) {

                           }
                       });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        imageViewdengkai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (d==true) {
                    imageViewdengkai.setImageResource(R.drawable.btn_list_switch_on);
                    imageViewdeng.setImageResource(R.drawable.lamp_on);

                    try {
                       // zigBee.ctrlSingleRelay(1,true,null);
                        zigBee.ctrlDoubleRelay(1, 2, true, new ZigBeeControlListener() {
                            @Override
                            public void onCtrl(boolean b) {
                                Log.e(TAG, "zigbee0+true" );
                            }

                            @Override
                            public void onFail(Exception e) {

                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, "灯已打开", Toast.LENGTH_SHORT).show();
                    d = false;
                } else if (d==false){
                    imageViewdengkai.setImageResource(R.drawable.btn_list_switch_off);
                    imageViewdeng.setImageResource(R.drawable.lamp_off);
                    try {
                       // zigBee.ctrlSingleRelay(1,false,null);
                       zigBee.ctrlDoubleRelay(1, 2, false, new ZigBeeControlListener() {
                           @Override
                           public void onCtrl(boolean b) {
                               Log.e(TAG, "zigbee0+false" );
                           }

                           @Override
                           public void onFail(Exception e) {

                           }
                       });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, "灯已关闭", Toast.LENGTH_SHORT).show();
                    d = true;
                }
            }
        });
        shezhiaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, shezhi.class);
                handler.removeCallbacks(runnable);
                close();
                startActivityForResult(intent, 1);
            }
        });
    }

    void du() {
        double[] vals = new double[2];

            vals[0] = Math.random() * (14+1-10) + 13;   //产生了10到12的随机数
            vals[1] = Math.random() * (35-30+1) + 30;
            String a = String.valueOf(vals[0]).substring(0, 5);
            String b = String.valueOf(vals[1]).substring(0, 5);

            Message message = new Message();
            message.obj = a + "-" + b;
            message.what = 1;
        handler.sendMessage(message);
            Log.e(TAG, "温度000000000000000000" + vals[0]);
            Log.e(TAG, "湿度000000000000000000" + vals[1]);

//
//            vals=zigBee.getFourEnter();
//            double a= FourChannelValConvert.getTemperature(vals[0]);
//            double b=FourChannelValConvert.getHumidity(vals[1]);
//            String tem_s =String.valueOf(a).substring(0,5);
//            String hum_s=String.valueOf(b).substring(0,5);
//            Message message=new Message();
//            message.what=1;
//            message.obj=tem_s+"-"+hum_s;

//            vals=zigBee.getTmpHum();
//            Log.e(TAG, "温度000000000000000000" +vals[0]);
//            Log.e(TAG, "湿度000000000000000000"+vals[1] );
//            Message message = new Message();
//            message.what = 1;
//           message.obj = vals[0] + "-" + vals[1];


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        open();
        handler.post(runnable);
        super.onActivityResult(requestCode, resultCode, data);
    }
}

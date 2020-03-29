package com.example.zuopin1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nle.mylibrary.enums.led.PlayType;
import com.nle.mylibrary.enums.led.ShowSpeed;
import com.nle.mylibrary.forUse.led.LedCommonListenerImpl;
import com.nle.mylibrary.forUse.led.LedScreen;
import com.nle.mylibrary.forUse.mdbus4150.MdBus4150RelayListener;
import com.nle.mylibrary.forUse.mdbus4150.MdBus4150SensorListener;
import com.nle.mylibrary.forUse.mdbus4150.Modbus4150;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Modbus4150 modbus4150;
    LedScreen ledScreen;
  //  String[] name = {"modbus IP地址", "modbus 端口", "LED IP地址", "LED 端口", "modbus 人体端子", "modbus 烟雾端子", "报警器端子"};

    String[] message = {"192.168.0.200", "2003", "192.168.0.200", "2004", "2", "3", "2"};
    ImageView imageView;
    SharedPreferences sharedPreferences;
    TextView textViewren, textViewyan;
String TAG="----------------";
    int ren, yan;
    boolean b=true;
    TextToSpeech t;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e(TAG, "1111111111111" );
            switch (msg.what) {
                case 1:
                    Log.e(TAG, "handleMessage: " );
                    String[] xin = msg.obj.toString().split("-");
                    if (xin[0] == "1") {
                        textViewren.setText("有人");
                        Log.e(TAG, "有人" );
                        try {
                         //   ledScreen.sendTxt("有人", PlayType.LEFT, ShowSpeed.SPEED1, 3, 100, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (xin[1] == "0") {
                        textViewren.setText("没人");
                        Log.e(TAG, "没人" );
                        try {
                      //      ledScreen.sendTxt("有人", PlayType.LEFT, ShowSpeed.SPEED1, 3, 100, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (Integer.valueOf(xin[1]) == 1) {
                        textViewyan.setText("有烟雾");
                        try {

                            if (b){
                                t.setPitch(0.9f);
                                t.speak("有烟雾，请注意危险情况的发生",TextToSpeech.LANG_AVAILABLE,null);
                                b=false;
                            }
                            modbus4150.ctrlRelay(3, true, new MdBus4150RelayListener() {
                                @Override
                                public void onCtrl(boolean b) {
                                    Log.e(TAG, "烟雾成功打开"+xin[1] );
                                }

                                @Override
                                public void onFail(Exception e) {
                                    Log.e(TAG, "烟雾打开失败-----------------"+xin[1] );
                                }
                            });
                          //  modbus4150.ctrlRelay(Integer.parseInt(message[6]), true, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (Integer.valueOf(xin[1]) == 0) {
                        // xin[1].equals("1")
                        textViewyan.setText("没有烟雾");
                        try {
                            b=true;
                            modbus4150.ctrlRelay(3, false, new MdBus4150RelayListener() {
                                @Override
                                public void onCtrl(boolean b) {
                                    Log.e(TAG, "烟雾成功关闭"+xin[1] );
                                }

                                @Override
                                public void onFail(Exception e) {
                                    Log.e(TAG, "烟雾关闭失败-----------------"+xin[1] );
                                }
                            });
                           // modbus4150.ctrlRelay(Integer.parseInt(message[6]), false, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            }
            super.handleMessage(msg);
        }
    };

    //  String[] name={"modbus IP地址","modbus 端口","LED IP地址","LED 端口","modbus 人体端子","modbus 烟雾端子","报警器端子"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onclick();
        handler.post(runnable);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            du();
            handler.postDelayed(runnable, 1000);
        }
    };

    private void du() {

        try {
            modbus4150.getDIVal(1, new MdBus4150SensorListener() {
                @Override
                public void onVal(int i) {
                    ren = 1;
                }

                @Override
                public void onFail(Exception e) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            modbus4150.getDIVal(0, new MdBus4150SensorListener() {
                @Override
                public void onVal(int i) {
                    yan = i;
                }

                @Override
                public void onFail(Exception e) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        Message message = new Message();
        message.obj = ren + "-" + yan;
        message.what = 1;
        handler.sendMessage(message);
        Log.e(TAG, "message"+ren+"----------"+yan );

    }


    private void init() {
        imageView = findViewById(R.id.imageView);
        textViewren = findViewById(R.id.tv_ren);
        textViewyan = findViewById(R.id.tv_yan);
        open();

    }

    void open() {
        t=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                t.setLanguage(Locale.CHINESE);
            }
        });
        modbus4150=new Modbus4150(DataBusFactory.newSerialDataBus(1,9600),null);
//        modbus4150 = new Modbus4150(DataBusFactory.newSocketDataBus(message[0], Integer.parseInt(message[1])), null);
//        ledScreen = new LedScreen(DataBusFactory.newSocketDataBus(message[2], Integer.parseInt(message[3])), null);

    }

    void close() {
        if (modbus4150 != null) {
            modbus4150.stopConnect();
        }
//        if (ledScreen != null) {
//            ledScreen.stopConnect();
//        }
    }

    private void onclick() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, shezhi.class);
                handler.removeCallbacks(runnable);
                close();
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        sharedPreferences=getSharedPreferences("qian",MODE_PRIVATE);
        for (int i = 0; i < message.length; i++) {
            message[i] = sharedPreferences.getString(""+i, "null");
        }
        open();
        handler.post(runnable);
        super.onActivityResult(requestCode, resultCode, data);
    }
}

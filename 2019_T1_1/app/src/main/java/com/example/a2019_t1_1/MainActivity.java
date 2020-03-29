package com.example.a2019_t1_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.lang.reflect.Method;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button A, B, C, DU;
    TextToSpeech textToSpeech;
    EditText Name, Khao, Money;
    SharedPreferences sharedPreferences;
    int com = 4;
    String keA="客人A";
    String keB="客人B";
    String keC="客人C";
    boolean k = true;
    RFID rfid;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message message) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (message.what) {
                case 1:
                    com = 4;
                    editor.putString(message.obj.toString().trim(), "-客户A-24-");
                    editor.commit();
                    Toast.makeText(MainActivity.this, "已绑定客人A", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    com = 4;
                    editor.putString(message.obj.toString().trim(), "-客人B-27-");
                    editor.commit();
                    Toast.makeText(MainActivity.this, "已绑定客人B", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    com = 4;
                    editor.putString(message.obj.toString().trim(), "-客人C-29");
                    editor.commit();
                    Toast.makeText(MainActivity.this, "已绑定客人C", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    if (sharedPreferences.getString(message.obj.toString().trim(), "null") != "null") {
                        String[] a = sharedPreferences.getString(message.obj.toString().trim(), "null").split("-");
                        Khao.setText(message.obj.toString().trim());
                        Name.setText(a[1]);
                        Money.setText(a[2]);
                        textToSpeech.speak(a[0]+"消费金额"+a[1],TextToSpeech.LANG_AVAILABLE,null);
                    } else {
                        Toast.makeText(MainActivity.this, "未绑定，请进行绑定", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            //return false;
            super.handleMessage(message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onclick();
        Speech();

    }

    private void Speech() {
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                textToSpeech.setLanguage(Locale.CHINESE);
            }
        });
        try {
            rfid.readSingleEpc(new SingleEpcListener() {
                @Override
                public void onVal(String s) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            duqu();
            handler.postDelayed(runnable, 500);

        }
    };

    private void duqu() {
        try {
            rfid.readSingleEpc(new SingleEpcListener() {
                @Override
                public void onVal(String val) {
                    Message message = new Message();
                    message.obj = val;
                    message.what = com;
                    handler.sendMessage(message);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onclick() {
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com = 1;
                duqu();
            }
        });
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com = 2;
                duqu();
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com = 3;
                duqu();
            }
        });
        DU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (k) {
                    DU.setText("停止读取");
                    handler.post(runnable);
                    k = false;
                } else {
                    DU.setText("开始读取");
                    handler.removeCallbacks(runnable);
                    k = true;
                }
            }
        });

    }

    private void init() {
        A = findViewById(R.id.buttonA);
        B = findViewById(R.id.button2B);
        C = findViewById(R.id.buttonC);
        DU = findViewById(R.id.button4);
        Name = findViewById(R.id.editTextxingming);
        Khao = findViewById(R.id.editTextKahao);
        Money = findViewById(R.id.editTextxiaofei);
        rfid = new RFID(DataBusFactory.newSerialDataBus(2,115200));
        sharedPreferences = getSharedPreferences("zhangqian", MODE_PRIVATE);

    }

    @Override
    protected void onDestroy() {
        System.exit(0);
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        System.exit(0);
        super.onStop();
    }
}

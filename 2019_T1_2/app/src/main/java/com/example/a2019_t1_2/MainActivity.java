package com.example.a2019_t1_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    RFID rfid;
    TextToSpeech textToSpeech;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (msg.what) {
                case 0:
                    editor.putString(msg.obj.toString().trim(), "客人A-24");
                    editor.commit();
                    textToSpeech.speak("客人A消费24元", TextToSpeech.LANG_AVAILABLE, null);
                    Toast.makeText(MainActivity.this, "客人A消费24元", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    editor.putString(msg.obj.toString().trim(), "客人B-30");
                    editor.commit();
                    textToSpeech.speak("客人B消费30元", TextToSpeech.LANG_AVAILABLE, null);
                    Toast.makeText(MainActivity.this, "客人B消费30元", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    editor.putString(msg.obj.toString().trim(), "客人C-27");
                    editor.commit();
                    textToSpeech.speak("客人C消费27元", TextToSpeech.LANG_AVAILABLE, null);
                    Toast.makeText(MainActivity.this, "客人C消费27元", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    EditText money, name, Khao;
    SharedPreferences sharedPreferences;
    Button bA, bB, bC, du_bt;
    int com = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        speak();
        onclick();

    }




    private void onclick() {
        bA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com = 0;
                duqu();
            }
        });
        bB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com = 1;
                duqu();
            }
        });
        bC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com = 2;
                duqu();
            }
        });
        du_bt.setOnClickListener(new View.OnClickListener() {
            boolean a = true;
            @Override
            public void onClick(View view) {

                if (a) {
                    handler.post(runnable);
                    du_bt.setText("关闭读取");
                    a = false;
                } else {
                    handler.removeCallbacks(runnable);
                    du_bt.setText("开始读取");
                    a = true;
                }

            }
        });
    }

    private void speak() {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.CHINA);
                }
            }
        });
    }

    private void duqu() {
        try {
            rfid.readSingleEpc(new SingleEpcListener() {
                @Override
                public void onVal(String val) {
                    Message message = Message.obtain();
                    message.what = com;
                    message.obj = val;
                    handler.sendMessage(message);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void init() {
        money = findViewById(R.id.money_et);
        name = findViewById(R.id.name_et);
        Khao = findViewById(R.id.kahao_et);
        bA = findViewById(R.id.A_bt);
        bB = findViewById(R.id.keB_bt);
        bC = findViewById(R.id.keC_bt);
        du_bt = findViewById(R.id.du_bt);

        sharedPreferences = getSharedPreferences("zq", MODE_PRIVATE);
        rfid = new RFID(DataBusFactory.newSocketDataBus("15.5.1.45", 952));


    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            duqu();
            handler.postDelayed(runnable, 1000);

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

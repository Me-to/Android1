package com.example.a2019_t1_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.service.autofill.OnClickAction;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button a, b, c, d;
    TextView ka, name, money;
    boolean e = true;
    RFID rfid;
    String t="a";
    TextToSpeech textToSpeech;
    String TAG="--------------";
    int com = 4;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (msg.what) {
                case 1:
                    Log.e(TAG, "1" );
                    editor.putString(msg.obj.toString(), "客人A-24-");
                    editor.commit();
                    com=4;
                    Toast.makeText(MainActivity.this, "客人A绑定完成", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Log.e(TAG, "2" );
                    editor.putString(msg.obj.toString(), "客人B-27-");
                    editor.commit();
                    com=4;
                    Toast.makeText(MainActivity.this, "客人B绑定完成", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    editor.putString(msg.obj.toString(), "客人C-30-");
                    editor.commit();
                    Log.e(TAG, "3" );
                    com=4;
                    Toast.makeText(MainActivity.this, "客人C绑定完成", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    if (sharedPreferences.getString(msg.obj.toString(), "NUll") != "NULL") {
                        String[] a = sharedPreferences.getString(msg.obj.toString(), "null").split("-");
                        ka.setText(msg.obj.toString().trim());
                        name.setText(a[0]);
                        money.setText(a[1]);
                        Log.e(TAG, "4 true" );

                        if (!t.equals(a[0])) {
                            textToSpeech.speak(a[0] + "消费的金额为" + a[1], TextToSpeech.QUEUE_FLUSH, null);
                            t=a[0];
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "请进行绑定", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "4 false" );
                    }
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
        a = findViewById(R.id.buttonA);
        b = findViewById(R.id.buttonB);
        c = findViewById(R.id.buttonC);
        d = findViewById(R.id.buttonD);
        ka = findViewById(R.id.textViewkahao);
        name = findViewById(R.id.textViewname);
        sharedPreferences = getSharedPreferences("qian", MODE_PRIVATE);
        money = findViewById(R.id.textViewjine);
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                textToSpeech.setLanguage(Locale.CHINESE);
            }
        });
        rfid = new RFID(DataBusFactory.newSerialDataBus(1, 115200), null);
        onclick();

    }

    private void onclick() {
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com = 1;
                duqu();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com = 2;
                duqu();

            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com = 3;
                duqu();

            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e) {
                    com=4;
                    d.setText("关闭读取");
                    duqu();
                    e = false;
                    handler.removeCallbacks(runnable);
                } else {
                    com=4;
                    d.setText("开始读取");
                    duqu();
                    e = true;
                    handler.post(runnable);
                }
            }
        });


    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            duqu();
            handler.postDelayed(runnable, 1000);
        }
    };

    void duqu() {
        try {
            rfid.readSingleEpc(new SingleEpcListener() {
                @Override
                public void onVal(String s) {
                    Message message =new  Message();
                    message.what = com;
                    message.obj = s;
                    handler.sendMessage(message);
                }

                @Override
                public void onFail(Exception e) {

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}


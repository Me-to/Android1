package com.example.a2019_t1_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.util.IllegalFormatCodePointException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView ka, name, jine;
    RFID rfid;
    int com = 1;
    String TAG = "--------------------";
    TextToSpeech textToSpeech;
    String c;
    SharedPreferences sharedPreferences;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (msg.what) {
                case 1:
                    editor.putString("" + msg.obj.toString().trim(), "客人A-24");
                    editor.commit();
                    Log.e(TAG, "1 ");
                    com = 2;
                    Toast.makeText(MainActivity.this, "客人A绑定成功", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    editor.putString("" + msg.obj.toString().trim(), "客人B-30");
                    editor.commit();
                    com = 3;
                    Log.e(TAG, "2 ");
                    Toast.makeText(MainActivity.this, "客人B绑定成功", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    SharedPreferences.Editor editor2 = sharedPreferences.edit();
                    editor.putString("" + msg.obj.toString().trim(), "客人C-27");
                    editor.commit();
                    com = 4;
                    Log.e(TAG, "3 ");
                    Toast.makeText(MainActivity.this, "客人C绑定成功", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Log.e(TAG, "4 ");
                    editor.putString("a",""+4);
                    editor.commit();
                    handler.post(runnable);
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
        ka = findViewById(R.id.tvkahao);
        name = findViewById(R.id.tvname);
        jine = findViewById(R.id.tvjine);
        sharedPreferences = getSharedPreferences("qian", MODE_PRIVATE);
        rfid = new RFID(DataBusFactory.newSerialDataBus(2, 115200), null);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.CHINESE);
                }
            }
        });
//        if (sharedPreferences.getString("a","null").equals(4)){
//            handler.post(runnable);
//           name.setOnClickListener(null);
//        }
        name.setOnClickListener(v -> {
            try {
                rfid.readSingleEpc(new SingleEpcListener() {
                    @Override
                    public void onVal(String s) {
                        name.setText(s);
                        Message message = Message.obtain();
                        message.obj = s;
                        message.what = com;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFail(Exception e) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                rfid.readSingleEpc(new SingleEpcListener() {
                    @Override
                    public void onVal(String s) {
                        if (sharedPreferences.getString("" + s, "null") != null) {
                            String[] b = sharedPreferences.getString("" + s, "null").split("-");
                            ka.setText(s);
                            name.setText(b[0]);
                            jine.setText(b[1]);
                            if (!b[1].equals(c)) {
                                textToSpeech.speak("消费的金额为" + b[1], TextToSpeech.QUEUE_FLUSH, null);
                            }
                            c = b[1];
                        } else {
                            Toast.makeText(MainActivity.this, "未绑定，请绑定", Toast.LENGTH_SHORT).show();
                        }
                        handler.postDelayed(runnable, 500);
                    }

                    @Override
                    public void onFail(Exception e) {

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


}

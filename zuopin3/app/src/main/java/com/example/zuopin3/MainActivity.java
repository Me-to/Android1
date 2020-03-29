package com.example.zuopin3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.ConnectResultListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    String TAG = "------------";
    SharedPreferences sharedPreferences;
    RFID rfid;

    TextToSpeech textToSpeech;
    boolean one = true;


    String a;
    boolean i = false;
    ImageView imageViewzhu, imageViewmen, imageViewzhaopian;
    TextView tv_zhuce, tv_ka, tv_name, tv_zhiwu, tv_dianhua;
    EditText et_mima;
    Button btn_duka, btn_kaimen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        imageViewzhu = findViewById(R.id.imageView);
        tv_zhuce = findViewById(R.id.tv_zhuce);
        tv_dianhua = findViewById(R.id.tv_dianhua);
        tv_ka = findViewById(R.id.tv_kahao);
        tv_name = findViewById(R.id.tv_name);
        tv_zhiwu = findViewById(R.id.tv_zhiwu);
        imageViewmen = findViewById(R.id.imageViewmen);
        et_mima = findViewById(R.id.et_mima);
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        imageViewmen.setImageResource(R.drawable.door_1);
                }
                super.handleMessage(msg);
            }
        };

        btn_duka = findViewById(R.id.button2);
        btn_kaimen = findViewById(R.id.buttonkaimen);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i==TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.CHINESE);
                }else {
                    Log.e(TAG, "onInit: ------语音初始化失败" );
                }
            }
        });
        sharedPreferences = getSharedPreferences("qian", MODE_PRIVATE);
        rfid = new RFID(DataBusFactory.newSerialDataBus(1, 115200), new ConnectResultListener() {
            @Override
            public void onConnectResult(boolean b) {
                Log.e(TAG, "onConnectResult:" + b);
            }
        });
        onclick();
    }

    void tan() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        View view1 = View.inflate(getApplicationContext(), R.layout.tan, null);
        EditText et_name = view1.findViewById(R.id.tan_name);
        EditText et_zhiwei = view1.findViewById(R.id.tanet_zhiwei);
        EditText et_dianhua = view1.findViewById(R.id.tan_dianhua);
        EditText et_mima = view1.findViewById(R.id.tanet_mima);
        TextView textViewkahao = view1.findViewById(R.id.tan_kahao);
        Button button = view1.findViewById(R.id.buttonduka);
        ImageView imageView = view1.findViewById(R.id.tan_paizhao);
        Button btn_queding = view1.findViewById(R.id.buttonqueding);
        alertDialog.setView(view1);
        alertDialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    rfid.readSingleEpc(new SingleEpcListener() {
                        @Override
                        public void onVal(String s) {
                            textViewkahao.setText(s);
                        }

                        @Override
                        public void onFail(Exception e) {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btn_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (et_name.getText().equals("") && et_zhiwei.getText().equals("") && et_dianhua.getText().equals("") && et_mima.getText().equals("") && textViewkahao.equals("") && i) {
                    Toast.makeText(MainActivity.this, "信息填写不完整", Toast.LENGTH_SHORT).show();

                } else {
                    editor.putString("name", et_name.getText().toString().trim());
                    editor.putString("zhiwei", et_zhiwei.getText().toString().trim());
                    editor.putString("dianhua", et_dianhua.getText().toString().trim());
                    editor.putString("mima", et_mima.getText().toString().trim());
                    editor.putString("kahao", textViewkahao.getText().toString().trim());
                    editor.commit();
                    Toast.makeText(MainActivity.this, "注册完成", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            }
        });
    }

    void onclick() {
        btn_kaimen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (one) {
                    Toast.makeText(MainActivity.this, "未刷卡，请刷卡后进入", Toast.LENGTH_SHORT).show();
                    textToSpeech.setPitch(0.9f);
                    textToSpeech.speak("未刷卡，请刷卡输入密码后进入", TextToSpeech.LANG_AVAILABLE, null);
                    one = false;

                } else {
                    textToSpeech.setPitch(0.9f);
                    textToSpeech.speak("密码输入正确，门已开，请进入", TextToSpeech.LANG_AVAILABLE, null);
                    imageViewmen.setImageResource(R.drawable.kaimen);
                    AnimationDrawable animationDrawable = (AnimationDrawable) imageViewmen.getDrawable();
                    animationDrawable.start();
                    Toast.makeText(getApplicationContext(), "密码输入正确，请进入", Toast.LENGTH_SHORT).show();

                }
            }
        });
        tv_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tan();
            }
        });
        btn_duka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv_dianhua.setText("15706495621");
                tv_ka.setText("fd456ds348s22s361");
                tv_name.setText("张前");
                tv_zhiwu.setText("学生");
                et_mima.setText("               ");
                imageViewzhu.setImageResource(R.drawable.p7);


            }
        });

    }
}
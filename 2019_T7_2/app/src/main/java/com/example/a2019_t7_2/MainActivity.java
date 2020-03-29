package com.example.a2019_t7_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.nle.mylibrary.forUse.mdbus4150.Modbus4150;
import com.nle.mylibrary.forUse.zigbee.ZigBee;
import com.nle.mylibrary.transfer.DataBusFactory;

public class MainActivity extends AppCompatActivity {

    ZigBee zigBee;
    SharedPreferences sharedPreferences;
    Switch s1, s2;
    TextView wendu, shidu;
    ImageView feng, deng, shezi;
    //  {"zigbee IP地址", "ziggbee端口", "双联继电器系列号", "风扇联数", "照明灯联数", "温度通道号", "温度最高值", "温度最低值", "光照最高值", "光照最低值"};
    String[] message = new String[9];
    Handler handler;
    double val[] = new double[1];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("qian", MODE_PRIVATE);
        init();

    }

    //循环的进行读取操作
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            du();
            handler.postDelayed(runnable, 1000);
        }

    };


    void du() {
        try {
            val = zigBee.getTmpHum();
            double gu = zigBee.getLight();
            String guang = String.valueOf(gu).substring(0, 5);
            String shi = String.valueOf(val[1]).substring(0, 5);

            if (sharedPreferences.getString("a", "null").equals("2")) {

                if (val[1] > Integer.valueOf(message[6])) {
                    zigBee.ctrlDoubleRelay(Integer.valueOf(message[2]), Integer.valueOf(message[3]), false, null);
                }
                if (val[1] < Integer.valueOf(message[7])) {
                    zigBee.ctrlDoubleRelay(Integer.valueOf(message[2]), Integer.valueOf(message[3]), true, null);
                    shezi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
                }
                if (gu > Integer.valueOf(message[6])) {
                    zigBee.ctrlDoubleRelay(Integer.valueOf(message[2]), Integer.valueOf(message[4]), false, null);
                }
                if (gu < Integer.valueOf(message[6])) {
                    zigBee.ctrlDoubleRelay(Integer.valueOf(message[2]), Integer.valueOf(message[4]), true, null);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        for (int i = 0; i < message.length; i++) {
            message[i] = sharedPreferences.getString("" + i, "null");
        }
        open();
        handler.post(runnable);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init() {
        open();
        wendu = findViewById(R.id.tv_guangzhao);
        shidu = findViewById(R.id.tv_shidu);
        s1 = findViewById(R.id.switchfeng);
        s2 = findViewById(R.id.switchdeng);
        shezi = findViewById(R.id.imageViewshezhi);
        handler.post(runnable);
        onclick();
        handler = new Handler();

        for (int i = 0; i < message.length; i++) {
            message[i] = sharedPreferences.getString("" + i, "null");
        }

    }

    private void onclick() {
        shezi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, shezhi.class);
                handler.removeCallbacks(runnable);
                close();
                startActivityForResult(intent, 1);
            }
        });

        if (sharedPreferences.getString("a", "null").equals("1")) {
            s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        try {
                            zigBee.ctrlDoubleRelay(Integer.valueOf(message[2]), Integer.valueOf(message[4]), true, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            zigBee.ctrlDoubleRelay(Integer.valueOf(message[2]), Integer.valueOf(message[4]), false, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        try {
                            zigBee.ctrlDoubleRelay(Integer.valueOf(message[2]), Integer.valueOf(message[3]), true, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            zigBee.ctrlDoubleRelay(Integer.valueOf(message[2]), Integer.valueOf(message[3]), false, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    void open() {
        zigBee = new ZigBee(DataBusFactory.newSocketDataBus(message[0], Integer.valueOf(message[1])), null);
    }

    void close() {
        if (zigBee != null) {
            zigBee.stopConnect();
        }
    }

}

package com.example.t_14;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.nle.mylibrary.forUse.zigbee.FourChannelValConvert;
import com.nle.mylibrary.forUse.zigbee.ZigBee;
import com.nle.mylibrary.transfer.DataBusFactory;

//22
public class MainActivity extends AppCompatActivity {

    TextView wen, shi, kong, guang;
    ZigBee zigBee;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    String[] a = msg.obj.toString().split("-");
                    wen.setText("温度：" + a[0] + " C");
                    shi.setText("湿度：" + a[1] + " %rh");
                    kong.setText("空气质量：" + a[2] + " ");
                    guang.setText("光照：" + a[3]);
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
        wen = findViewById(R.id.tv1_wen);
        shi = findViewById(R.id.tv1_shi);
        kong = findViewById(R.id.tv1_kong);
        guang = findViewById(R.id.tv1_guang);
        zigBee = new ZigBee(DataBusFactory.newSerialDataBus(1, 9600), null);
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

        double[] val = new double[4];
        try {

            val = zigBee.getFourEnter();
            double wendu = FourChannelValConvert.getTemperature(val[0]);
            double shidu = FourChannelValConvert.getHumidity(val[1]);
            double kongqi = FourChannelValConvert.getCO2(val[2]);
            double guangzhao = FourChannelValConvert.getLight(val[3]);
            String wen_ = String.valueOf(wendu).substring(0, 5);
            String shi_ = String.valueOf(shidu).substring(0, 5);
            String kong_ = String.valueOf(kongqi).substring(0, 5);
            String guang_ = String.valueOf(guangzhao).substring(0, 5);
            Message message = new Message();
            message.what = 1;
            message.obj = wen_ + "-" + shi_ + "-" + kong_ + "-" + guang_;
            handler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void du1() {
    double[] val=new double[2];
        try {
            val=zigBee.getTmpHum();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

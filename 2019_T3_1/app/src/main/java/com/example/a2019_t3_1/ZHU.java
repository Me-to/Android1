package com.example.a2019_t3_1;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nle.mylibrary.forUse.zigbee.FourChannelValConvert;
import com.nle.mylibrary.forUse.zigbee.Zigbee;
import com.nle.mylibrary.forUse.zigbee.ZigbeeControlListener;
import com.nle.mylibrary.transfer.DataBusFactory;

public class ZHU extends AppCompatActivity {
    boolean b=true;
    Zigbee zigbee;
    double[] vals;
    double CO2;
    Handler handler=new Handler();
    Button btn_co2;
    ImageView imagev_ferg,imagev_dengpao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhujiemain);
     init();

 onclick();
 handler.postDelayed(runnable,500);
    }

    private void onclick() {
        imagev_dengpao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b) {
                    try {
                        zigbee.ctrlDoubleRelay(1, 1, true, new ZigbeeControlListener() {
                            @Override
                            public void onCtrl(boolean isSuccess) {
                                Toast.makeText(ZHU.this, "打开灯泡", Toast.LENGTH_SHORT).show();
                                b=false;
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        zigbee.ctrlDoubleRelay(1, 1, false, new ZigbeeControlListener() {
                            @Override
                            public void onCtrl(boolean isSuccess) {
                                Toast.makeText(ZHU.this, "关闭灯泡", Toast.LENGTH_SHORT).show();
                                b=true;
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void init() {
        btn_co2=findViewById(R.id.button_co2);
        imagev_ferg=findViewById(R.id.imagev_feng);
        imagev_dengpao=findViewById(R.id.imagev_deng);

    }


    private void huoqu() {
        zigbee = new Zigbee(DataBusFactory.newSocketDataBus("156.456.782", 38400));
        try {
            vals = zigbee.getFourEnter();
            // 四通道Val转换
            CO2 = FourChannelValConvert.getCO2(vals[1]);
          btn_co2.setText("CO2: "+CO2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
              huoqu();
              handler.postDelayed(runnable,500);

        }
    };
}

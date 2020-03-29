package com.example.t_15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import cn.com.newland.nle_sdk.requestEntity.SignIn;
import cn.com.newland.nle_sdk.responseEntity.SensorInfo;
import cn.com.newland.nle_sdk.responseEntity.User;
import cn.com.newland.nle_sdk.responseEntity.base.BaseResponseEntity;
import cn.com.newland.nle_sdk.util.NCallBack;
import cn.com.newland.nle_sdk.util.NetWorkBusiness;

public class MainActivity extends AppCompatActivity {

    String sheID = "";
    String zhixingqi = "sa4fd5646sd";
    String chuankong = "z_kong";
    String chuanco = "z_co";
    String name = "zhang";
    String mima = "12345678";
    String tock = null;
    String api = "http://nlecloud.com";
    Button bt_kong, bt_co;
    TextView tv_kong, tv_co;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        yun();
        onclick();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            message();
            handler.postDelayed(runnable, 1000);
        }
    };

    private void message() {
        if (tock != null) {
            NetWorkBusiness netWorkBusiness = new NetWorkBusiness(tock, api);
            netWorkBusiness.getSensors(sheID, chuanco + "," + chuankong, new NCallBack<BaseResponseEntity<List<SensorInfo>>>(getApplicationContext()) {
                @Override
                protected void onResponse(BaseResponseEntity<List<SensorInfo>> listBaseResponseEntity) {

                    List<SensorInfo> list = listBaseResponseEntity.getResultObj();
                    for (SensorInfo sensorInfo : list) {
                        if (sensorInfo.getApiTag().equals(chuanco)) {
                            tv_co.setText(sensorInfo.getValue() + sensorInfo.getUnit());
                        }
                        if (sensorInfo.getApiTag().equals(chuankong)) {
                            tv_kong.setText(sensorInfo.getValue() + sensorInfo.getUnit());
                        }
                    }
                }
            });
        }
    }

    private void onclick() {
        bt_kong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorkBusiness netWorkBusiness = new NetWorkBusiness(tock, api);
                netWorkBusiness.control(sheID, zhixingqi, 0, new NCallBack<BaseResponseEntity>(getApplicationContext()) {
                    @Override
                    protected void onResponse(BaseResponseEntity baseResponseEntity) {

                    }
                });
            }
        });
        bt_co.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorkBusiness netWorkBusiness = new NetWorkBusiness(tock, api);
                netWorkBusiness.control(sheID, zhixingqi, 1, new NCallBack<BaseResponseEntity>(getApplicationContext()) {
                    @Override
                    protected void onResponse(BaseResponseEntity baseResponseEntity) {

                    }
                });
            }
        });
    }

    private void yun() {
        NetWorkBusiness netWorkBusiness = new NetWorkBusiness(sheID, api);
        netWorkBusiness.signIn(new SignIn(name, mima), new NCallBack<BaseResponseEntity<User>>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<User> userBaseResponseEntity) {
                if (userBaseResponseEntity.getStatus() == 0) {
                    tock = userBaseResponseEntity.getResultObj().getAccessToken();
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        bt_co = findViewById(R.id.button2);
        bt_kong = findViewById(R.id.bt_kong);
        tv_co = findViewById(R.id.tv_co);
        tv_kong = findViewById(R.id.tv_kong);
    }
}

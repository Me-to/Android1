package com.example.t_15;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cn.com.newland.nle_sdk.requestEntity.SignIn;
import cn.com.newland.nle_sdk.responseEntity.SensorInfo;
import cn.com.newland.nle_sdk.responseEntity.User;
import cn.com.newland.nle_sdk.responseEntity.base.BaseResponseEntity;
import cn.com.newland.nle_sdk.util.NCallBack;
import cn.com.newland.nle_sdk.util.NetWorkBusiness;


public class a extends AppCompatActivity {
    String sheID="123";
    String zhixingqi="qwqe";
    String chaunco="a";
    String chaunkong="b";
    String name="65656";
    String mima="adsafsdfa";
    String api="http://nlecloud.com";
    String tock=null;
    Button bt_kong,bt_co;
    TextView tv_kong,tv_co;
    Handler handler=new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        yun();
        onclick();
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            message();
            handler.postDelayed(runnable,1000);
        }
    };
    private void message(){
        NetWorkBusiness netWorkBusiness=new NetWorkBusiness(tock,api);
        netWorkBusiness.getSensors(sheID, chaunco + "," + chaunkong, new NCallBack<BaseResponseEntity<List<SensorInfo>>>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<List<SensorInfo>> listBaseResponseEntity) {
                List<SensorInfo> list=listBaseResponseEntity.getResultObj();
                for (SensorInfo sensorInfo:list){
                    if (sensorInfo.getApiTag().equals(chaunco)){
                        tv_kong.setText(sensorInfo.getValue()+sensorInfo.getUnit());
                    }
                    if (sensorInfo.getApiTag().equals(chaunkong)){
                        tv_co.setText(sensorInfo.getValue()+sensorInfo.getUnit());
                    }
                }
            }
        });
    }

    private void onclick() {
        bt_kong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorkBusiness netWorkBusiness=new NetWorkBusiness(tock,api);
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
                NetWorkBusiness netWorkBusiness=new NetWorkBusiness(tock,api);
                netWorkBusiness.control(sheID, zhixingqi, 1, new NCallBack<BaseResponseEntity>(getApplicationContext()) {
                    @Override
                    protected void onResponse(BaseResponseEntity baseResponseEntity) {

                    }
                });
            }
        });
    }

    private void yun() {
        NetWorkBusiness netWorkBusiness=new NetWorkBusiness(sheID,api);
        netWorkBusiness.signIn(new SignIn(name, mima), new NCallBack<BaseResponseEntity<User>>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<User> userBaseResponseEntity) {
                if (userBaseResponseEntity.getStatus()==0){
                    tock=userBaseResponseEntity.getResultObj().getAccessToken();
                    Toast.makeText(a.this, "登录成功", Toast.LENGTH_SHORT).show();
                    handler.post(runnable);
                }else {
                    Toast.makeText(a.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        tv_co=findViewById(R.id.tv_co);
        tv_kong=findViewById(R.id.tv_kong);
        bt_co=findViewById(R.id.button2);
        bt_kong=findViewById(R.id.bt_kong);

    }
}

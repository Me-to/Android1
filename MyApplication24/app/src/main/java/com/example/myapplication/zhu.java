package com.example.myapplication;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nle.mylibrary.forUse.mdbus4150.MdBus4150RelayListener;
import com.nle.mylibrary.forUse.mdbus4150.Modbus4150;
import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

public class zhu extends AppCompatActivity {
    RFID rfid;
    Modbus4150 modbus4150;
    AnimationDrawable animationDrawable;
    SharedPreferences sharedPreferences;
    Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donghau);
        init();
    }

    private void init() {
        rfid=new RFID(DataBusFactory.newSerialDataBus("12.12.54.",952));
        sharedPreferences=getSharedPreferences("safd",MODE_PRIVATE);
         modbus4150=new Modbus4150(DataBusFactory.newSerialDataBus(3,951));
        handler=new Handler();
        handler.post(runnable);
    }
    Runnable  runnable=new Runnable(){

        @Override
        public void run() {
            try {
                rfid.readSingleEpc(new SingleEpcListener() {
                    @Override
                    public void onVal(String val) {
                        //用equals（val）还是     用等于号进行比较
                          if (sharedPreferences.getString(val,"null")==val)  {
                              Toast.makeText(zhu.this, "打开设备", Toast.LENGTH_SHORT).show();
                              animationDrawable.start();
                              try {
                                  new Thread().sleep(30000);
                                  modbus4150.openRelay(6, new MdBus4150RelayListener() {
                                      @Override
                                      public void onCtrl(boolean isSuccess) {

                                      }
                                  });
                              } catch (InterruptedException e) {
                                  e.printStackTrace();
                              } catch (Exception e) {
                                  e.printStackTrace();
                              }
                          }else if (!sharedPreferences.getString(val,"null").equals(val)){

                          }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.postDelayed(runnable,1000);
        }
    };

    @Override
    protected void onDestroy() {
        if (rfid!=null){
            rfid.stopConnect();
        }
        super.onDestroy();
    }
}

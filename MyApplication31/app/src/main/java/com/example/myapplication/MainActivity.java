package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.nle.mylibrary.forUse.zigbee.Zigbee;
import com.nle.mylibrary.forUse.zigbee.ZigbeeControlListener;

public class MainActivity extends AppCompatActivity {

    Zigbee zigbee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            zigbee=zigbee.ctrlDoubleRelay(1, 3, true, new ZigbeeControlListener() {
                @Override
                public void onCtrl(boolean isSuccess) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("---", "onCreate: 出问题了");
        }
    }

//    ALT+回车

}

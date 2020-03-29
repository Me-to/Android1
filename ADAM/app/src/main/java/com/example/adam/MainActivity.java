package com.example.adam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.nle.mylibrary.forUse.mdbus4150.MdBus4150RelayListener;
import com.nle.mylibrary.forUse.mdbus4150.Modbus4150;
import com.nle.mylibrary.transfer.ConnectResultListener;
import com.nle.mylibrary.transfer.DataBusFactory;

public class MainActivity extends AppCompatActivity {
String TAG="----------";
    Modbus4150 modbus4150;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        modbus4150=new Modbus4150(DataBusFactory.newSerialDataBus(1, 9600), new ConnectResultListener() {
            @Override
            public void onConnectResult(boolean b) {
                Log.d(TAG, "onConnectResult: "+b);
            }
        });

        try {
            modbus4150.ctrlRelay(1, true, new MdBus4150RelayListener() {
                @Override
                public void onCtrl(boolean b) {
                    Log.d(TAG, "onCtrl: 1------"+b);
                }

                @Override
                public void onFail(Exception e) {
                    Log.d(TAG, "1 异常");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            modbus4150.ctrlRelay(2, true, new MdBus4150RelayListener() {
                @Override
                public void onCtrl(boolean b) {
                    Log.d(TAG, "onCtrl: 2------"+b);
                }

                @Override
                public void onFail(Exception e) {
                    Log.d(TAG, "2  异常");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

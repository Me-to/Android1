package com.example.myapplication1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
    //    throw new UnsupportedOperationException("Not yet implemented");
        String number = getResultData();
        if(number.startsWith("0")) {
            SharedPreferences sp = context.getSharedPreferences("ip",
                    Context.MODE_PRIVATE);
            String ipNumber = sp.getString("ipNumber", "");
            //把IP线路号码添加至用户拨打号码的前面
            number = ipNumber + number;
            //把新的号码重新放入广播中
            setResultData(number);
            abortBroadcast();

        }
        }
}

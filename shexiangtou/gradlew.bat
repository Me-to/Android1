package com.example.a2019_t1_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.lang.reflect.Method;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button A, B, C, DU;
    TextToSpeech textToSpeech;
    EditText Name, Khao, Money;
    SharedPreferences sharedPreferences;
    int com = 4;
    String keA="客人A";
    String keB="客人B";
    String keC="客人C";
    boolean k = true;
    RFID rfid;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message message) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (message.what) {
                case 1:
                    com = 4;
                    editor.putString(message.obj.toString().trim(), "-客户A-24-");
                    editor.commit();
                    Toast.makeText(MainActivity.this, "已绑定客人A", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    com = 4;
                    editor.putString(message.obj.toString().trim(), "-客人B-27-");
                    editor.commit();
                    Toast.makeText(MainActivity.this, "已绑定客人B", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    com = 4;
                    editor.putString(message.obj.toString().trim(), "-客人C-29");
                    editor.commit();
                    Toast.makeText(MainActivity.this, "已绑定客人C", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    if (sharedPreferences.getStr
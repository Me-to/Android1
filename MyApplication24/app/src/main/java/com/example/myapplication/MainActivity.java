package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.text.DateFormat;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button buttonduqu, buttonqueding;
    TextView textView;
    RFID rfid;
    String ka;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        buttonduqu = findViewById(R.id.button);
        buttonqueding = findViewById(R.id.button2);
        textView = findViewById(R.id.textView3);
        sharedPreferences = getSharedPreferences("zhangqian", MODE_PRIVATE);
        rfid = new RFID(DataBusFactory.newSerialDataBus("10.15.15.12", 952));
        onclick();
    }

    private void onclick() {
        buttonduqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rfid != null) {
                    try {
                        rfid.readSingleEpc(new SingleEpcListener() {
                            @Override
                            public void onVal(String val) {
                                textView.setText(val);
                                ka = val;
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        buttonqueding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(ka, ka);
                editor.commit();

            }
        });
    }

    @Override
    protected void onDestroy() {
        if (rfid != null) {
            rfid.stopConnect();
        }
        super.onDestroy();
    }
}

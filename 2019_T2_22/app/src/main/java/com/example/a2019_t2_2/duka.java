package com.example.a2019_t2_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nle.mylibrary.forUse.mdbus4150.Modbus4150;
import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

public class duka extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Button b1, b2;
    String a;
    TextView textView;
    RFID rfid;
    ImageView imageView,imageViewq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duka);
        init();
    }

    private void init() {
        textView = findViewById(R.id.dutextViewkahao);
        b1 = findViewById(R.id.dubutton1);
        b2 = findViewById(R.id.dubutton2);
        sharedPreferences = getSharedPreferences("qian", MODE_PRIVATE);
        imageView = findViewById(R.id.imaaaa);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        open();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        onclick();

    }

    private void onclick() {

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    rfid.readSingleEpc(new SingleEpcListener() {
                        @Override
                        public void onVal(String s) {
                            a = s;
                            textView.setText(s);
                            Toast.makeText(duka.this, "读取成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFail(Exception e) {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(a, a);
                editor.commit();
                Toast.makeText(duka.this, "写入成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        close();
        super.onStop();
    }

    void open() {
        rfid=new RFID(DataBusFactory.newSerialDataBus(2,115200),null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        open();
        super.onActivityResult(requestCode, resultCode, data);
    }



    void close() {
        if (rfid != null) {
            rfid.stopConnect();
        }
    }
    protected void onDestroy() {
        close();
        super.onDestroy();
    }
}

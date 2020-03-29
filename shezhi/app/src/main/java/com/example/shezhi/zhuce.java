package com.example.shezhi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

public class zhuce extends AppCompatActivity {
    RFID rfid;
    Button btn_queding,btn_duqu;
    TextView textView;
    SharedPreferences sharedPreferences;
    String kahao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
      init();
        onlick();
    }

    private void onlick() {
        btn_duqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    rfid.readSingleEpc(new SingleEpcListener() {
                        @Override
                        public void onVal(String val) {
                            kahao=val;
                            textView.setText(kahao);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btn_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /***********************不知道怎么添加数据****************************/
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(kahao,kahao);
                editor.commit();
            }
        });
    }

    private void init() {
btn_queding=findViewById(R.id.btn_queding);
btn_duqu=findViewById(R.id.btn_duqu);
textView=findViewById(R.id.tv_kaohao);
sharedPreferences=getSharedPreferences("zhangqian", Context.MODE_PRIVATE);
rfid=new RFID(DataBusFactory.newSocketDataBus("132.168.0.200",115200));
    }

    @Override
    protected void onDestroy() {
        if (rfid!=null){
            rfid.stopConnect();
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        if (rfid!=null){
            r
        }
        super.onStop();
    }
}

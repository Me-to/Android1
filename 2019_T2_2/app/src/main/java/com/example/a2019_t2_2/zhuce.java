package com.example.a2019_t2_2;

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
    Button btn_que,btn_duqu;
    TextView textView;
    RFID rfid;
    String b;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        init();
        duqu();
    }

    private void duqu() {
        btn_duqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /************************  每次使用的时候都要给他初始化IP和信息  ***************************/
                open();
                /************************  判断是否为空  ***************************/
                if (rfid != null) {
                    try {
                        rfid.readSingleEpc(new SingleEpcListener() {
                            @Override
                            public void onVal(String val) {
                                b = val;
                                textView.setText(val);
                            }
                        });
                        btn_que.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String b = textView.getText().toString();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(b, b);
                                editor.commit();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void init() {
        btn_duqu=findViewById(R.id.btn_zhuce_du);
        btn_que=findViewById(R.id.btn_zhuce_que);
        textView=findViewById(R.id.textView3);

        sharedPreferences=getSharedPreferences("zhangqian",MODE_PRIVATE);
        open();
    }
    private void open(){
        rfid=new RFID(DataBusFactory.newSocketDataBus("192.168.782",953));
    }
/***********   这些内容忘了打  *************/
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
            rfid.stopConnect();
        }
        super.onStop();
    }
}

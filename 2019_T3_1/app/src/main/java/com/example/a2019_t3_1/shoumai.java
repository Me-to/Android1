package com.example.a2019_t3_1;

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

public class shoumai extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    RFID rfid;
    String a;
    Button btn_du,btn_jihuo;
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoumai);
        intit();
        onclick();
    }


    private void intit() {
         textView=findViewById(R.id.tv_kahaoa);
         btn_du=findViewById(R.id.button_du);
         btn_jihuo=findViewById(R.id.button_zhu);
         sharedPreferences=getSharedPreferences("zhangqian",MODE_PRIVATE);
         chu();
    }
    private void chu(){
        rfid=new RFID(DataBusFactory.newSocketDataBus("10.13.14.16",952));
    }
    private void onclick(){
        btn_du.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                duqu();
            }
        });
        btn_jihuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(a,a);
                editor.commit();
            }
        });
    }
    private void duqu(){
        chu();
        if (rfid!=null){
            try {

                rfid.readSingleEpc(new SingleEpcListener() {
                    @Override
                    public void onVal(String val) {
                        a=val;
                         textView.setText(a);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

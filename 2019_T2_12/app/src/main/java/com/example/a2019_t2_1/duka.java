package com.example.a2019_t2_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

public class duka extends AppCompatActivity {
SharedPreferences sharedPreferences;
RFID rfid;
String a;
String TAG="---------";
Button button1,button2;
TextView textView;
ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duka);
        init();
    }
void open(){
        if (rfid== null){
            rfid=new RFID(DataBusFactory.newSocketDataBus("172.16.7.16",952),null);
        }



}
    void close(){
        if (rfid!= null){
            rfid=new RFID(DataBusFactory.newSocketDataBus("172.16.7.16",952),null);
        }
    }
    private void init() {
        textView=findViewById(R.id.dukahao);
        button1=findViewById(R.id.dubuttonduqu);
        button2=findViewById(R.id.dubutton2jihuo);
        open();
        imageView=findViewById(R.id.jihuoimageView4);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu  popupMenu=new PopupMenu(duka.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.zhu:
                                Intent intent2=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent2);
                                popupMenu.dismiss();
                                close();
                                break;
                            case R.id.jihuo:
                                break;
                            case R.id.shezhi:
                                Intent intent1=new Intent(getApplicationContext(),shezhi.class);
                                startActivity(intent1);
                                popupMenu.dismiss();
                                close();
                                break;
                        }
                        return false;
                    }
                });
            }
        });
        sharedPreferences=getSharedPreferences("zhangqian",MODE_PRIVATE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    rfid.readSingleEpc(new SingleEpcListener() {
                        @Override
                        public void onVal(String s) {
                            a=s;
                            textView.setText("票号："+a);
                            Log.e(TAG, a);

                        }

                        @Override
                        public void onFail(Exception e) {

                        }
                    });
                } catch (Exception e) {


                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(a,a);
                editor.commit();
                Toast.makeText(duka.this, "激活成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

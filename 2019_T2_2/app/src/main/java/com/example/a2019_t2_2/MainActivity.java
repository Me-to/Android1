package com.example.a2019_t2_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.nle.mylibrary.forUse.mdbus4150.Modbus4150;
import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
Handler handler=new Handler();
RFID rfid;
Modbus4150 modbus4150;
String r;
String[] strMessage=new String[7];
ImageView imageView;
    private PopupMenu popupMenu1;
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        rfid = new RFID(DataBusFactory.newSocketDataBus("192.456.23",953));
        modbus4150=new Modbus4150(DataBusFactory.newSocketDataBus("789.65.45",895));
        imageView=findViewById(R.id.im_door);
        imageView.setImageResource(R.drawable.open_door);
        imageView.setImageResource(R.drawable.close_door);
        AnimationDrawable animationDrawable= (AnimationDrawable) imageView.getDrawable();
        AnimationDrawable animationDrawable1= (AnimationDrawable) imageView.getDrawable();
    }

    @NonNull

Runnable runnable=new Runnable() {
    @Override
    public void run() {
        duqu();
        handler.postDelayed(runnable,500);

    }
};

    private void duqu() {

        try {
            rfid.readSingleEpc(new SingleEpcListener() {
                @Override
                public void onVal(String val) {
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("rfidSocket",val);
                    editor.commit();
                   if (sharedPreferences.getString(val,"NULL").equals(val)){
                              modbus4150.closeRelay(Integer.parseInt(strMessage[5],null));
                   }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_shezhi:
                Intent a=new Intent(this,shezhi.class);
                startActivity(a);
                break;
            case R.id.menu_zhuce:
                Intent b=new Intent(this,zhuce.class);
                startActivity(b);

                break;
                default:
                    break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if (modbus4150!=null){
            modbus4150.stopConnect();
        }
        if (rfid!=null){
            rfid.stopConnect();
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        if (modbus4150!=null){
            modbus4150.stopConnect();
        }
        if (rfid!=null){
            rfid.stopConnect();
        }
        super.onStop();
    }
}


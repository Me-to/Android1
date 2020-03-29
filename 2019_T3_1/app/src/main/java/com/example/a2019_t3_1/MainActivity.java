package com.example.a2019_t3_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    RFID rfid;
    Handler handler = new Handler();
    shoumai shoumai1 = new shoumai();
    String b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rfid = new RFID(DataBusFactory.newSocketDataBus("10.13.14.16", 952));
        sharedPreferences = getSharedPreferences("zhangqian", MODE_PRIVATE);
        duqu();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            duqu();
            handler.postDelayed(runnable, 500);
        }
    };

    private void chu() {

    }

    private void duqu() {
//        chu();
//        if (rfid != null) {
//            rfid.stopConnect();
//        } else {
            try {
                chu();
                rfid.readSingleEpc(new SingleEpcListener() {
                    @Override
                    public void onVal(String val) {
                        b = val;
                    }
                });
                if (sharedPreferences.getString(shoumai1.a, "null").equals(b)) {
                    Toast.makeText(this, "刷票成功，即将进入主界面", Toast.LENGTH_SHORT).show();
                    //打开闸门
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            //关闭闸门
                            Intent intent = new Intent(MainActivity.this, ZHU.class);
                            startActivity(intent);
                        }
                    }, 3000);
                }
            } catch (Exception e) {
                e.printStackTrace();
//            }
        }
        handler.postDelayed(runnable, 500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_shezhi:
                Intent a = new Intent(this, shezhi.class);
                startActivity(a);
                break;
            case R.id.menu_zhuye:
                Intent b = new Intent(this, ZHU.class);
                startActivity(b);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}

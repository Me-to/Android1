package com.example.a2019_t2_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.nle.mylibrary.forUse.mdbus4150.Modbus4150;
import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    RFID rfid;
    Modbus4150 modbus4150;
    Handler handler;
    ImageView imageView,imageViewkai,imageViewguan;
   AnimationDrawable animation,animation1;
    String a = "1";
    String[] message = new String[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {

        sharedPreferences = getSharedPreferences("qian", MODE_PRIVATE);
        for (int i = 0; i < message.length; i++) {
            message[i] = sharedPreferences.getString("" + i, "null");
        }
        imageViewguan=findViewById(R.id.imageViewguan);
        imageViewkai=findViewById(R.id.imageViewkai);
        animation1= (AnimationDrawable) imageViewguan.getDrawable();
animation= (AnimationDrawable) imageViewkai.getDrawable();
        // modbus4150=new Modbus4150(DataBusFactory.newSerialDataBus(2,9600),null);
        open();
        handler = new Handler();
        imageView = findViewById(R.id.imageViewzhucai);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.tiao1:
                                Intent intent = new Intent(MainActivity.this, duka.class);
                                handler.removeCallbacks(runnable);
                                close();
                                startActivityForResult(intent, 1);
                                break;
                            case R.id.taio2:
                                Intent intent1 = new Intent(MainActivity.this, shezhi.class);
                                handler.removeCallbacks(runnable);
                                close();
                                startActivityForResult(intent1, 1);
                        }
                        ;
                        return false;
                    }
                });
            }
        });
        handler.post(runnable);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        open();
        handler.post(runnable);

        super.onActivityResult(requestCode, resultCode, data);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            du();
            handler.postDelayed(runnable, 500);

        }
    };


    void du() {
        try {
            rfid.readSingleEpc(new SingleEpcListener() {
                @Override
                public void onVal(String s) {
                    if (!sharedPreferences.getString(s, "null").equals("null")) {

                        if (!a.equals(sharedPreferences.getString(s, "null"))) {

                            try {
                                Toast.makeText(MainActivity.this,
                                        "卡号正确", Toast.LENGTH_SHORT).show();
                                Log.e("---------------", "1");
                               animation.start();

                                //+
                                // .3modbus4150.ctrlRelay(5,true,null);
                                TimerTask timerTask = new TimerTask() {
                                    @Override
                                    public void run() {

                                    }
                                };
                                Timer timer = new Timer();
                                timer.schedule(timerTask, 3000);
                                animation1.start();
                                // modbus4150.ctrlRelay(5,false,null);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            a = sharedPreferences.getString(s, "null");
                        } else {
                            Log.e("---------------", "2");
                        }

                    } else {
                        try {
//                          modbus4150.ctrlRelay(6,true,null);
                            TimerTask timerTask = new TimerTask() {
                                @Override
                                public void run() {

                                }
                            };
                            Timer timer = new Timer();
                            timer.schedule(timerTask, 3000);
                            //         modbus4150.ctrlRelay(6,false,null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }

                @Override
                public void onFail(Exception e) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void open() {
//        if (modbus4150==null){
//            modbus4150=new Modbus4150(DataBusFactory.newSerialDataBus(2,9600),null);
//        }
        rfid = new RFID(DataBusFactory.newSerialDataBus(2, 115200), null);

    }

    void close() {
//        if (modbus4150!=null){
//            modbus4150.stopConnect();
//        }
        rfid.stopConnect();
        a = "1";
    }

    @Override
    protected void onDestroy() {
        close();
        super.onDestroy();
    }


}

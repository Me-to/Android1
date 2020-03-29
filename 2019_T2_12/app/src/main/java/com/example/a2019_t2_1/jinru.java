package com.example.a2019_t2_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nle.mylibrary.forUse.zigbee.FourChannelValConvert;
import com.nle.mylibrary.forUse.zigbee.ZigBee;
import com.nle.mylibrary.transfer.ConnectResultListener;
import com.nle.mylibrary.transfer.DataBusFactory;

public class jinru extends AppCompatActivity {
    ZigBee zigBee;
    SharedPreferences sharedPreferences;
    String[] a = new String[11];

    RotateAnimation animation;
    ImageView imageView;
    Handler handler;
    String[] s = new String[12];

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jinru);
        init();
//        animation = (RotateAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.drawable.xuanzhuang);
//        animation.setDuration(300);
//        ImageView imageView = null;
//        imageView.startAnimation(animation);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            du();
            handler.postDelayed(runnable, 500);
        }
    };

    void open() {
        if (zigBee == null) {
            zigBee = new ZigBee(DataBusFactory.newSocketDataBus("172.16.7.16", 950), null);
        }
    }

    private void init() {
        open();
        sharedPreferences = getSharedPreferences("zhangqian", MODE_PRIVATE);
        for (int i = 0; i < a.length; i++) {
            a[i] = sharedPreferences.getString("" + i, "null");
        }
        imageView = findViewById(R.id.jinruimageView5);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(jinru.this, view);
                getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.zhu:
                                Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent3);
                                popupMenu.dismiss();
                                break;
                            case R.id.jihuo:
                                Intent intent = new Intent(getApplicationContext(), duka.class);
                                startActivity(intent);
                                popupMenu.dismiss();
                                break;
                            case R.id.shezhi:
                                Intent intent1 = new Intent(getApplicationContext(), shezhi.class);
                                startActivity(intent1);
                                popupMenu.dismiss();
                                break;
                        }
                        return false;
                    }
                });
            }
        });
        handler.post(runnable);
    }

    void du() {
        double[] vals = new double[4];
        try {
            vals = zigBee.getFourEnter();
            double co = FourChannelValConvert.getCO2(vals[3]);
            if (co > 20) {
                zigBee.ctrlDoubleRelay(4, 0, true, null);
            }else {
                zigBee.ctrlDoubleRelay(4,0,false,null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

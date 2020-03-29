package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.logging.SimpleFormatter;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {
//
//    SimpleDateFormat simpleDateFormat;
//    double q=33,w=3;
//    String e="33";
//    String r="3";
//    String[] a = {"8:21", "10:15"};


    String TAG = "--------";
    Handler handler = new Handler();
    ImageView imageView;
    @SuppressLint("ResourceType")
    RotateAnimation rotateAnimation;
    RadioButton radioButton1, radioButton2,radioButton3,radioButton4,radioButton5,radioButton6,radioButton7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void fangfa(int id) {
        if (R.id.radioButton1 != id && radioButton1.isChecked()) {
            radioButton1.setChecked(false);
            Log.e(TAG, "进入的事1" + radioButton1.isChecked());
        }
        if (R.id.radioButton2 != id && radioButton2.isChecked()) {
            radioButton2.setChecked(false);
            Log.e(TAG, "进入的事2" + radioButton2.isChecked());
        }
    }
    void init() {
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    fangfa(compoundButton.getId());
                }
            }
        });
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    fangfa(compoundButton.getId());
                }
            }
        });





        radioButton3=findViewById(R.id.radioButton3);
        radioButton4=findViewById(R.id.radioButton4);
        radioButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                radioButton3.setChecked(true);
            }
        });
        radioButton4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                radioButton4.setChecked(true);
            }

        });
 radioButton5=findViewById(R.id.radioButton5);
 radioButton6=findViewById(R.id.radioButton6);
 radioButton7=findViewById(R.id.radioButton7);
 radioButton5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
     @Override
     public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
         Log.e(TAG, "1 ");
     }
 });
 radioButton6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
     @Override
     public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
         Log.e(TAG, "2 ");
     }
 });
radioButton7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }
});

    }
//    @SuppressLint("ResourceType")
//    private void init() {
//        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.aada);
//        rotateAnimation.setRepeatCount(-1);
//        rotateAnimation.setDuration(300);
//      imageView=findViewById(R.id.imageView);
//      imageView.setAnimation(rotateAnimation);
//      imageView.startAnimation(rotateAnimation);
//    //  imageView.startAnimation(rotateAnimation);


}


//    private void init() {
//
//        simpleDateFormat=new SimpleDateFormat("HH:mm");
//        String b=simpleDateFormat.format(new Date());
//        try {
//            Date start=simpleDateFormat.parse(a[0]);
//            Date end=simpleDateFormat.parse(a[1]);
//            Date now =simpleDateFormat.parse(b);
//
//
//
//            Log.e(TAG, "q"+Double.valueOf(q));
//            Log.e(TAG, "w"+Double.valueOf(w));
//            Log.e(TAG, "q.parset"+Double.parseDouble(e));
//            Log.e(TAG, "r.parset"+Double.parseDouble(r));
//            Log.e(TAG, "---------------------------------" );
//
//            Log.e(TAG, "start"+start);
//            Log.e(TAG, "end"+end);
//            Log.e(TAG, "now"+now);
//            Log.e(TAG, "---------------------------------" );
//
//            Log.e(TAG, "start"+start.getTime());
//            Log.e(TAG, "end"+end.getTime() );
//            Log.e(TAG, "now"+now.getTime() );
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
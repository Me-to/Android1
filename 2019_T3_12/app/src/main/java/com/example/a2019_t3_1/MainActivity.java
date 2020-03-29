package com.example.a2019_t3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;

import com.nle.mylibrary.forUse.mdbus4150.MdBus4150RelayListener;
import com.nle.mylibrary.forUse.mdbus4150.Modbus4150;
import com.nle.mylibrary.forUse.zigbee.Zigbee;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    TextView textView;
    SharedPreferences sharedPreferences;
    Zigbee zigbee;
    Modbus4150 modbus4150;
    Switch aSwitch;
    Handler handler;
    double light;
    boolean k;
   Button button;
   SimpleDateFormat simpleDateFormat;
    String[] neirong = {"192.168.0.200", "952", "192.168.0.200", "2001", "0", "1", "1", "08:42", "10:32", "33", "3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        speak();
        menu();
    }

    private void menu() {
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               PopupMenu popupMenu=new PopupMenu(MainActivity.this,view);
               getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
               popupMenu.show();
               popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem menuItem) {
                       switch (menuItem.getItemId()){
                           case R.id.a:
                               Intent intent=new Intent(MainActivity.this,SHEZHI.class);
                               startActivity(intent);
                               break;

                       }
                       return false;
                   }
               });
           }
       });
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            zigb();
          handler.postDelayed(runnable,500);
        }
    };

    private void speak() {
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                  textToSpeech.setLanguage(Locale.CHINA);
            }
        });
    }

    private void init() {
        textView=findViewById(R.id.tv_guangzhao);
        aSwitch=findViewById(R.id.switchkaguan);
        zigbee=new Zigbee(DataBusFactory.newSocketDataBus("45.52.58.55",951));
        modbus4150=new Modbus4150(DataBusFactory.newSocketDataBus("dsad",956));
        sharedPreferences=getSharedPreferences("zhangqian",MODE_PRIVATE);
        handler=new Handler();
        button=findViewById(R.id.button2);

    }
   void time (){
        simpleDateFormat=new SimpleDateFormat("HH:mm");
        String a=simpleDateFormat.format(new Date());
       try {
           Date start=simpleDateFormat.parse(neirong[7]);
           Date end =simpleDateFormat.parse(neirong[8]);
           Date now =simpleDateFormat.parse(a);

           if (start.getTime()>=now.getTime()&&now.getTime()<end.getTime()){
               k=true;
           }else {
               k=false;
           }
       } catch (ParseException e) {
           e.printStackTrace();
       }
   }
    private void zigb(){
        try {
            double light=zigbee.getLight();
            textView.setText(light+"lx");
         if (k){
             if (light>Double.valueOf(neirong[9])) {
                 modbus4150.openRelay(5, new MdBus4150RelayListener() {
                     @Override
                     public void onCtrl(boolean isSuccess) {

                     }
                 });
             }
         }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

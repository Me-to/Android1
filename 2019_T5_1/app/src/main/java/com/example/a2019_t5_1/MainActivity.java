package com.example.a2019_t5_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nle.mylibrary.forUse.zigbee.FourChannelValConvert;
import com.nle.mylibrary.forUse.zigbee.Zigbee;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Zigbee zigbee;
    TextToSpeech textToSpeech;
    SharedPreferences sharedPreferences;
    TextView shi,wen;
    Button button;
    String[] a=new String[4];
 Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
    void close(){
        if (zigbee!=null){
            zigbee.stopConnect();
        }
        handler.removeCallbacks(runnable);
    }
    void open(){

        zigbee=new Zigbee(DataBusFactory.newSocketDataBus("172.16.7.16",951));

    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            du();
                handler.postDelayed(runnable,5000);

        }
    };

    private void du() {

        try {
//            double [] vals=zigbee.getTmpHum();
//            double tem=vals[0];
//            double hum=vals[1];
            if(zigbee.getFourEnter()!=null){
                double[] vals=zigbee.getFourEnter();
                double hum= FourChannelValConvert.getHumidity(vals[1]);
                double tem=FourChannelValConvert.getTemperature(vals[0]);
                //加小数点只有5位
                String tem_s =String.valueOf(tem).substring(0,5);
                String hum_s=String.valueOf(hum).substring(0,5);
                wen.setText(""+tem_s);
                shi.setText(""+hum_s);
             //   textToSpeech.speak("wendu"+tem_s+"度湿度为"+hum_s,TextToSpeech.QUEUE_ADD,null);
               textToSpeech.speak("Temperature"+tem_s+"Humidity"+hum_s,TextToSpeech.QUEUE_ADD,null);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        open();
        handler.post(runnable);
    }

    private void init() {

       open();
      sharedPreferences=getSharedPreferences("zhangqian",MODE_PRIVATE);
      for(int i=0;i<a.length;i++){
        a[i]= sharedPreferences.getString(""+i,null);

      }
      shi=findViewById(R.id.tv_shi);
      wen=findViewById(R.id.tv_wen);
      button=findViewById(R.id.button111);
      textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
          @Override
          public void onInit(int i) {
              if (i==TextToSpeech.SUCCESS){
                  textToSpeech.setLanguage(Locale.US);
              }
          }
      });
        handler.post(runnable);
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(MainActivity.this,shezhi.class);
             startActivityForResult(intent,1);
              close();
          }
      });




    }



}

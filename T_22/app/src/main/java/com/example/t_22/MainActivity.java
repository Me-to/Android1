package com.example.t_22;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.RFIDReadListener;
import com.nle.mylibrary.forUse.rfid.RFIDWriteListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RFID rfid;
    SharedPreferences sharedPreferences;
    TextToSpeech textToSpeech;
    Button b1, b2, b3, b5, b7, b9, b6, b8, b10, b12, b16, b18, b20, bqueding;
    EditText editText;
    SimpleDateFormat simpleDateFormat;
    Date start,end;
boolean a=true;
String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                textToSpeech.setLanguage(Locale.CHINESE);
            }
        })
        rfid = new RFID(DataBusFactory.newSocketDataBus("192.168.1", 951), null);
        sharedPreferences = getSharedPreferences("A", MODE_PRIVATE);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b10 = findViewById(R.id.button10);
        b12 = findViewById(R.id.button12);
        b16 = findViewById(R.id.button16);
        b18 = findViewById(R.id.button18);
        b20 = findViewById(R.id.button20);
        bqueding = findViewById(R.id.bt_quding);
        editText = findViewById(R.id.editText);


    }

    void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:

             
                if(a){
                    if (editText.length()!=16){
                        Toast.makeText(this, "请输入正确的长度", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    writ();
                    try {
                        start=simpleDateFormat.parse(new String(simpleDateFormat.format(new Date())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    b1.setBackgroundColor(Color.parseColor("#F44336"));
                    a=false;
                }else {

                }
                break;
            case R.id.button2:
                b2.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            case R.id.button3:
                b3.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            case R.id.button5:
                b5.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            case R.id.button6:
                b6.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            case R.id.button7:
                b7.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            case R.id.button8:
                b8.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            case R.id.button9:
                b9.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            case R.id.button10:
                b10.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            case R.id.button12:
                b12.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            case R.id.button16:
                b16.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            case R.id.button18:
                b18.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            case R.id.button20:
                b20.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            case R.id.bt_quding:
                editText.setText(sharedPreferences.getString("8301",null));
            default:
                break;


        }
    }

    void writ() {

        try {
            rfid.writeData("8301", new RFIDWriteListener() {
                @Override
                public void onResult(boolean b) {

                }

                @Override
                public void onFail(Exception e) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("8301",editText.getText().toString());
        editor.apply();
    }
    void read(){
        try {
            rfid.readData(new RFIDReadListener() {
                @Override
                public void onResult(String s) {
                    if (s.equals("8301")){

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
  void   tan(){
      AlertDialog alertDialog=new AlertDialog.Builder(this).create();
      View view=View.inflate(getApplicationContext(),R.layout.tan,null);
      TextView shen,jin,shi;
      alertDialog.setView(view);
      alertDialog.show();
      Button button=view.findViewById(R.id.button);
      try {
          end=simpleDateFormat.parse(new String(simpleDateFormat.format(new Date())));
      } catch (ParseException e) {
          e.printStackTrace();
      }
      long  a=(end.getTime()-start.getTime())*10;
      int b=Integer.valueOf((int) a);
      shen=view.findViewById(R.id.tan_shen);
      jin=findViewById(R.id.tan_jin);
      shi=findViewById(R.id.tan_shi);
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
textToSpeech.speak("金额为"+b,TextToSpeech.QUEUE_FLUSH,null);

          }
      });


    }
}

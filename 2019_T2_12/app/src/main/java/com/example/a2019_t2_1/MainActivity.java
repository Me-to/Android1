package com.example.a2019_t2_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.ConnectResultListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    String TAG="-------------";
    SharedPreferences sharedPreferences;
    RFID rfid;
    TextView textView;
    Handler handler=new Handler();
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        imageView=findViewById(R.id.zhuimageView4);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu=new PopupMenu(getApplicationContext(),view);
                popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.zhu:
                                popupMenu.dismiss();
                                break;
                            case R.id.jihuo:
                                Intent intent=new Intent(getApplicationContext(),duka.class);
                                startActivityForResult(intent,1);
                                popupMenu.dismiss();
                                handler.removeCallbacks(runnable);
                                close();
                                break;
                            case R.id.shezhi:
                                Intent intent1=new Intent(getApplicationContext(),shezhi.class);
                             startActivityForResult(intent1,1);
                                popupMenu.dismiss();
                                handler.removeCallbacks(runnable);
                                close();
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
            try {
                rfid.readSingleEpc(new SingleEpcListener() {
                    @Override
                    public void onVal(String s) {
                        Log.e(TAG,s );
                        if(sharedPreferences.getString(s,"null")!="null"){
                            textView.setText(""+s);
                            Toast.makeText(MainActivity.this, "刷票成功进入主界面", Toast.LENGTH_SHORT).show();
                            TimerTask timerTask=new TimerTask() {
                                @Override
                                public void run() {

                                }
                            };
                            Timer timer=new Timer();
                            timer.schedule(timerTask,3000);

                                Intent intent=new Intent(MainActivity.this,jinru.class);
                                startActivityForResult(intent,1);
                                close();

                        }
                    }

                    @Override
                    public void onFail(Exception e) {

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.postDelayed(runnable,500);
        }
    };

    private void init() {
        sharedPreferences=getSharedPreferences("zhangqian",MODE_PRIVATE);
       open();
        textView=findViewById(R.id.zhutextView);
        handler.post(runnable);
    }
    void close(){
        if (rfid!=null){
            rfid.stopConnect();
        }
    }
    void open(){
        if (rfid==null){
            rfid=new RFID(DataBusFactory.newSocketDataBus("172.16.7.16", 952), new ConnectResultListener() {
                @Override
                public void onConnectResult(boolean b) {

                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        open();
        handler.post(runnable);
    }
}

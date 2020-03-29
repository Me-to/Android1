package com.example.a2019_t12_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nle.mylibrary.forUse.rfid.RFID;
import com.nle.mylibrary.forUse.rfid.SingleEpcListener;
import com.nle.mylibrary.transfer.DataBusFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Currency;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    EditText mima;
    TextView name, zhiwu, dianhua, kahao, zhuce;
    ImageView photo;
    Button kaimen, duka;
    RFID rfid;
    MySqliteHelper mySqliteHelper;
    String path;
    View view;
     String da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onclick();
    }

    private void onclick() {
        duka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    rfid.readSingleEpc(new SingleEpcListener() {
                        @Override
                        public void onVal(String val) {
                            kahao.setText(val);
                            /**************************************************************/
                            SQLiteDatabase sqLiteDatabase = mySqliteHelper.getWritableDatabase();
                            Cursor cursor = sqLiteDatabase.query("person", null, null, null, null, null, null);
                            while (cursor.moveToNext()) {
                                if (val.equals(cursor.getString(3))) {
                                    name.setText(cursor.getString(1));
                                    photo.setImageBitmap(BitmapFactory.decodeFile(cursor.getString(2)));
                                    dianhua.setText(cursor.getString(4));
                                    zhiwu.setText(cursor.getString(6));
                                    da=cursor.getString(5);
                                    sqLiteDatabase.close();
                                } else {
                                    Toast.makeText(MainActivity.this, "未注册，请注册！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tan();

            }
        });
        kaimen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mima.getText().toString().equals(da)){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent=new Intent(MainActivity.this,a.class);
           startActivity(intent);
                }
            }
        });
    }

    private void tan() {

        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        TextView name, zhiwei, dainhua, mima, kaohao;
        ImageView imageView;
        Button duka, zhuce;

        name = view.findViewById(R.id.tan_name);
        zhiwei = view.findViewById(R.id.tan_zhiwei);
        dainhua = view.findViewById(R.id.tan_dianhua);
        mima = view.findViewById(R.id.tan_mima);
        kaohao = view.findViewById(R.id.tan_kahao);
        zhuce = view.findViewById(R.id.btn_zhuce);
        imageView = view.findViewById(R.id.tan_im);
        duka=view.findViewById(R.id.btn_tanduka);
        duka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    rfid.readSingleEpc(new SingleEpcListener() {
                        @Override
                        public void onVal(String val) {
                                     kaohao.setText(val);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uri = Uri.fromFile(new File(path));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, 1);
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           if (name.getText()!=null&&zhiwei.getText()!=null&&dainhua.getText()!=null&&mima.getText()!=null&&kaohao.getText()!=null){
               SQLiteDatabase sqLiteDatabase=mySqliteHelper.getWritableDatabase();
               ContentValues values=new ContentValues();
               values.put("name",name.getText().toString());
               values.put("icon_path",path);
               values.put("job",zhiwei.getText().toString());
               values.put("card_number",kaohao.getText().toString());
               values.put("password",mima.getText().toString());
               sqLiteDatabase.insert("person",null,values);
               sqLiteDatabase.close();

           }
                ad.setView(view);
                ad.show();
            }

        });

    }

    private void init() {
        kahao = findViewById(R.id.tv_kahao);
        photo = findViewById(R.id.im_zhaopian);
        name = findViewById(R.id.tv_namae);
        zhiwu = findViewById(R.id.tv_zhiwu);
        dianhua = findViewById(R.id.tv_dainhua);
        mima = findViewById(R.id.et_mima);
        kaimen = findViewById(R.id.btn_kaimen);
        zhuce = findViewById(R.id.tv_zhuce);
        duka = findViewById(R.id.duka);
        mySqliteHelper = new MySqliteHelper(this);
        view = View.inflate(getApplicationContext(),R.layout.tan,null);
        path = Environment.getExternalStorageDirectory().getPath();
        path = path + "/add.jpg";
        rfid = new RFID(DataBusFactory.newSocketDataBus("192.168.45.52", 952));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                try {
                    FileInputStream fis = new FileInputStream(new File(path));
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    ImageView imageView =view.findViewById(R.id.tan_im);
                    imageView.setImageBitmap(bitmap);
                    photo.setImageBitmap(bitmap);
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }

    }
}

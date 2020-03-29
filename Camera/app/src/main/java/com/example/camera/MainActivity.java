package com.example.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
final static int REQ_1=1;
final static int REQ_2=2;
 Button btn_1,btn_2;
 String path;
 ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_1=findViewById(R.id.btn_1);
        btn_2=findViewById(R.id.btn_2);
        imageView=findViewById(R.id.imageView);
        path= Environment.getExternalStorageDirectory().getPath();
        path=path+"/temp.jpg";
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(intent,REQ_1);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uri=Uri.fromFile(new File(path));
                //把系统默认路径更改成这个路径，
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                  startActivityForResult(intent,REQ_2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==REQ_1){
                Bundle bundle=data.getExtras();
                Bitmap bitmap= (Bitmap) bundle.get("data");
                imageView.setImageBitmap(bitmap);
            }else if (requestCode==REQ_2){
                FileInputStream fis = null;
                try {
                     fis=new FileInputStream(path);
                    Bitmap bitmap= BitmapFactory.decodeStream(fis);
                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        if (fis!=null){
                            fis.close();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}

package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Comment;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;

public class MainActivity extends AppCompatActivity {
    String TAG = "--------";
    Button get, post;

    String URL = "http://10.0.2.2:9102";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d);
        get = findViewById(R.id.buttonget);
        post = findViewById(R.id.buttonpost);

        inti();

    }

    private void inti() {
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //要有客户端，就类似与一个浏览器
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(10000, TimeUnit.MILLISECONDS)
                        .build();
                //创建一个连接，请求内容
                final Request request = new Request.Builder()
                        .get()
                        .url(URL + "/get/text")
                        .build();
                //用client去创建请求任务
                Call task= okHttpClient.newCall(request);
                //创建异步请求
                task.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.e(TAG, "onFailure: "+"失败"+e.toString() );
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        int code=response.code();
                        Log.e(TAG, "onResponse: "+"成功" );
                     ResponseBody body=response.body();
                        Log.e(TAG, "onResponse: "+"body"+body.string() );


                    }
                });

            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个客户端
                OkHttpClient okHttpClient=new OkHttpClient.Builder()
                        .connectTimeout(10000,TimeUnit.MILLISECONDS)
                        .build();
                //创建一个连接进行发送
                Comment commentItem=new Comment("2546456123213","你的东西成功了");

                RequestBody requestBody=RequestBody.create()
                Request request=new Request.Builder()
                        .post()
                        .url((URL+"/post/comment")
                        .build();
            }
        });
    }

}

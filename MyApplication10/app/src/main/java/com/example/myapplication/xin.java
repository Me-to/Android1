package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class xin extends AppCompatActivity {
    ProgressBar progressBar;
    MyAsncy myAsncy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xin);
        init();
    }

    private void init() {
        progressBar=findViewById(R.id.PB);
    myAsncy=new MyAsncy();
    myAsncy.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myAsncy!=null&&myAsncy.getStatus()==AsyncTask.Status.RUNNING){
            myAsncy.cancel(true);
        }
    }

    class MyAsncy extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
     for (int i=0;i<99;i++){
         if (isCancelled()){
             break;
         }
         publishProgress(i);
     }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
    }

}

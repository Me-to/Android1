package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

public class jindutiao extends AppCompatActivity {
    ProgressBar progressBar;
    private MyAs myAs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jindutiao);
       init();
       myAs=new MyAs();
       myAs.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myAs!=null&&myAs.getStatus()==AsyncTask.Status.RUNNING){
            myAs.cancel(true);
        }
    }

    private void init() {
        progressBar=findViewById(R.id.progressBar);
    if (myAs!= null&&myAs.getStatus()==AsyncTask.Status.RUNNING){
        myAs.cancel(true);
    }
    }

    class MyAs extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i=0;i<100;i++){
                if (isCancelled()){
                    break;
                }
                publishProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

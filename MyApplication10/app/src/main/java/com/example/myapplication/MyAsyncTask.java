package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

public class MyAsyncTask extends AsyncTask<Void,Void,Void> {
static String TAG="--------------------";
    @Override
    protected void onPreExecute() {
        Log.e(TAG, "onPreExecute: 执行前");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.e(TAG, "onPostExecute: 执行后" );
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        Log.e(TAG, "onProgressUpdate: ");
        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.e(TAG, "doInBackground: ");
       publishProgress();
        return null;

    }

}

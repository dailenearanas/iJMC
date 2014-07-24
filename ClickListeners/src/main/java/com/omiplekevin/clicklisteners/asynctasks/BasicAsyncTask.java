package com.omiplekevin.clicklisteners.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by omiplekevin on 7/24/2014.
 */
public class BasicAsyncTask extends AsyncTask<Void, Void, Float>{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e("ASYNC TASK", "BEFORE DOING THE BACKGROUND PROCESS");
    }

    @Override
    protected Float doInBackground(Void... params) {
        for(int i=0;i<10;i++)
        {
            Log.e("ASYNC TASK","DOING IN BACKGROUND");
            publishProgress();
        }
        return 1.065f;
    }

    @Override
    protected void onPostExecute(Float aVoid) {
        super.onPostExecute(aVoid);
        Log.e("ASYNC TASK","AFTER DOING THE BACKGROUND PROCESS: " + aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.e("ASYNC TASK","UPDATE!");
    }
}

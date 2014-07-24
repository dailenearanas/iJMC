package com.omiplekevin.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by omiplekevin on 7/23/2014.
 */
public class AsyncTaskCountOneToHundred extends AsyncTask<Void, Void, Void>{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e("AsyncTask", "Before counting 1 to 100");
    }

    @Override
    protected Void doInBackground(Void... params) {
        for(int i=1;i<=100;i++)
        {
            Log.e("AsyncTask", "Counting "+i);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.e("AsyncTask","Doing this after counting 1 to 100");
    }
}

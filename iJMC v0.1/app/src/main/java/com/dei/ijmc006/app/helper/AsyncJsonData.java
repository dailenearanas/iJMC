package com.dei.ijmc006.app.helper;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import com.dei.ijmc006.app.model.ContentModel;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 9/12/2014.
 */
public class AsyncJsonData extends AsyncTask<String, Void, JSONObject> {
    SQLiteDatabase sqliteDB;
    DatabaseHandler dbHandler;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        //ArrayList<ContentModel> contentModel = Queries.getContents(sqliteDB, dbHandler);
        for (int i = 0; i < 20; i++) {
            if (isCancelled()) {
                break;
            } else {
                System.out.println(i);
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }
}

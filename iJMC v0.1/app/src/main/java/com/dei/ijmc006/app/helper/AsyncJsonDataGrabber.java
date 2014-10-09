package com.dei.ijmc006.app.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import com.dei.ijmc006.app.config.Config;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * iJMC0.0.6
 * Developer: Kevin Jimenez Omiple
 * Datestamp: 10/9/2014, 7:22 PM.
 *
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class AsyncJsonDataGrabber extends AsyncTask<Void, Void, Void> {

    ArrayList<String> urls;
    SQLiteDatabase sqliteDB;
    DatabaseHandler dbHandler;
    ServiceHandler handler;

    public AsyncJsonDataGrabber(Context context){
        this.urls = Config.getJsonUrls();
        dbHandler = new DatabaseHandler(context);
        handler = new ServiceHandler();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        for(String url : this.urls){
            String response = handler.makeServiceCall(url, ServiceHandler.GET);
            String jsonFile = url.substring(url.lastIndexOf('/')+1, url.length());
            if(jsonFile.equals(Config.CONTENT_JSON)){
                insertIntoContent(response);
            } else if(jsonFile.equals(Config.DEPARTMENT_JSON)){
                insertIntoDepartments(response);
            } else if(jsonFile.equals(Config.STUDENT_JSON)){
                insertIntoStudent(response);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    private void insertIntoContent(String response){
        Log.e("FUNCTION CONTENT", response);
        JSONObject jsonObject = stringToJSON(response);
        Iterator keys = jsonObject.keys();
        while(keys.hasNext()){

        }
    }

    private void insertIntoDepartments(String response){
        Log.e("FUNCTION DEPARTMENTS", response);
        JSONObject jsonObject = stringToJSON(response);
        Iterator keys = jsonObject.keys();
        while(keys.hasNext()){

        }
    }

    private void insertIntoStudent(String response){
        Log.e("FUNCTION STUDENT", response);
        JSONObject jsonObject = stringToJSON(response);
        Iterator keys = jsonObject.keys();
        while(keys.hasNext()){

        }
    }

    private JSONObject stringToJSON(String jsonStr){
        JSONObject object = null;
        try {
            object = new JSONObject(jsonStr);
        } catch(Exception e){
            Log.e("stringToJSON", e.getMessage().toString());
        }
        return object;
    }
}

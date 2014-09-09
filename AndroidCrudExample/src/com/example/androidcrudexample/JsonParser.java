package com.example.androidcrudexample;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class JsonParser extends ListActivity {
    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = "http://192.168.56.1/iJMC-WebApp/public/test/list.json";

    // JSON Node names
    private static final String TAG_CONTENT_ID = "idvjvh";
    private static final String TAG_CONTENT_TYPE = "content_type";
    private static final String TAG_CONTENT_BODY = "content_body";

    // contacts JSONArray
    JSONArray contents = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contentList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();

        // Listview on item click listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String contentId = ((TextView) view.findViewById(R.id.content_id))
                        .getText().toString();
                String contentTitle = ((TextView) view.findViewById(R.id.content_title))
                        .getText().toString();
                String contentBody = ((TextView) view.findViewById(R.id.content_body))
                        .getText().toString();

//                //Starting single contact activity
//                Intent in = new Intent(getApplicationContext(), SingleContactActivity.class);
//                in.putExtra(TAG_CONTENT_ID, contentId);
//                in.putExtra(TAG_CONTENT_TITLE, contentTitle);
//                in.putExtra(TAG_CONTENT_BODY, contentBody);
//                startActivity(in);
            }
        });

        // Calling async task to get json
        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(JsonParser.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            //String newJson = jsonStr.substring(1,jsonStr.length()-1);
            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try{
                    JSONArray jsonArray = new JSONArray(jsonStr);

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        Iterator it = object.keys();
                        while(it.hasNext()){
                            Log.e("OBJECT", object.getString(it.next().toString()));
                        }

                        String contentId = object.getString(TAG_CONTENT_ID);
                        String contentTitle = object.getString(TAG_CONTENT_TYPE);
                        String contentBody = object.getString(TAG_CONTENT_BODY);

                        HashMap<String, String> content = new HashMap<String, String>();

                        content.put(TAG_CONTENT_ID, contentId);
                        content.put(TAG_CONTENT_TYPE, contentTitle);
                        content.put(TAG_CONTENT_BODY, contentBody);

                        contentList.add(content);
                    }
                }
                catch (Exception e){
                    Log.e("Error: ", e.getMessage());
                }


            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             **/
            ListAdapter adapter = new SimpleAdapter(
                    JsonParser.this, contentList,
                    R.layout.list_item, new String[] { TAG_CONTENT_ID, TAG_CONTENT_TYPE,
                    TAG_CONTENT_BODY }, new int[] { R.id.content_id,
                    R.id.content_title, R.id.content_body });

            setListAdapter(adapter);
        }

    }
}

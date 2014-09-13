package com.dei.ijmc006.app.helper;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.dei.ijmc006.app.R;
import com.dei.ijmc006.app.config.Config;
import com.dei.ijmc006.app.model.ContentModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class JsonParser extends ListActivity {
    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = Config.JSON_URL + "/" + Config.CONTENT_JSON;

    // JSON Node names CONTENT
    private static final String TAG_CONTENT_TYPE = "content_type";
    private static final String TAG_CONTENT_BODY = "content_body";

    // contacts JSONArray
    JSONArray contents = null;

    // Hashmap for ListView
    ArrayList<ContentModel> contentList;

    SQLiteDatabase sqLiteDB;
    DatabaseHandler dbHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        contentList = new ArrayList<ContentModel>();

        ListView lv = getListView();

        dbHandler = new DatabaseHandler(this);
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
                            Log.e("OBJECT",object.getString(it.next().toString()));
                        }

                        String contentType = object.getString(TAG_CONTENT_TYPE);
                        String contentBody = object.getString(TAG_CONTENT_BODY);

                        ContentModel contentModel = new ContentModel();

                        contentModel.contentType = contentType;
                        contentModel.contentBody = contentBody;

                        Queries.InsertContent(sqLiteDB, dbHandler, contentModel);
                        contentList.add(contentModel);
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

            ArrayList<ContentModel> contentModels = Queries.getContents(sqLiteDB, dbHandler);
            ContentListAdapter adapter = new ContentListAdapter(contentModels);

            setListAdapter(adapter);
            /**
             * Updating parsed JSON data into ListView
             **/
        }

    }

    class ContentListAdapter extends BaseAdapter{
        ArrayList<ContentModel> contentModels;

        public ContentListAdapter(ArrayList<ContentModel> contentModels){
            this.contentModels = contentModels;
        }

        @Override
        public int getCount() {
            return contentList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ContentModel content = this.contentModels.get(position);

            LayoutInflater inflater = (LayoutInflater)JsonParser.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.list_item, null);

            TextView contentType = (TextView)view.findViewById(R.id.content_title);
            contentType.setText(content.contentType);

            TextView contentBody = (TextView)view.findViewById(R.id.content_body);
            contentBody.setText(content.contentBody);
            return view;
        }
    }
}

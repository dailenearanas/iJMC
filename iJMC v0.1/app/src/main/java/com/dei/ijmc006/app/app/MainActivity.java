package com.dei.ijmc006.app.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
//import android.content.Intent;
import com.dei.ijmc006.app.R;
import com.dei.ijmc006.app.config.Config;
import com.dei.ijmc006.app.helper.*;
import com.dei.ijmc006.app.model.ContentModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends ActionBarActivity {

    SharedPreferences sharedPref;
    SQLiteDatabase sqLiteDB;
    DatabaseHandler dbHandler;
    private Button registerBtn;
    private AlertDialog dialog;
    private ProgressDialog pDialog;
    ArrayList<ContentModel> contentList;

    AsyncJsonCheckID asyncJsonCheckID;
    private static String url = Config.JSON_URL + "/" + Config.CONTENT_JSON;

    private static final String TAG_CONTENT_TYPE = "content_type";
    private static final String TAG_CONTENT_BODY = "content_body";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentList = new ArrayList<ContentModel>();
        setContentView(R.layout.register_page);

        registerBtn = (Button) findViewById(R.id.register);

        dbHandler = new DatabaseHandler(this);

        final SharedPreferences.Editor prefEditor;
        Config config = new Config();
        sharedPref = this.getSharedPreferences("SETTINGS", MODE_PRIVATE);
        boolean hasLoggedIn = sharedPref.getBoolean(config.LOGIN, false);
        if (hasLoggedIn) {
            changeIntent();
            finish();

        } else {
            instantiateRegisterBtn();
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            dialog = builder.create();
            dialog.setTitle("Account Activation");
            dialog.setMessage("Student ID");

            // edit text
            final EditText input = new EditText(MainActivity.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            dialog.setView(input);

            dialog.setIcon(R.drawable.ic_launcher);
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Activate", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    asyncJsonCheckID = new AsyncJsonCheckID(MainActivity.this, sharedPref);
                    asyncJsonCheckID.execute(input.getText().toString());
                    //new GetContents().execute();

                }
            });

            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            });
        }
    }

    private void instantiateRegisterBtn() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    private void changeIntent() {
        Intent intent = new Intent(MainActivity.this, MainScreenActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Log.e("MAIN ACTIVITY", "SETTINGS");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class GetContents extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
//            pDialog = new ProgressDialog(MainActivity.this);
//            pDialog.setMessage("Please wait...");
//            pDialog.setCancelable(false);
//            pDialog.show();

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

//            ArrayList<ContentModel> contentModels = Queries.getContents(sqLiteDB, dbHandler);
//            ContentListAdapter adapter = new ContentListAdapter(contentModels);
//
//            setListAdapter(adapter);
        }

    }
}

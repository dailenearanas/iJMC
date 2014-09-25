package com.dei.ijmc006.app.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import com.dei.ijmc006.app.R;
import com.dei.ijmc006.app.config.Config;
import com.dei.ijmc006.app.fragments.MainMenuFragment;
import com.dei.ijmc006.app.fragments.NavigationDrawerFragment;
import com.dei.ijmc006.app.fragments.PlaceholderFragment;
import com.dei.ijmc006.app.helper.AsyncJsonCheckID;
import com.dei.ijmc006.app.helper.DatabaseHandler;
import com.dei.ijmc006.app.helper.Queries;
import com.dei.ijmc006.app.helper.ServiceHandler;
import com.dei.ijmc006.app.model.ContentModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


public class MainScreenActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    SQLiteDatabase sqLiteDB;
    DatabaseHandler dbHandler;

    private ProgressDialog pDialog;
    ArrayList<ContentModel> contentList;

    private static String url = Config.JSON_URL + "/" + Config.CONTENT_JSON;

    private static final String TAG_CONTENT_TYPE = "content_type";
    private static final String TAG_CONTENT_BODY = "content_body";


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainScreenActivity","START");

        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.container, new MainMenuFragment());
        fm.commit();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        contentList = new ArrayList<ContentModel>();

        //new GetContents().execute();

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        Config config = new Config();
        mTitle = config.getNavMenuItem(number-1);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            Log.e("MAIN FRAGMENT", "SETTINGS");
            SharedPreferences prefs = getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putBoolean("LOGIN", false);
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class GetContents extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainScreenActivity.this);
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

//            ArrayList<ContentModel> contentModels = Queries.getContents(sqLiteDB, dbHandler);
//            ContentListAdapter adapter = new ContentListAdapter(contentModels);
//
//            setListAdapter(adapter);
        }

    }
}

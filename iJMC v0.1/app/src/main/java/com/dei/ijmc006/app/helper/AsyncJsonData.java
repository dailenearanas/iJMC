package com.dei.ijmc006.app.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.dei.ijmc006.app.app.MainScreenActivity;
import com.dei.ijmc006.app.config.Config;
import com.dei.ijmc006.app.model.ContentModel;
import com.dei.ijmc006.app.model.FacultyModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by user on 9/12/2014.
 */
public class AsyncJsonData extends AsyncTask<Void, Void, Void> {
    SQLiteDatabase sqLiteDB;
    DatabaseHandler dbHandler;
    private ProgressDialog pDialog;
    ArrayList<ContentModel> contentList;
    ArrayList<FacultyModel> facultyList;
    Context context;

    private static String url = Config.JSON_URL + "/" + Config.CONTENT_JSON;

    public AsyncJsonData(Context context) {
        this.context = context;
        contentList = new ArrayList<ContentModel>();
        facultyList = new ArrayList<FacultyModel>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        dbHandler = new DatabaseHandler(context);
        contentList = new ArrayList<ContentModel>();
        pDialog = new ProgressDialog(this.context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        // Creating service handler class instance
        ServiceHandler sh = new ServiceHandler();

        Queries.TruncateTables(sqLiteDB, dbHandler);

        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
        //String newJson = jsonStr.substring(1,jsonStr.length()-1);
        Log.d("Response: ", "> " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonStr);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    Iterator it = object.keys();
                    while (it.hasNext()) {
                        Log.e("OBJECT", object.getString(it.next().toString()));
                    }

                    String contentId = object.getString(Config.TAG_CONTENT_ID);
                    String contentType = object.getString(Config.TAG_CONTENT_TYPE);
                    String contentBody = object.getString(Config.TAG_CONTENT_BODY);

                    ContentModel contentModel = new ContentModel();

                    contentModel.contentId = contentId;
                    contentModel.contentType = contentType;
                    contentModel.contentBody = contentBody;

                    Queries.InsertContent(sqLiteDB, dbHandler, contentModel);
                    contentList.add(contentModel);

                    String facultyId = object.getString(Config.TAG_FACULTY_ID);
                    String facultyFname = object.getString(Config.TAG_FACULTY_FNAME);
                    String facultyMname = object.getString(Config.TAG_FACULTY_MNAME);
                    String facultyLname = object.getString(Config.TAG_FACULTY_LNAME);
                    String facultySfx = object.getString(Config.TAG_FACULTY_SFX);
                    String facultyGender = object.getString(Config.TAG_FACULTY_GENDER);
                    String facultyImage = object.getString(Config.TAG_FACULTY_IMAGE);
                    String facultyDept = object.getString(Config.TAG_FACULTY_DEPT);
                    String facultyPos = object.getString(Config.TAG_FACULTY_POSITION);

                    FacultyModel facultyModel = new FacultyModel();

                    facultyModel.facultyId = facultyId;
                    facultyModel.facultyFname = facultyFname;
                    facultyModel.facultyMname = facultyMname;
                    facultyModel.facultyLname = facultyLname;
                    facultyModel.facultySfx = facultySfx;
                    facultyModel.facultyGender = facultyGender;
                    facultyModel.facultyImagePath = facultyImage;
                    facultyModel.facultyDeptId = facultyDept;
                    facultyModel.facultyPositionId = facultyPos;

                    Queries.InsertFaculty(sqLiteDB, dbHandler, facultyModel);
                    facultyList.add(facultyModel);
                }
            } catch (Exception e) {
                e.printStackTrace();
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
        /*if (pDialog.isShowing())*/
        dbHandler.close();
        pDialog.dismiss();
        Toast.makeText(this.context, "Activated", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, MainScreenActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
//            ArrayList<ContentModel> contentModels = Queries.getContents(sqLiteDB, dbHandler);
//            ContentListAdapter adapter = new ContentListAdapter(contentModels);
//
//            setListAdapter(adapter);
    }

}

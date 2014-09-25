package com.dei.ijmc006.app.helper;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import com.dei.ijmc006.app.app.MainActivity;
import com.dei.ijmc006.app.app.MainScreenActivity;
import com.dei.ijmc006.app.config.Config;
import com.dei.ijmc006.app.model.ContentModel;
import com.dei.ijmc006.app.model.StudentModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by user on 9/11/2014.
 */
public class AsyncJsonCheckID extends AsyncTask<String, Integer, Boolean>{
    SQLiteDatabase sqLiteDB;
    DatabaseHandler dbHandler;
    Context context;
    SharedPreferences sha;
    SharedPreferences.Editor shaEditor;

    public ProgressDialog dialog;

    private static String url = Config.JSON_URL + "/" + Config.STUDENT_JSON;

    private static final String TAG_STUD_ID = "stud_idnum";
    private static final String TAG_STUD_FNAME = "stud_fname";
    private static final String TAG_STUD_MNAME = "stud_mname";
    private static final String TAG_STUD_LNAME = "stud_lname";
    private static final String TAG_DEPT_ID = "dept_id";

    ArrayList<StudentModel> studentList;

    public AsyncJsonCheckID(Context context, SharedPreferences sha){
        this.context = context;
        this.sha = sha;
        this.shaEditor = sha.edit();
        dialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog.setIndeterminate(true);
        dialog.setTitle("Login");
        dialog.setMessage("Checking ID");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        // Creating service handler class instance
        ServiceHandler sh = new ServiceHandler();
        String studId = "";
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
                        studId = object.getString(TAG_STUD_ID);
                        String studFname = object.getString(TAG_STUD_FNAME);
                        String studMname = object.getString(TAG_STUD_MNAME);
                        String studLname = object.getString(TAG_STUD_LNAME);
                        Integer deptId = object.getInt(TAG_DEPT_ID);

                        StudentModel studentModel = new StudentModel();

                        studentModel.studId = studId;
                        studentModel.studentFname = studFname;
                        studentModel.studentMname = studMname;
                        studentModel.studentLname = studLname;
                        studentModel.deptId = deptId;

                        if(studId.equals(params[0])){
                                shaEditor.putString(Config.SHA_USR_ID,studentModel.studId);
                                shaEditor.putString(Config.SHA_USR_FNAME,studentModel.getStudentFname());
                                shaEditor.putString(Config.SHA_USR_MNAME,studentModel.getStudentMname());
                                shaEditor.putString(Config.SHA_USR_LNAME,studentModel.getStudentLname());
                                shaEditor.putInt(Config.SHA_USR_DEPT_ID,studentModel.getDeptId());
                                shaEditor.putBoolean("LOGIN", true);
                                shaEditor.commit();

                            return true;
                        }
                    }

                }
            }
            catch (Exception e){

            }

        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

        return false;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

   @Override
    protected void onPostExecute(Boolean studId) {
        super.onPostExecute(studId);
        if (dialog.isShowing())
            dialog.dismiss();

       //Toast.makeText(this.context, "THE RESULT IS " + studId, Toast.LENGTH_SHORT).show();
       if(studId) {
           AsyncJsonData asyncJsonData = new AsyncJsonData(context);
                            asyncJsonData.execute();
       }
    }
}

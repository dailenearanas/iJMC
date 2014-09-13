package info.androidhive.jsonparsing;

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
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by user on 9/12/2014.
 */
public class JsonStudents extends ListActivity{
    private ProgressDialog pDialog;

    // URL to get students JSON
    private static String url = Config.JSON_URL + "/" + Config.STUDENT_JSON;

    // JSON Node names STUDENT
    private static final String TAG_STUD_ID = "stud_idnum";
    private static final String TAG_STUD_FNAME = "stud_fname";
    private static final String TAG_STUD_MNAME = "stud_mname";
    private static final String TAG_STUD_LNAME = "stud_lname";
    private static final String TAG_DEPT_ID = "dept_id";

    ArrayList<StudentModel> studentList;

    SQLiteDatabase sqLiteDB;
    DatabaseHandler dbHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentList = new ArrayList<StudentModel>();

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
            pDialog = new ProgressDialog(JsonStudents.this);
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

                        String studId = object.getString(TAG_STUD_ID);
                        String studFname = object.getString(TAG_STUD_FNAME);
                        String studMname = object.getString(TAG_STUD_MNAME);
                        String studLname = object.getString(TAG_STUD_LNAME);
                        Integer deptId = object.getInt(TAG_DEPT_ID);

                        StudentModel studentModel = new StudentModel();

                        studentModel.studentId = studId;
                        studentModel.studentFname = studFname;
                        studentModel.studentMname = studMname;
                        studentModel.studentLname = studLname;
                        studentModel.deptId = deptId;

                        Queries.InsertStudents(sqLiteDB, dbHandler, studentModel);
                        studentList.add(studentModel);
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

            ArrayList<StudentModel> studentModels = Queries.getStudentID(sqLiteDB, dbHandler);
            StudentListAdapter adapter = new StudentListAdapter(studentModels);

            setListAdapter(adapter);
            /**
             * Updating parsed JSON data into ListView
             **/
        }

    }

    class StudentListAdapter extends BaseAdapter {
        ArrayList<StudentModel> studentModels;

        public StudentListAdapter(ArrayList<StudentModel> studentModels){
            this.studentModels = studentModels;
        }

        @Override
        public int getCount() {
            return studentList.size();
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
            StudentModel studentModel = this.studentModels.get(position);

            LayoutInflater inflater = (LayoutInflater)JsonStudents.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.list_item, null);

            TextView studentId = (TextView)view.findViewById(R.id.content_title);
            studentId.setText(studentModel.studentId);

            TextView studentFname = (TextView)view.findViewById(R.id.content_body);
            studentFname.setText(studentModel.studentFname);
            return view;
        }
    }
}

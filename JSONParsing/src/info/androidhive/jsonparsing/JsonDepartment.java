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
 * Created by user on 9/9/2014.
 */
public class JsonDepartment extends ListActivity{
    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = Config.JSON_URL + "/" + Config.DEPARTMENT_JSON;

    // JSON Node names DEPARTMENT
    private static final String TAG_DEPT_ID = "id";
    private static final String TAG_DEPT_TITLE = "dept_title";
    private static final String TAG_DEPT_DESC = "dept_desc";

    // contacts JSONArray
    JSONArray contents = null;

    // Hashmap for ListView
    ArrayList<DepartmentModel> departmentList;

    SQLiteDatabase sqLiteDB;
    DatabaseHandler dbHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        departmentList = new ArrayList<DepartmentModel>();

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
            pDialog = new ProgressDialog(JsonDepartment.this);
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

                        Integer deptId = object.getInt(TAG_DEPT_ID);
                        String deptTitle = object.getString(TAG_DEPT_TITLE);
                        String deptDesc = object.getString(TAG_DEPT_DESC);

                        DepartmentModel departmentModel = new DepartmentModel();

                        departmentModel.deptId = deptId;
                        departmentModel.deptTitle = deptTitle;
                        departmentModel.deptDesc = deptDesc;

                        Queries.InsertDepartment(sqLiteDB, dbHandler, departmentModel);
                        departmentList.add(departmentModel);
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

            ArrayList<DepartmentModel> departmentModels = Queries.getDepartment(sqLiteDB, dbHandler);
            DepartmentListAdapter adapter = new DepartmentListAdapter(departmentModels);

            setListAdapter(adapter);
            /**
             * Updating parsed JSON data into ListView
             **/
        }
    }

    class DepartmentListAdapter extends BaseAdapter {
        ArrayList<DepartmentModel> departmentModels;

        public DepartmentListAdapter(ArrayList<DepartmentModel> departmentModels){
            this.departmentModels = departmentModels;
        }

        @Override
        public int getCount() {
            return departmentList.size();
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
            DepartmentModel department = this.departmentModels.get(position);

            LayoutInflater inflater = (LayoutInflater)JsonDepartment.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.list_item, null);

            TextView deptTitle = (TextView)view.findViewById(R.id.content_title);
            deptTitle.setText(department.deptTitle);

            TextView deptDesc = (TextView)view.findViewById(R.id.content_body);
            deptDesc.setText(department.deptDesc);
            return view;
        }
    }
}

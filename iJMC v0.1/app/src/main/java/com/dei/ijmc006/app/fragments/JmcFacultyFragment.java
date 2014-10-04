package com.dei.ijmc006.app.fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.dei.ijmc006.app.R;
import com.dei.ijmc006.app.adapters.JmcContentAdapter;
import com.dei.ijmc006.app.adapters.JmcFacultyAdapter;
import com.dei.ijmc006.app.helper.DatabaseHandler;
import com.dei.ijmc006.app.helper.Queries;
import com.dei.ijmc006.app.model.ContentModel;
import com.dei.ijmc006.app.model.FacultyModel;

import java.util.ArrayList;

/**
 * Created by user on 10/1/2014.
 */
public class JmcFacultyFragment extends Fragment {
    SQLiteDatabase sqLiteDB;
    DatabaseHandler dbHandler;
    Context context;
    ArrayList<FacultyModel> facultyList;
    private View view;

    public JmcFacultyFragment(Context context) {
        this.context = context;
        this.dbHandler = new DatabaseHandler(context);
        facultyList = new ArrayList<FacultyModel>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.jmc_contents_list, null);

            ArrayList<FacultyModel> facultyModels = Queries.getFaculty(sqLiteDB, dbHandler);
            JmcFacultyAdapter adapter = new JmcFacultyAdapter(context, facultyModels);

            ListView listContentView = (ListView)view.findViewById(R.id.list_contentbody);
            listContentView.setAdapter(adapter);
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
        }
    }
}

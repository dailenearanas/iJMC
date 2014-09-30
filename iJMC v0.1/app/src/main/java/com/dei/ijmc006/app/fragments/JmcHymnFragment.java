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
import com.dei.ijmc006.app.helper.DatabaseHandler;
import com.dei.ijmc006.app.helper.Queries;
import com.dei.ijmc006.app.model.ContentModel;

import java.util.ArrayList;

/**
 * Created by user on 9/27/2014.
 */
public class JmcHymnFragment extends Fragment {
    SQLiteDatabase sqLiteDB;
    DatabaseHandler dbHandler;
    Context context;
    ArrayList<ContentModel> contentList;

    private View view;

    public JmcHymnFragment(Context context) {
        this.context = context;
        this.dbHandler = new DatabaseHandler(context);
        contentList = new ArrayList<ContentModel>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.menu_item_1, null);

            ArrayList<ContentModel> contentModels = Queries.getJmcHymn(sqLiteDB, dbHandler);
            JmcContentAdapter adapter = new JmcContentAdapter(context, contentModels);

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

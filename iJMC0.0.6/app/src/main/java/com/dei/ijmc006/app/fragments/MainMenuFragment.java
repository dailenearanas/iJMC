package com.dei.ijmc006.app.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.dei.ijmc006.app.R;
import com.dei.ijmc006.app.adapters.JmcProfileAdapter;
import com.dei.ijmc006.app.adapters.MainScreenMenuSectionAdapter;
import com.dei.ijmc006.app.app.MainScreenActivity;
import com.dei.ijmc006.app.config.Config;
import com.dei.ijmc006.app.helper.DatabaseHandler;
import com.dei.ijmc006.app.helper.Queries;
import com.dei.ijmc006.app.model.ContentModel;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by user on 8/21/2014.
 */

public class MainMenuFragment extends Fragment {
    private View mainMenu, contentView;
    private Config config;
    private FragmentTransaction ft;
    SQLiteDatabase sqLiteDb;
    DatabaseHandler dbHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.config = new Config();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainMenu = inflater.inflate(R.layout.main_menu, container, false);
        ft = getFragmentManager().beginTransaction();

        final ArrayList<String> menus = this.config.getMenu();
        MainScreenMenuSectionAdapter adapter = new MainScreenMenuSectionAdapter(getActivity(), menus);

       ListView listView = (ListView)mainMenu.findViewById(R.id.list);
       listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
//                        final String jmcProf = Queries.getJMCProf(sqLiteDB, dbHandler);
                        String jmcProf = Queries.getJMCProf(sqLiteDb, dbHandler);
                        JmcProfileAdapter jmcProfileAdapter = new JmcProfileAdapter(getActivity(), jmcProf);

                        ListView listView = (ListView)contentView.findViewById(R.id.list);
                        listView.setAdapter(jmcProfileAdapter);


                        break;
                    case 1:
                        //Toast.makeText(getActivity(), "Clicked " + str, Toast.LENGTH_SHORT).show();
                        ft.replace(R.id.container, new TextualContent());
                        ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack("TextualContent");
                        ft.commit();
                        break;
                    case 2:
                        //Toast.makeText(getActivity(), "Clicked " + str, Toast.LENGTH_SHORT).show();
                        ft.replace(R.id.container, new TextualContent());
                        ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack("TextualContent");
                        ft.commit();
                        break;
                    case 3:
                        //Toast.makeText(getActivity(), "Clicked " + str, Toast.LENGTH_SHORT).show();
                        ft.replace(R.id.container, new TextualContent());
                        ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack("TextualContent");
                        ft.commit();
                        break;
                    case 4:
                        //Toast.makeText(getActivity(), "Clicked " + str, Toast.LENGTH_SHORT).show();
                        ft.replace(R.id.container, new TextualContent());
                        ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack("TextualContent");
                        ft.commit();
                        break;
                }
            }
        });

        return mainMenu;
    }

//    class ContentAdapter extends BaseAdapter{
//        @Override
//        public int getCount() {
//            return 0;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view = inflater.inflate(R.layout.list_item, null);
//
//            TextView contentType = (TextView)view.findViewById(R.id.content_text);
//            contentType.setText(jmcProf);
//
//            return view;
//        }
    //}
}

package com.omiplekevin.clickeventlisteners.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.omiplekevin.adapters.ListViewAdapter;

import java.util.ArrayList;

/**
 * Created by omiplekevin on 7/23/2014.
 */
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newintent_layout);

        ListView listView = (ListView)findViewById(R.id.listView);

        ArrayList<String> items = new ArrayList<String>();
        for (int i=0;i<100;i++)
        {
            items.add(""+i);
        }
        ListViewAdapter adapter = new ListViewAdapter(items, this);
        listView.setAdapter(adapter);
    }
}

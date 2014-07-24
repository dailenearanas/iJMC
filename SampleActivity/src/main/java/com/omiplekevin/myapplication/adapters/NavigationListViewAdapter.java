package com.omiplekevin.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.omiplekevin.myapplication.sampleactivity.R;

import java.util.ArrayList;


/**
 * Created by omiplekevin on 7/19/2014.
 */
public class NavigationListViewAdapter extends ArrayAdapter<String>{

    private Context context;
    private ArrayList<String> items;
    public NavigationListViewAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
        super(context, 0, objects);
        this.context = context;
        this.items = objects;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public String getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.navigationlistview_item, null);
        TextView textView = (TextView)view.findViewById(R.id.listViewItem);
        textView.setText(this.items.get(position));
        return view;
    }
}

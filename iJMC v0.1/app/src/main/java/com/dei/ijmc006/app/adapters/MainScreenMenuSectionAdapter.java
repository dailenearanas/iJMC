package com.dei.ijmc006.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.dei.ijmc006.app.R;

import java.util.ArrayList;

/**
 * Created by user on 8/26/2014.
 */
public class MainScreenMenuSectionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> menus;

    public MainScreenMenuSectionAdapter(Context context, ArrayList<String> menus) {
        this.context = context;
        this.menus = menus;
    }

    @Override
    public int getCount() {
        return menus.size();
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
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.menu_item, null);

        TextView textView = (TextView)view.findViewById(R.id.main_menu_title);
        textView.setText(menus.get(position));
        return view;
    }
}

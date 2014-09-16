package com.dei.ijmc006.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dei.ijmc006.app.R;

import java.util.ArrayList;

/**
 * Created by user on 8/21/2014.
 */
public class MainMenuAdapter extends BaseAdapter {

    private ArrayList<String> menu;
    private Context context;

    public MainMenuAdapter(Context context, ArrayList<String> menu) {
        this.menu = menu;
        this.context = context;
    }

    @Override
    public int getCount() { return menu.size(); }

    @Override
    public Object getItem(int position) { return menu.get(position); }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.menu_item, null);
        ImageView mainMenuIcon = (ImageView)view.findViewById(R.id.main_menu_icon);
        TextView mainMenuTitle = (TextView)view.findViewById(R.id.main_menu_title);

        switch (position) {
            case 0:
                mainMenuIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
                break;
            case 1:
                mainMenuIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
                break;
            case 2:
                mainMenuIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
                break;
            case 3:
                mainMenuIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
                break;
            case 4:
                mainMenuIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
                break;
            case 5:
                mainMenuIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
                break;
        }

        mainMenuTitle.setText(this.menu.get(position));
        return view;

    }
}

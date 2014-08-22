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
 * Created by user on 8/19/2014.
 */
public class NavigationMenuAdapter extends BaseAdapter{

    private ArrayList<String> menus;
    private Context context;

    public NavigationMenuAdapter(Context context, ArrayList<String> menus) {
        this.menus = menus;
        this.context = context;
    }
    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.nav_menu_item,null);
        ImageView menuIcon = (ImageView)view.findViewById(R.id.menu_icon);
        TextView menuTitle = (TextView)view.findViewById(R.id.menu_title);

        switch (position)
        {
            case 0:
                menuIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
                break;
            case 1:
                menuIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
                break;
            case 2:
                menuIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
                break;
            case 3:
                menuIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
                break;
        }

        menuTitle.setText(this.menus.get(position));
        return view;
    }
}

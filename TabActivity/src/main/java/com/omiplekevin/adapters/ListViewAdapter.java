package com.omiplekevin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.omiplekevin.tabactivity.R;

import java.util.ArrayList;

/**
 * Created by omiplekevin on 7/29/2014.
 */
public class ListViewAdapter extends BaseAdapter{

    private ArrayList<String> items;
    private Context context;

    public ListViewAdapter(ArrayList<String> items, Context context) {
        super();
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(this.context).inflate(R.layout.listview_item, parent, false);
            holder.textView = (TextView)convertView.findViewById(R.id.listitem_text);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.textView.setText(this.items.get(position));


        return convertView;
    }

    static class ViewHolder
    {
        TextView textView;
    }
}

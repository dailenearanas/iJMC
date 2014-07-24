package com.omiplekevin.clicklisteners.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.omiplekevin.clicklisteners.app.R;

import java.util.ArrayList;

/**
 * Created by omiplekevin on 7/24/2014.
 */
public class ListViewAdapter extends BaseAdapter{

    private ArrayList<String> items;
    private Context context;

    public ListViewAdapter(ArrayList<String> items, Context context)
    {
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
        LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listitemview, null);

        TextView itemtext = (TextView)view.findViewById(R.id.textView);
        itemtext.setText(items.get(position));

        return view;
    }
}

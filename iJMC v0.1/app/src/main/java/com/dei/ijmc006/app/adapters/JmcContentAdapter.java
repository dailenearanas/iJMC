package com.dei.ijmc006.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.dei.ijmc006.app.R;
import com.dei.ijmc006.app.app.MainScreenActivity;
import com.dei.ijmc006.app.config.Config;
import com.dei.ijmc006.app.fragments.MainMenuFragment;
import com.dei.ijmc006.app.helper.JsonParser;
import com.dei.ijmc006.app.model.ContentModel;

import java.util.ArrayList;

/**
 * Created by user on 9/16/2014.
 */
public class JmcContentAdapter extends BaseAdapter {
    ArrayList<ContentModel> contentList;
    Context context;


    public JmcContentAdapter(Context context, ArrayList<ContentModel> contentModels) {
        this.context = context;
        this.contentList = contentModels;
    }

    @Override
    public int getCount() {
        return contentList.size();
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
        ContentModel content = this.contentList.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, null);

        TextView contentType = (TextView) view.findViewById(R.id.content_title);
        contentType.setText(content.contentType);

        TextView contentBody = (TextView) view.findViewById(R.id.content_body);
        contentBody.setText(content.contentBody);
        return view;
    }
}

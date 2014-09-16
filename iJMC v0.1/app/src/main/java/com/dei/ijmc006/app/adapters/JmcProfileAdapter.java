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
public class JmcProfileAdapter extends BaseAdapter {
    private Context context;
    private String jmcProfile;

    public JmcProfileAdapter(Context context, String jmcProfile){
        this.context = context;
        this.jmcProfile = jmcProfile;
    }

    @Override
    public int getCount() {
        return 0;
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
        //Config config = this.jmcProfile.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, null);

        TextView txtJmcProf = (TextView)view.findViewById(R.id.content_text);
        txtJmcProf.setText(jmcProfile);

        return view;
    }
}

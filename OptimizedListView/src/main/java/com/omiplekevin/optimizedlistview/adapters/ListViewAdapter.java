package com.omiplekevin.optimizedlistview.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.omiplekevin.optimizedlistview.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by omiplekevin on 7/26/2014.
 */
public class ListViewAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<String> items;
    public ListViewAdapter(Context context)
    {
        this.context = context;
        items = new ArrayList<String>();
        for(int i=1;i<=50;i++)
        {
            items.add("Lorem ipsum dolor sit amet... "+i);
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listviewitem, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.labelText);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        new AsyncTask<ViewHolder, Void, Bitmap>()
        {
            private ViewHolder v;

            @Override
            protected Bitmap doInBackground(ViewHolder... params) {
                v = params[0];
                int randNum = 1 + (int)(Math.random()*3);
                switch(randNum)
                {
                    case 1:
                        return BitmapFactory.decodeResource(ListViewAdapter.this.context.getResources(), R.drawable.landscape_scaledown);
                    case 2:
                        return BitmapFactory.decodeResource(ListViewAdapter.this.context.getResources(), R.drawable.sample_image_apple);
                    case 3:
                        return BitmapFactory.decodeResource(ListViewAdapter.this.context.getResources(), R.drawable.android_icon);
                }
                return BitmapFactory.decodeResource(ListViewAdapter.this.context.getResources(), R.drawable.image_holder);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                v.imageView.setVisibility(View.INVISIBLE);
                v.textView.setVisibility(View.INVISIBLE);

                v.imageView.setImageBitmap(bitmap);
                v.imageView.setVisibility(View.VISIBLE);
                v.textView.setText(ListViewAdapter.this.items.get(position));
                v.textView.setVisibility(View.VISIBLE);
            }
        }.execute(viewHolder);

        return convertView;
    }

    static class ViewHolder
    {
        ImageView imageView;
        TextView textView;
    }
}

package com.dei.ijmc006.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.dei.ijmc006.app.R;

/**
 * MyApplication
 * Developer: Kevin Jimenez Omiple
 * Datestamp: 8/12/2014, 10:17 PM.
 *
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class DummySectionFragment extends Fragment{

    private int pageNum = 0;
    private ListView view;
    private String sectionTitle;
    public DummySectionFragment(int pageNumber, String sectionTitle)
    {
        this.pageNum = pageNumber;
        this.sectionTitle = sectionTitle;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        if(savedInstanceState == null) {
            this.view = (ListView)inflater.inflate(R.layout.fragment_dummy_section, container, false);
            this.view.setAdapter(new ListViewAdapter());
            this.view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast notifier = Toast.makeText(getActivity(), ((TextView) view).getText(), Toast.LENGTH_SHORT);
                    notifier.show();
                }
            });
            this.view.setScrollbarFadingEnabled(true);
        }
        this.view.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch(scrollState){
                    case SCROLL_STATE_IDLE:
                        Log.e("SCROLL STATE", "List is now idle");
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        Log.e("SCROLL STATE", "List is now scrolling");
                        break;
                    case SCROLL_STATE_FLING:
                        Log.e("SCROLL STATE", "List is now flinging");
                        break;
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(android.os.Build.VERSION.SDK_INT > 14){
                    try{
                        View v = DummySectionFragment.this.view.getChildAt(0);
                        View v2 = DummySectionFragment.this.view.getChildAt(1);
                        int scroll = -v.getTop() + DummySectionFragment.this.view.getFirstVisiblePosition() * v.getHeight();
                        v.setScrollY((int)((v2.getScrollY()-scroll)/2));
                        v2.setScrollY(v2.getScrollY()*2);
                    }
                    catch(Exception e)
                    {
                        Log.e("EXCEPTION", "Child View 'N' not yet drawn");
                    }
                }
            }
        });
        return this.view;
    }

    class ListViewAdapter extends BaseAdapter{

        public ListViewAdapter(){
        }

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(getActivity());
            textView.setText(DummySectionFragment.this.sectionTitle + " " + position);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            if(position == 0){
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PT, 30f);
            }
            return textView;
        }
    }
}

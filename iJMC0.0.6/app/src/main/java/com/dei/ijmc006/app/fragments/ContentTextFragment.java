package com.dei.ijmc006.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dei.ijmc006.app.R;

/**
 * Created by user on 8/26/2014.
 */
public class ContentTextFragment extends Fragment {

    private View view;
    public ContentTextFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.menu_item_1, null);
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
        }
    }
}

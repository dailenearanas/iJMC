package com.omiplekevin.clickeventlisteners.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by omiplekevin on 7/23/2014.
 */
public class FragmentView extends Fragment {

    /**
     * Constructor for FragmentView class
     */
    public FragmentView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }
}

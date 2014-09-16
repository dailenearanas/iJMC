package com.dei.ijmc006.app.fragments;

/**
 * MyApplication
 * Developer: Kevin Jimenez Omiple
 * Datestamp: 7/30/2014, 4:52 PM.
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

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.dei.ijmc006.app.adapters.SectionPagerAdapter;
import com.dei.ijmc006.app.app.MainScreenActivity;
import com.dei.ijmc006.app.R;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private ViewPager viewPager;

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final LinearLayout linearBtnHolder = (LinearLayout) rootView.findViewById(R.id.linearBtnHolder);
        final HorizontalScrollView sectionTabHolder = (HorizontalScrollView)rootView.findViewById(R.id.sectionTabHolder);
        this.viewPager = (ViewPager) rootView.findViewById(R.id.section_pager);
        final List<Button> menuBtns = new ArrayList<Button>();

        String[] menuLabels = {"The School",
                "Vision, Mission and Goal",
                "Administration",
                "Departments",
                "Faculty",
                "Staff"}; //6 sections
        for (int i = 0; i < menuLabels.length; i++) {
            final int refID = i;
            Button btn = createMeAButton(menuLabels[i]);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int x = 0; x < menuBtns.size(); x++) {
                        if (x != refID) {
                            menuBtns.get(x).setSelected(false);
                        } else {
                            menuBtns.get(x).setSelected(true);

                            PlaceholderFragment.this.viewPager.setCurrentItem(x);
                        }
                    }
                }
            });
            menuBtns.add(btn);
            linearBtnHolder.addView(btn);
        }
        menuBtns.get(0).setSelected(true);

        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getFragmentManager(), menuBtns.size(), menuLabels);
        this.viewPager.setAdapter(pagerAdapter);
        this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int x = 0; x < menuBtns.size(); x++) {
                    if (x != position) {
                        menuBtns.get(x).setSelected(false);
                    } else {
                        menuBtns.get(x).setSelected(true);
                        int btnHalf = menuBtns.get(x).getWidth()/2;
                        int btnLeft = menuBtns.get(x).getLeft();
                        int btnCenterOffset = btnLeft+btnHalf;
                        sectionTabHolder.smoothScrollTo((btnCenterOffset-sectionTabHolder.getWidth()/2),0);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return rootView;
    }

    private Button createMeAButton(String label) {
        Button menubtn = new Button(getActivity());
        menubtn.setText(label);
        menubtn.setTextColor(Color.parseColor("#FFFFFF"));
        menubtn.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        menubtn.setSingleLine(true);
        menubtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_tab_selector));
        return menubtn;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainScreenActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}

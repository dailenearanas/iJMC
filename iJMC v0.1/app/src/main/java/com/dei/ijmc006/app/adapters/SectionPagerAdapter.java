package com.dei.ijmc006.app.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.dei.ijmc006.app.fragments.DummySectionFragment;

/**
 * MyApplication
 * Developer: Kevin Jimenez Omiple
 * Datestamp: 8/12/2014, 10:16 PM.
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

public class SectionPagerAdapter extends FragmentPagerAdapter{

    private int sectionNumbers;
    private String[] labels;
    public SectionPagerAdapter(FragmentManager fm, int sectionNumbers, String[] labels) {
        super(fm);
        this.sectionNumbers = sectionNumbers;
        this.labels = labels;
    }

    @Override
    public Fragment getItem(int position) {
        return new DummySectionFragment(position, labels[position]);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return sectionNumbers;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}

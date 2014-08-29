package com.dei.ijmc006.app.config;

import java.util.ArrayList;

/**
 * Created by user on 8/21/2014.
 */
public class ConfigMain {

    ArrayList<String> menu;

    public ConfigMain() {
        menu = new ArrayList<String>();
        menu.add("JMC Profile");
        menu.add("Vision, Mission, Goals");
        menu.add("JMC Hymn");
        menu.add("Board of Trustees");
        menu.add("Faculty Members");

    }

    public ArrayList<String> getMenu() { return menu; }

    public String getMenuItem(int position) { return menu.get(position);}
}

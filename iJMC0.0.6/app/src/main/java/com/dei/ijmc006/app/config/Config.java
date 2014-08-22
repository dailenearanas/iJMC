package com.dei.ijmc006.app.config;

import java.util.ArrayList;

/**
 * Created by user on 8/19/2014.
 */
public class Config {

    ArrayList<String> menus;

    public Config() {
        menus = new ArrayList<String>();
        menus.add("iJMC");
        menus.add("Developers");
        menus.add("Update");
        menus.add("Help");
    }

    public ArrayList<String> getNavMenus(){
        return menus;
    }

    public String getNavManuItem(int position){
        return menus.get(position);
    }

}

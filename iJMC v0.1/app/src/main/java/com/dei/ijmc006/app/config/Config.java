package com.dei.ijmc006.app.config;

import java.util.ArrayList;

/**
 * Created by user on 8/19/2014.
 */
public class Config {

    ArrayList<String> menus;
    ArrayList<String> mainMenuItems;

    public Config() {
        menus = new ArrayList<String>();
        menus.add("iJMC");
        menus.add("Developers");
        menus.add("Update");
        menus.add("Help");

        mainMenuItems = new ArrayList<String>();
        mainMenuItems.add("JMC Profile");
        mainMenuItems.add("Vision, Mission, Goals");
        mainMenuItems.add("JMC Hymn");
        mainMenuItems.add("Board of Trustees");
        mainMenuItems.add("Faculty Members");
    }

    public static String FACULTY_TABLE = "faculties";

    public static String STUDENT_TABLE = "students";

    public static String CONTENT_TABLE = "contents";

    public static String DEPARTMENT_TABLE = "departments";

    public static String BASE_URL = "http://192.168.56.1/iJMC-WebApp/public";

    public static String JSON_URL = "http://192.168.56.1/iJMC-WebApp/public/jsonlisting";

    public static String CONTENT_JSON = "contentlist.json";

    public static String DEPARTMENT_JSON = "departmentlist.json";

    public static String STUDENT_JSON = "studentlist.json";

    public ArrayList<String> getNavMenus() {
        return menus;
    }

    public String getNavMenuItem(int position) {
        return menus.get(position);
    }

    public ArrayList<String> getMenu() {
        return mainMenuItems;
    }

    public String jmcProfile;

    public String LOGIN = "LOGIN";
    /*
    SHARED PREFERENCE FIELDS
     */

    public static String SHA_USR_ID = "USR_ID";

    public static String SHA_USR_FNAME = "USR_FNAME";

    public static String SHA_USR_MNAME = "USR_MNAME";

    public static String SHA_USR_LNAME = "USR_LNAME";

    public static String SHA_USR_DEPT_ID = "USR_DEPT_ID";
}

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
        menus.add("About");
        menus.add("Developers");
        menus.add("Update");
        menus.add("Help");

        mainMenuItems = new ArrayList<String>();
        mainMenuItems.add("JMC Profile");
        mainMenuItems.add("Vision, Mission, Goals");
        mainMenuItems.add("JMC Hymn");
        mainMenuItems.add("Faculty Members");
    }

    public static String FACULTY_TABLE = "faculties";

    public static String STUDENT_TABLE = "students";

    public static String CONTENT_TABLE = "contents";

    public static String DEPARTMENT_TABLE = "departments";

    public static String BASE_URL = "http://192.168.56.1/iJMC-WebApp/public";

//    public static String JSON_URL = "http://192.168.43.74/iJMC-WebApp/public/jsonlisting";

    public static String JSON_URL = "http://192.168.43.74/ijmc3/public/jsonlisting";

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

    //    CONTENT TABLE TAGS
    public static final String TAG_CONTENT_ID = "id";

    public static final String TAG_CONTENT_TYPE = "content_type";

    public static final String TAG_CONTENT_BODY = "content_body";

    //    FACULTY TABLE TAGS
    public static final String TAG_FACULTY_ID = "fc_idn";

    public static final String TAG_FACULTY_FNAME = "fc_name";

    public static final String TAG_FACULTY_MNAME = "fc_mname";

    public static final String TAG_FACULTY_LNAME = "fc_lname";

    public static final String TAG_FACULTY_SFX = "fc_suffix";

    public static final String TAG_FACULTY_GENDER = "fc_gender";

    public static final String TAG_FACULTY_IMAGE = "image_path";

    public static final String TAG_FACULTY_DEPT = "dept_id";

    public static final String TAG_FACULTY_POSITION = "pos";

    //    DEPARTMENT TABLE TAGS
    public static final String TAG_DEPT_ID = "id";

    public static final String TAG_DEPT_TITLE = "dept_title";

    public static final String TAG_DEPT_DESC = "dept_desc";

    public static ArrayList<String> getJsonUrls()
    {
        ArrayList<String> jsonUrls = new ArrayList<String>();
        jsonUrls.add(JSON_URL + "/" + CONTENT_JSON);
        jsonUrls.add(JSON_URL + "/" + DEPARTMENT_JSON);
        jsonUrls.add(JSON_URL + "/" + STUDENT_JSON);
        return jsonUrls;
    }
}


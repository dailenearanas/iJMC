package info.androidhive.jsonparsing;

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

    public static String contentsTable = "contents";

    public static String departmentTable = "departments";

    public static String BASE_URL = "http://192.168.56.1/iJMC-WebApp/public";

    public static String JSON_URL = "http://192.168.56.1/iJMC-WebApp/public/jsonlisting";

    public static String CONTENT_JSON = "contentlist.json";

    public static String DEPARTMENT_JSON = "departmentlist.json";

    public ArrayList<String> getNavMenus() {
        return menus;
    }

    public String getNavManuItem(int position) {
        return menus.get(position);
    }

}

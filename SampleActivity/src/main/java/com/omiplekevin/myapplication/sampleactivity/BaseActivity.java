package com.omiplekevin.myapplication.sampleactivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by omiplekevin on 7/20/2014.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        Boolean logged = sp.getBoolean("_login", false);
        Log.e("LOGIN", ""+logged);
        if(logged)
        {
        }
        else
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}

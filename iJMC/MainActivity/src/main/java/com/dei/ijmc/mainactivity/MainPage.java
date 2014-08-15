package com.dei.ijmc.mainactivity;

/**
 * Created by user on 7/23/2014.
 */
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.LinearLayout;


public class MainPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        Toast.makeText(getApplicationContext(), "Activated", Toast.LENGTH_LONG).show();
    }
}

package com.omiplekevin.clickeventlisteners.app;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import com.omiplekevin.asynctasks.AsyncTaskCountOneToHundred;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("ACTIVITY", "OnCreate");
        Button btnForIntent = (Button)findViewById(R.id.btnForIntent);
        Button btnForFragment = (Button)findViewById(R.id.btnForFragment);
        Button btnForAsync = (Button)findViewById(R.id.btnForAsync);

        btnForAsync.setOnClickListener(this);
        btnForIntent.setOnClickListener(this);
        btnForFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btnForIntent:
                Intent newIntent = new Intent(this, SecondActivity.class);
                startActivity(newIntent);
                break;
            case R.id.btnForFragment:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, new FragmentView());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack("fromBaseToFragmentView");
                ft.commit();
                break;
            case R.id.btnForAsync:
                new AsyncTaskCountOneToHundred().execute();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("ACTIVITY", "OnDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("ACTIVITY", "OnPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("ACTIVITY", "OnResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("ACTIVITY", "OnStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("ACTIVITY", "OnStop");
    }
}

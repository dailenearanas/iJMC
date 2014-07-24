package com.omiplekevin.clicklisteners.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.omiplekevin.clicklisteners.adapters.ListViewAdapter;
import com.omiplekevin.clicklisteners.asynctasks.BasicAsyncTask;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button clickBtn = (Button)findViewById(R.id.clickBtn);
        Button taskBtn = (Button)findViewById(R.id.asyncBtn);
        ListView listView = (ListView)findViewById(R.id.listView);

        ArrayList<String> items = new ArrayList<String>();
        for (int i=1;i<=50;i++) {
            items.add("Dummy " + i);
        }

        ListViewAdapter adapter = new ListViewAdapter(items, getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtitem = (TextView) view.findViewById(R.id.textView);
                Toast.makeText(getApplicationContext(), txtitem.getText() + " position: " + position, Toast.LENGTH_LONG).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtitem = (TextView) view.findViewById(R.id.textView);
                Toast.makeText(getApplicationContext(), "Long click on " + txtitem.getText() + " position: " + position, Toast.LENGTH_LONG).show();
                return true;
            }
        });
        clickBtn.setOnClickListener(this);
        clickBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(),"Loooooooong Click!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        taskBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.clickBtn:
                Toast.makeText(getApplicationContext(),"Clicked Button!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.asyncBtn:
                new BasicAsyncTask().execute();
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
}

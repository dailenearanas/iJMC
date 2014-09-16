package com.dei.ijmc006.app.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
//import android.content.Intent;
import com.dei.ijmc006.app.R;
import com.dei.ijmc006.app.helper.AsyncJsonCheckID;
import com.dei.ijmc006.app.helper.AsyncJsonData;
import com.dei.ijmc006.app.helper.DatabaseHandler;
import com.dei.ijmc006.app.helper.Queries;
import org.json.JSONObject;

public class MainActivity extends ActionBarActivity {

    SharedPreferences sharedPref;
    private Button registerBtn;
    private AlertDialog dialog;

    AsyncJsonCheckID asyncJsonCheckID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        registerBtn = (Button) findViewById(R.id.register);

        final SharedPreferences.Editor prefEditor;
        sharedPref = this.getSharedPreferences("SETTINGS", MODE_PRIVATE);
        boolean hasLoggedIn = sharedPref.getBoolean("LOGIN", false);
        if (hasLoggedIn) {
            changeIntent();
            finish();

        } else {
            instantiateRegisterBtn();
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            dialog = builder.create();
            dialog.setTitle("Account Activation");
            dialog.setMessage("Student ID");

            // edit text
            final EditText input = new EditText(MainActivity.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            dialog.setView(input);

            dialog.setIcon(R.drawable.ic_launcher);
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Activate", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    asyncJsonCheckID = new AsyncJsonCheckID(MainActivity.this, sharedPref);
                    asyncJsonCheckID.execute(input.getText().toString());

                }
            });

            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            });
        }
    }

    private void instantiateRegisterBtn() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    private void changeIntent() {
        Intent intent = new Intent(MainActivity.this, MainScreenActivity.class);
        startActivity(intent);
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
            Log.e("MAIN ACTIVITY", "SETTINGS");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

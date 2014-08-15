package com.dei.ijmc.mainactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
//import android.content.Intent;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {

    SharedPreferences sharedPref;
    private Button registerBtn;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerBtn = (Button) findViewById(R.id.register);

        final SharedPreferences.Editor prefEditor;
        sharedPref = this.getSharedPreferences("SETTINGS", MODE_PRIVATE);
        boolean hasLoggedIn = sharedPref.getBoolean("LOGIN", false);
        if (hasLoggedIn) {
            changeIntent();
            finish();
            // charcharan lng ... birthday ni kharen kron .. i hate her sssososososo 
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
                    SharedPreferences.Editor prefEditor;
                    boolean log = sharedPref.getBoolean("LOGIN", false);
                    prefEditor = sharedPref.edit();
                    prefEditor.putBoolean("LOGIN", true);
                    prefEditor.commit();
                    changeIntent();
                    finish();
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
        Intent intent = new Intent(MainActivity.this, MainPage.class);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

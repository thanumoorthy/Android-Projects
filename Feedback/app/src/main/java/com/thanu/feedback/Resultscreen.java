package com.thanu.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Resultscreen extends ActionBarActivity {

    private String status = null;
    private Bundle getData = null;
    private final String TAG_MSG = "Message";
    private TextView Resultview = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultscreen);
        Intent i = getIntent();
        getData = i.getExtras();
        if( getData != null ) {
            status = getData.getString(TAG_MSG);
        }

        Resultview = (TextView)findViewById(R.id.resut);
        if ( null !=status && status.equalsIgnoreCase("success")) {
            Resultview.setText(R.string.success_msg);
        }
        else {
            Resultview.setText(R.string.error_msg);
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultscreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

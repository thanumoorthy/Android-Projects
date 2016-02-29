package com.thanu.feedback;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private RadioGroup  btngrp = null;
    private EditText    comment = null;
    private RadioButton ratebtn = null;
    private Button  submit  = null;
    private Toast toast = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }

    void initialization(){
        btngrp  =  (RadioGroup)findViewById(R.id.rate_group);
        comment =  ( EditText) findViewById(R.id.comment);
        submit  =  (Button)findViewById(R.id.submit);
        submit.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.submit:
                if ( btngrp.getCheckedRadioButtonId() != -1 ){
                    int selectedId=btngrp.getCheckedRadioButtonId();
                    ratebtn=(RadioButton)findViewById(selectedId);
                    System.out.println("Rate is " + ratebtn.getText());
                    System.out.println("comment is " + comment.getText());
                    if ( null != ratebtn.getText() && null!= comment.getText() ){

                    }

                }
                else {
                    toast.makeText(this,"Kindly fill the required details", Toast.LENGTH_SHORT).show();
                }


             break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

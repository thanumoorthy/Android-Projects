package com.thanu.feedback;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private RadioGroup  btngrp = null;
    private EditText    comment = null;
    private RadioButton ratebtn = null;
    private Button  submit  = null;
    private Toast toast = null;
    private String URL = null;
    private String rating = null;
    private String feedback = null;
    private final String BaseURL = "http://192.168.43.59:8080/AIR-Mobile/ws/rateus/";
    private final String PARAM1 = "rating";
    private final String PARAM2 = "feedback";
    private ProgressDialog pDialog = null;
    private String jsonStr = null;
    private String status = null;
    private final String TAG_MSG = "Message";
    private final String FAIL_MSG = "Failure";




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
                          rating =  ratebtn.getText().toString();
                          feedback = comment.getText().toString();

                        try {
                            rating = java.net.URLEncoder.encode(rating, "utf-8").replaceAll("\\+",
                                    "%20");
                            feedback = java.net.URLEncoder.encode(feedback,
                                    "utf-8").replaceAll("\\+", "%20");
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                        URL = BaseURL +  rating + "/"
                                + feedback ;
                        System.out.println("Submitting request " + URL);
                        new submitFeedback().execute();
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



    private class submitFeedback extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Submiting the feedback...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // TODO Auto-generated method stub

            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response

            try {
                jsonStr = sh.makeServiceCall(URL, ServiceHandler.GET);

            } catch (Exception e) {
                status = FAIL_MSG;
                System.out.println("Exception occurs " + e);

            }

            if (jsonStr != null ) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    status = jsonObj.getString(TAG_MSG);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    status = FAIL_MSG;
                    e.printStackTrace();
                }

            }

            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
                URL = "";


                Intent i;
                Bundle input = new Bundle();
                input.putString(TAG_MSG, status);

                i = new Intent(MainActivity.this, Resultscreen.class);
                i.putExtras(input);
                startActivity(i);


        }

    }


}

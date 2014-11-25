package com.example.allintasks;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
//permissions needed to use the SMS feature, and creating button and text edit boxes
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//below allows access to contacts within the app, part of the permissions 
import android.provider.ContactsContract;

public class MainActivity extends Activity {

    Button button;
    EditText editPhoneNum;
    EditText editSMS;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
 
        button = (Button) findViewById(R.id.button);
        editPhoneNum = (EditText) findViewById(R.id.editPhoneNum);
        editSMS = (EditText) findViewById(R.id.editSMS);
 

        button.setOnClickListener(new OnClickListener() {
 
            //still figuring out the specifics of this code, and how to alter it for our uses. implementation of onClick method
        	@Override
            public void onClick(View v) {
 
                String phoneNo = editPhoneNum.getText().toString();
                String sms = editSMS.getText().toString();
                //useful if sending fails
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "Message Sent!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "Message failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
 
            }

        });
    }


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.print("Ron Test__Asia2 Erika3");

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

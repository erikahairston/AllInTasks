package com.example.allintasks;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
//permissions needed to use the SMS feature, and creating button and text edit boxes
//below allows access to contacts within the app, part of the permissions 
//addition allows selection of contacts
//<<<<<<< Updated upstream
//=======
//>>>>>>> Stashed changes

@SuppressLint("NewApi") public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    Button button;
    EditText editContactName;
    EditText editSMS;
 
    public void onCreate1(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_screen);
        //makeNewGroup();
 
        button = (Button) findViewById(R.id.button);
        editContactName = (EditText) findViewById(R.id.editContactName);
        editSMS = (EditText) findViewById(R.id.editSMS);
 

        button.setOnClickListener(new OnClickListener() {
 
            //still figuring out the specifics of this code, and how to alter it for our uses. implementation of onClick method
        	@SuppressLint("NewApi") @Override
            public void onClick(View v) {
 
                String phoneNo = editContactName.getText().toString();
                String sms = editSMS.getText().toString();
                
                //useful if sending fails for network reasons or things out of out control
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
    

 
   /*
    Defines an array that contains resource ids for the layout views  that get the Cursor column contents. 
    The id is pre-defined in the Android framework, so it is prefaced with "android.R.id"
    */
   private final static int[] TO_IDS = {
          android.R.id.text1
   };
   // Define global mutable variables
   // Define a ListView object
   ListView mContactsList;
   // Define variables for the contact the user selects
   // The contact's _ID value
   long mContactId;
   // The contact's LOOKUP_KEY
   String mContactKey;
   // A content URI for the selected contact
   Uri mContactUri;
   // An adapter that binds the result Cursor to the ListView
   private SimpleCursorAdapter mCursorAdapter;

// Empty public constructor, required by the system
   public void ContactsFragment() {}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        System.out.print("Analisse2 from erika");
        
        makeNewGroup();
    }
    
    public void makeNewGroup() {
    	//Intent intent = new Intent(this, MemberTasks.class);
    	EditText edit_message = (EditText) findViewById(R.id.edit_message);
    	String message = edit_message.getText().toString();
    	//intent.putExtra(EXTRA_MESSAGE, message);
    	// startActivity(intent);

    	button = (Button) findViewById(R.id.button1);
    	button.setOnClickListener(new OnClickListener() 
    	{
    		@Override
    		public void onClick(View view) 
    		{
    			System.out.println("hey girl");
    		}
    	
    	});
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
    
   /* public static void people()
    {
    	Scanner attendeesList = new Scanner (System.in);
    	
    	 for (int r = 1; r <  ; r++)
    	{
    	String  = attendeesList.next();
    	}
    	
    }*/
}

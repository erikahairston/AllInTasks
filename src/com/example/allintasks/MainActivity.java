package com.example.allintasks;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.CursorLoader;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
//permissions needed to use the SMS feature, and creating button and text edit boxes
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
//below allows access to contacts within the app, part of the permissions 
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
//addition allows selection of contacts
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.AdapterView;
import java.util.*;

@SuppressLint("NewApi") public class MainActivity extends Activity {

    Button button;
    EditText editContactName;
    EditText editSMS;
 
    public void onCreate1(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.main);
 
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
    //code is from http://developer.android.com/training/contacts-provider/retrieve-names.html
    
    /*
     * Defines an array that contains column names to move from
     * the Cursor to the ListView.
     */
    @SuppressLint("InlinedApi")
    private final static String[] FROM_COLUMNS = {
            Build.VERSION.SDK_INT
                    >= Build.VERSION_CODES.HONEYCOMB ?
                    Contacts.DISPLAY_NAME_PRIMARY :
                    Contacts.DISPLAY_NAME
                    };
                    
 
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
        setContentView(R.layout.activity_list_item);
        System.out.print("Analisse2 from erika");
    }

    // A UI Fragment must inflate its View
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the fragment layout
        return inflater.inflate(R.layout.contact_list_fragment,
            container, false);
    }
    
    public void onActivityCreated1(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Gets the ListView from the View list of the parent activity
        mContactsList =
            (ListView) getActivity().findViewById(R.layout.contact_list_view);
        // Gets a CursorAdapter
        mCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.contact_list_item,
                null,
                FROM_COLUMNS, TO_IDS,
                0);
        // Sets the adapter for the ListView
        mContactsList.setAdapter(mCursorAdapter);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        // Set the item click listener to be the current fragment.
        mContactsList.setOnItemClickListener(this);
 
    }
    @SuppressLint("InlinedApi")
    private static final String[] PROJECTION =
            {
                Contacts._ID,
                Contacts.LOOKUP_KEY,
                Build.VERSION.SDK_INT
                        >= Build.VERSION_CODES.HONEYCOMB ?
                        Contacts.DISPLAY_NAME_PRIMARY :
                        Contacts.DISPLAY_NAME

            };
 // The column index for the _ID column
    private static final int CONTACT_ID_INDEX = 0;
    // The column index for the LOOKUP_KEY column
    private static final int LOOKUP_KEY_INDEX = 1;
    
 // Defines the text expression
    @SuppressLint("InlinedApi")
    private static final String SELECTION =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
            Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?" :
            Contacts.DISPLAY_NAME + " LIKE ?";
    // Defines a variable for the search string
    private String mSearchString;
    // Defines the array to hold values that replace the ?
    private String[] mSelectionArgs = { mSearchString };
    
    public void onItemClick(
        AdapterView<?> parent, View item, int position, long rowID) {
        // Get the Cursor
        Cursor cursor = parent.getAdapter().getCursor();
        // Move to the selected contact
        cursor.moveToPosition(position);
        // Get the _ID value
        mContactId = getLong(CONTACT_ID_INDEX);
        // Get the selected LOOKUP KEY
        mContactKey = getString(CONTACT_KEY_INDEX);
        // Create the contact's content Uri
        mContactUri = Contacts.getLookupUri(mContactId, mContactKey);
        /*
         * You can use mContactUri as the content URI for retrieving
         * the details for a contact.
         */
    }
    public class ContactsFragment extends Fragment implements
    LoaderManager.LoaderCallbacks<Cursor> {
    	
    }
// Called just before the Fragment displays its UI
public void onActivityCreated(Bundle savedInstanceState) {
    // Always call the super method first
    super.onActivityCreated(savedInstanceState);
    // Initializes the loader
    getLoaderManager().initLoader(0, null, this);
    

    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        /*
         * Makes search string into pattern and
         * stores it in the selection array
         */
        mSelectionArgs[0] = "%" + mSearchString + "%";
        // Starts the query
        return new CursorLoader(
                getActivity(),
                Contacts.CONTENT_URI,
                PROJECTION,
                SELECTION,
                mSelectionArgs,
                null
        );
    }
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Put the result Cursor in the adapter for the ListView
        mCursorAdapter.swapCursor(cursor);
    }
    
    public void onLoaderReset(Loader<Cursor> loader) {
        // Delete the reference to the existing Cursor
        mCursorAdapter.swapCursor(null);
    }
        
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
        if (id == R.id.button1) {
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
    	
    }
    */
}

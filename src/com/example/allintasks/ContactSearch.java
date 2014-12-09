package com.example.allintasks;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
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
//addition allows selection of contacts
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.AdapterView;
//<<<<<<< Updated upstream
import java.util.*;
import android.content.Intent;

public class ContactSearch extends Activity  {
	Button btn = (Button) findViewById(R.id.button);
	//btn.setOnClickListener(new View.OnClickListener() {

	    public void onClick(View v) {
	        myClick(v); /* my method to call new intent or activity */
	    }
	    public void myClick(View v) {
	        Intent intent = new Intent(this, MainActivity.class);
	        startActivity(intent);// for calling the activity
	    }
	//});
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

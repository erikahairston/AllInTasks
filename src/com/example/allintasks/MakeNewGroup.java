package com.example.allintasks;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import android.content.Intent;


public class MakeNewGroup extends Activity  
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
	        //Set the initial view of the app to be the stuff we created in activity_main.xm
	setContentView(R.layout.make_new_group);

	//make t be the textbox where our program will output data
	TextView t = (TextView) findViewById(R.id.output);

	//make it so the scroll bar on t will work
	//t.setMovementMethod(new ScrollingMovementMethod());

	//make it so when we write System.out.println in our code, it goes to t
	System.setOut(new TextViewPrintStream(this, t));
	
	//make editText be the textbox where the user can enter in data
	EditText editText = (EditText) findViewById(R.id.textIn);
	        editText.setOnEditorActionListener(new OnEditorActionListener() {
	            @Override
	            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	                boolean handled = false;
	                if (actionId == EditorInfo.IME_ACTION_DONE) {
	                //the below is the only line you have to understand
	                //we get to this point if the user clicked on the 'Done' key on the keyboard when typing text into the textbox
	                    //it calls the processSalaray method inside our CPSC112.java file, sending whatever the user typed into the textbox as a String parameter to the method
	                CPSC112.processSalary(v.getText().toString());
	                v.setText("");
	                    handled = true;
	                }
	                return handled;
	            }
	        });
	        System.out.print(""); 
}

	/* public static String makeNewGroup(String a, String b, String c, String d){
		
		
		
		return */
	//}


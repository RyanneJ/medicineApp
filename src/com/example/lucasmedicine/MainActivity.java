package com.example.lucasmedicine;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.lucasmedicine.menu.MenuHandler;

import models.MedicineList;
import models.Person;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private MySQLiteHelper database;
	TextView textViewName;
	String personName, now, answers;
	TextView date;
	MedicineList list;
	Button acceptBut, editBut;
	EditText ans1,ans2,ans3, ans4, ans5, ans6, ans7, ans8, ans9, ans10, ans11, ans12, ans13, ans14, ans15, ans16, ans17;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		database = new MySQLiteHelper(this);
        
		//name field
		personName = Person.getName(getBaseContext(), database);
		textViewName = (TextView) findViewById(R.id.textListWelcome); 
    	textViewName.setText("Hallo " + personName);
    	
    	//date field, 
    	//vergeet hier geen knoppen later te maken om van datum te veranderen. (dialog of dag naar voor/dag naar achter)
    	Calendar c = Calendar.getInstance();
    	date = (TextView) findViewById(R.id.textListToday); 
    	SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
        now = df.format(Calendar.getInstance().getTime());
    	
    	date.setText("  " + now);
    	
    	
    	//save all to database
    	ans1 = (EditText) findViewById(R.id.editListQuestion1); ans2 = (EditText) findViewById(R.id.editListQuestion2);
    	ans3 = (EditText) findViewById(R.id.editListQuestion3); ans4 = (EditText) findViewById(R.id.editListQuestion4);
    	ans5 = (EditText) findViewById(R.id.editListQuestion5); ans6 = (EditText) findViewById(R.id.editListQuestion6);
    	ans7 = (EditText) findViewById(R.id.editListQuestion7); ans8 = (EditText) findViewById(R.id.editListQuestion8);
    	ans9 = (EditText) findViewById(R.id.editListQuestion9); ans10 = (EditText) findViewById(R.id.editListQuestion10);
    	ans11 = (EditText) findViewById(R.id.editListQuestion11); ans12 = (EditText) findViewById(R.id.editListQuestion12);
    	ans13 = (EditText) findViewById(R.id.editListQuestion13); ans14 = (EditText) findViewById(R.id.editListQuestion14);
    	ans15 = (EditText) findViewById(R.id.editListQuestion15); ans16 = (EditText) findViewById(R.id.editListQuestion16);
    	
    	acceptBut = (Button) findViewById(R.id.buttonListSave);
        acceptBut.setOnClickListener(new OnClickListener() { 
        	public void onClick(View v) {    
        		answers = ans1.getText().toString()+ ", " + ans2.getText().toString()+ ", " + ans3.getText().toString()+ ", ";
        		answers += ans3.getText().toString()+ ", " + ans4.getText().toString()+ ", " + ans5.getText().toString()+ ", ";
        		answers += ans6.getText().toString()+ ", " + ans7.getText().toString()+ ", " + ans8.getText().toString()+ ", ";
        		answers += ans9.getText().toString()+ ", " + ans10.getText().toString()+ ", " + ans11.getText().toString()+ ", ";
        		answers += ans12.getText().toString()+ ", " + ans13.getText().toString()+ ", " + ans14.getText().toString()+ ", ";
        		answers += ans15.getText().toString()+ ", " + ans16.getText().toString();
        		 	    	
        			addList(answers, now);
            		answers = "";
            		ans1.setText("");ans2.setText("");ans3.setText("");ans4.setText("");ans5.setText("");ans6.setText("");ans7.setText("");ans8.setText("");
            		ans9.setText("");ans10.setText("");ans11.setText("");ans12.setText("");ans13.setText("");ans14.setText("");ans15.setText("");ans16.setText("");
            		
        	}
        	
        	
		private void addList(String answers, String now) {
			 Uri uri = Uri.parse("" + DBProvider.LIST_URI);
			 	
	    	    ContentValues values = new ContentValues();
	    		values.put("date", now);
	    		values.put("answers", answers);
	    		
	    		getContentResolver().insert(uri, values);	    
			
		}
        });  

	}
	
	public boolean onCreateOptionsMenu(Menu menu) {		 
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_menu, menu);
		return true;		 
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		startActivity(MenuHandler.getMenu(this, item));
		return super.onOptionsItemSelected(item);	 
	}
	
}

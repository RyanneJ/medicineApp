package com.example.lucasmedicine;

import com.example.lucasmedicine.R;
import com.example.lucasmedicine.R.layout;
import com.example.lucasmedicine.R.menu;
import com.example.lucasmedicine.menu.MenuHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class HelpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		
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
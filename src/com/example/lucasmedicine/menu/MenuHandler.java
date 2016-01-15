package com.example.lucasmedicine.menu;

import com.example.lucasmedicine.HelpActivity;
import com.example.lucasmedicine.MainActivity;
import com.example.lucasmedicine.R;
import com.example.lucasmedicine.SettingActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

public abstract class MenuHandler {
	public static Intent getMenu(Activity activity, MenuItem item){
		switch (item.getItemId()) {			 
			case R.id.helpMen:			
				activity.finish();
			 	return new Intent(activity, HelpActivity.class);		 
			case R.id.settingsMen:
				activity.finish();
			 	return new Intent(activity, SettingActivity.class);		 	 
			default:
				activity.finish();
				return new Intent(activity, MainActivity.class);
		}
	}
}

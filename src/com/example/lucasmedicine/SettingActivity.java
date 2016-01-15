package com.example.lucasmedicine;

import java.util.Calendar;

import models.Alarm;
import models.Person;

import com.example.lucasmedicine.menu.MenuHandler;

import dialogs.ChangeAlarmDialog;
import dialogs.ChangeBDayDialog;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SettingActivity extends Activity {

	EditText naam;
	TextView textgeboorte, textAlarm;
	Button changedateButton, changeNameButton, changeAlarmButton;
	
	private MySQLiteHelper database;
	private TextView tvDisplayDate;

	private String birthdate;

	static final int DATE_DIALOG_ID = 999;
	
	
	
	Person person;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		database = new MySQLiteHelper(this);
		
		naam = (EditText) findViewById(R.id.editSettingsNaam);
		naam.setText(Person.getName(getBaseContext(), database));
		changeNameButton = (Button) findViewById(R.id.editsavename);
	   
		//listener name save
		changeNameButton.setOnClickListener(new OnClickListener() { 
        	public void onClick(View v) {  
        		Uri uri = Uri.parse("" + DBProvider.PEOPLES_URI);
        	    String naamVerander = naam.getText().toString();

        		ContentValues values = new ContentValues();
        		values.put("name", naamVerander);
        		getContentResolver().update(uri, values, null, null);

        		naam.setText(Person.getName(getBaseContext(), database));
        	}
		});
        	
		
		textgeboorte = (TextView) findViewById(R.id.textSettingsGeboorte);
		tvDisplayDate = (TextView) findViewById(R.id.geboorte);
		
		birthdate = Person.getBDay(getBaseContext(), database);
		tvDisplayDate.setText(birthdate);
		
		changedateButton = (Button) findViewById(R.id.btnChangeDate);
		changedateButton.setOnClickListener(new OnClickListener() { 
        	public void onClick(View v) {  
				  
        		ChangeBDayDialog changeTheDate = new ChangeBDayDialog("Verander geboortedag", tvDisplayDate, getContentResolver());
        	    changeTheDate.show(getFragmentManager(), "datePicker");

        	}
		});
		
		textAlarm = (TextView) findViewById(R.id.textAlarmNow);
		textAlarm.setText(Alarm.getCurrentHour(getBaseContext(), database) + " : " + Alarm.getCurrentMinute(getBaseContext(), database));
		
		
		changeAlarmButton = (Button) findViewById(R.id.buttonSettingsChangeAlarm);
		changeAlarmButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ChangeAlarmDialog changeAlarm = new ChangeAlarmDialog("verander alarm", textAlarm, getBaseContext(), getContentResolver());
				changeAlarm.show(getFragmentManager(), "timepicker");
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


	public static void saveTheDate(String date, ContentResolver resolver) {
		Uri uri = Uri.parse("" + DBProvider.PEOPLES_URI);

		ContentValues values = new ContentValues();
		values.put("birthdate", date);
		resolver.update(uri, values, null, null);

	}


	public static void saveTheTime(String time, int hour, int minute, Context context, ContentResolver resolver) {
		Uri uri = Uri.parse("" + DBProvider.ALARM_URI);

		ContentValues values = new ContentValues();
		values.put("timehour", hour);
		values.put("timeminute", minute);
		resolver.update(uri, values, null, null);
		
		
		 AlarmManager alarmMgr;
		 PendingIntent alarmIntent;

		 alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		 Intent intent = new Intent(context, AlarmReceiver.class);
		 alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		 
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);

		alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);

	}
	
	
}

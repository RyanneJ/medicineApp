package models;

import java.util.ArrayList;

import com.example.lucasmedicine.MySQLiteHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class Alarm {
	public final static String PROVIDER = "com.example.lucasmedicine.menu.DBProvider/alarms";
	public static int timeHour;
	public static int timeMinute;
	public String alarmTone, name; 
	private int id;
	public Uri tone;
	int day, month, year;
	

	public Alarm() {
	}
	
	public static ArrayList<Alarm> getAll(MySQLiteHelper db) {
		ArrayList<Alarm> alarmList = new ArrayList<Alarm>();
		String selectQuery = "SELECT * FROM " + db.getAlarmTableName();
		 
	    SQLiteDatabase dab = db.getWritableDatabase();
	    Cursor cursor = dab.rawQuery(selectQuery, null);

	    if (cursor.moveToFirst()) {
	        do {
	            Alarm alarm = new Alarm();
	            alarm.setID(Integer.parseInt(cursor.getString(0)));
	            alarm.setTimeHour(Integer.parseInt(cursor.getString(1)));
	            alarm.setTimeMinute(Integer.parseInt(cursor.getString(2)));
	            alarm.setSound(cursor.getString(3));
	           
	            alarmList.add(alarm);
	            
	        } while (cursor.moveToNext());
	    }
	    return alarmList;		
	}
    @Override
    public String toString(){
    	return timeHour+":"+timeMinute;
    	
    }

	public static void setTimeMinute(int minute) {
		timeMinute = minute;
	}

	public static void setTimeHour(int hour) {
		timeHour = hour;
	}

	private void setSound(String tone) {
		alarmTone = tone;
	}

	private void setID(int id) {
		this.id = id;	
	}

	public static int getCurrentHour(Context context, MySQLiteHelper db) {
		String selectQuery = "SELECT * FROM " + db.getAlarmTableName();
		 
	    SQLiteDatabase dab = db.getWritableDatabase();
	    Cursor cursor = dab.rawQuery(selectQuery, null);

	    if (cursor.moveToFirst()) {
	        do {
	        	timeHour = cursor.getInt(1);
	        } while (cursor.moveToNext());
	    }
	    return timeHour;
	}

	public static int getCurrentMinute(Context context, MySQLiteHelper db) {
		String selectQuery = "SELECT * FROM " + db.getAlarmTableName();
		 
	    SQLiteDatabase dab = db.getWritableDatabase();
	    Cursor cursor = dab.rawQuery(selectQuery, null);

	    if (cursor.moveToFirst()) {
	        do {
	        	timeMinute = cursor.getInt(2);
	        } while (cursor.moveToNext());
	    }
	    return timeMinute;
	}

	public int getID() {
		return id;
	}

	public String getAlarmTone() {
		return alarmTone;
	}
	
	public void setAlarmTone(String tone) {
		this.alarmTone = tone;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}


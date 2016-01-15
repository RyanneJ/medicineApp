package com.example.lucasmedicine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "LucasMedicine.db";
    // Table Names
    private static final String PEOPLE_TABLE_NAME = "people";
    private static final String LIST_TABLE_NAME = "list";
    private static final String ALARMS_TABLE_NAME = "alarm";
    
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {    	
    	String CREATE_PERSON_TABLE = "CREATE TABLE "+PEOPLE_TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, birthdate TEXT NOT NULL)"; 
        db.execSQL(CREATE_PERSON_TABLE);
        
        String CREATE_LIST_TABLE = "CREATE TABLE "+LIST_TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT NOT NULL, answers TEXT NOT NULL)"; 
        db.execSQL(CREATE_LIST_TABLE);
        
        String CREATE_ALARM_TABLE = "CREATE TABLE "+ALARMS_TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, timehour TEXT NOT NULL, timeminute TEXT NOT NULL, sound TEXT)"; 
        db.execSQL(CREATE_ALARM_TABLE);
        
        //FOR TESTING PURPOSES:
        db.execSQL("INSERT INTO "+PEOPLE_TABLE_NAME+" ('name', 'birthdate') VALUES ('Ryanne', '06-06-1988')");
        db.execSQL("INSERT INTO "+LIST_TABLE_NAME+" ('date', 'answers') VALUES ('5-6-2015', '5,1,4,2,6,3,6,7,1,3,4,6,4')");
        db.execSQL("INSERT INTO "+ALARMS_TABLE_NAME+" ('timehour', 'timeminute', 'sound') VALUES ('17', '00', 'Crazy Train.mp3')");
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+PEOPLE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+LIST_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ALARMS_TABLE_NAME);
        
        this.onCreate(db);
    }
    
    public String getPeopleTableName(){
		return PEOPLE_TABLE_NAME;
    }
    
    public String getListTableName(){
		return LIST_TABLE_NAME;
    }

	public String getAlarmTableName() {
		return ALARMS_TABLE_NAME;
	}
}
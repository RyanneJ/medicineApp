package models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lucasmedicine.MySQLiteHelper;

public class Person {
	public final static String PROVIDER = "com.example.lucasmedicine.controllers.DBProvider/Person";
    static String name, bday;
    static int id;
	
	public static String getName(Context context, MySQLiteHelper db) {
		//ArrayList<Person> persons = new ArrayList<Person>();
		String selectQuery = "SELECT * FROM " + db.getPeopleTableName();
		 
	    SQLiteDatabase dab = db.getWritableDatabase();
	    Cursor cursor = dab.rawQuery(selectQuery, null);

	    if (cursor.moveToFirst()) {
	        do {
	            id = Integer.parseInt(cursor.getString(0));
	            name = cursor.getString(1);
	        } while (cursor.moveToNext());
	    }
	    return name;		
	}
	
	 @Override
	    public String toString(){
	    	return name;
	    }

	 public void setName(String name){
	    	this.name = name;
	    }

	 public static String getBDay(Context context, MySQLiteHelper db) {
			String selectQuery = "SELECT * FROM " + db.getPeopleTableName();
			 
		    SQLiteDatabase dab = db.getWritableDatabase();
		    Cursor cursor = dab.rawQuery(selectQuery, null);

		    if (cursor.moveToFirst()) {
		        do {
		            bday = cursor.getString(2);
		        } while (cursor.moveToNext());
		    }
		    return bday;		
	 }
	    
	 public static void setBDay(String date){
		 bday = date;
	 }
	 
	 public static String getChangedBDay() {
		 return bday;
	 }
	    public int getID(){
	        return id;
	    }
	    
	    public void setID(int id){
	    	this.id = id;
	    }
	    
}
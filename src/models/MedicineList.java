package models;

import java.util.ArrayList;

import com.example.lucasmedicine.MySQLiteHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MedicineList {

	private int id;
	private String answers;
	private String now;
	
	public MedicineList() {}
	
	public MedicineList(int id, String answers, String now) {
		this.id = id;
		this.answers = answers;
		this.now = now;
	}
	
	public static ArrayList<MedicineList> getAll(Context context, MySQLiteHelper db) {
		ArrayList<MedicineList> answerList = new ArrayList<MedicineList>();
		String selectQuery = "SELECT * FROM " + db.getListTableName();
		 
	    SQLiteDatabase dab = db.getWritableDatabase();
	    Cursor cursor = dab.rawQuery(selectQuery, null);

	    if (cursor.moveToFirst()) {
	        do {
	            MedicineList allOnList = new MedicineList();
	            allOnList.setID(Integer.parseInt(cursor.getString(0)));
	            allOnList.setAnswers(cursor.getString(1));

	            answerList.add(allOnList);
	        } while (cursor.moveToNext());
	    }
	    return answerList;		
	}
	
	public int getID(){
        return id;
    }
    
	public String getNow() {
		return now;
	}
    public void setID(int id){
    	this.id = id;
    }
    
    public String getAnswers(){
    	return answers;
    }
    
    public void setAnswers(String ans){
    	this.answers = ans;
    } 
}
package com.example.lucasmedicine;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class DBProvider extends ContentProvider{
	private MySQLiteHelper mHelper;
	private SQLiteDatabase database; 
	
	static final String PROVIDER_NAME = "com.example.lucasmedicine.DBProvider";
	
	static final String TABLE_PEOPLE = "people";
	static final String TABLE_LIST = "list";
	static final String TABLE_ALARM = "alarm";
	
	static final int PEOPLES_ID = 2;
	static final int LIST_ID = 3;
	static final int ALARM_ID = 4;
	
	public static final Uri PEOPLES_URI = Uri.parse("content://"+PROVIDER_NAME+"/"+TABLE_PEOPLE);
	public static final Uri LIST_URI = Uri.parse("content://"+PROVIDER_NAME+"/"+TABLE_LIST);
	public static final Uri ALARM_URI = Uri.parse("content://" + PROVIDER_NAME+ "/" + TABLE_ALARM);
	
	private static final UriMatcher uriMatcher;
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(PROVIDER_NAME, TABLE_PEOPLE, PEOPLES_ID);
		uriMatcher.addURI(PROVIDER_NAME, TABLE_LIST, LIST_ID);
		uriMatcher.addURI(PROVIDER_NAME, TABLE_ALARM, ALARM_ID);
	}  
	
	@Override
	public boolean onCreate() {
		mHelper  = new MySQLiteHelper(getContext());
		database = mHelper.getWritableDatabase();
		this.resetDatabase();
		return false;
	}
	public void resetDatabase()
	{
		mHelper.onUpgrade(mHelper.getWritableDatabase(), 0, 0);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
		
		sqlBuilder.setTables(getTable(uri));
		
		Cursor c = sqlBuilder.query(database, projection, selection, selectionArgs, null, null,	sortOrder);
		
		// register to watch a content URI for changes
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
    public String getType(Uri uri) {
		if(uriMatcher.match(uri) == PEOPLES_ID){
            return "vdn.android.cursor.dir/"+ PROVIDER_NAME + "." + TABLE_PEOPLE;
        } else if(uriMatcher.match(uri) == LIST_ID){
        	return "vdn.android.cursor.dir/"+ PROVIDER_NAME + "." + TABLE_LIST;
        } else if(uriMatcher.match(uri) == ALARM_ID){
        	return "vdn.android.cursor.dir/"+ PROVIDER_NAME + "." + TABLE_ALARM;
        }
        return null;
    }
	
	public String getTable(Uri uri) {
		if(uriMatcher.match(uri) == PEOPLES_ID){
				return TABLE_PEOPLE;
		} else if(uriMatcher.match(uri) == LIST_ID){
				return TABLE_LIST;
		} else if(uriMatcher.match(uri) == ALARM_ID) {
				return TABLE_ALARM;
		}
		
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long rowID = database.insert(getTable(uri), null, values);

		// If inserted successfully
		if (rowID > 0) {
			Uri _uri = null;
			
			if(getTable(uri) == TABLE_PEOPLE){
				_uri = ContentUris.withAppendedId(PEOPLES_URI, rowID);
			} else if(getTable(uri) == TABLE_LIST) {
				_uri = ContentUris.withAppendedId(LIST_URI, rowID);
			}
			
			getContext().getContentResolver().notifyChange(_uri, null);
			return _uri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public int delete(Uri uri, String where, String[] selectionArgs) {
		database.delete(getTable(uri), where, selectionArgs);		
		int rowID = Integer.parseInt(selectionArgs[0]);
		return rowID;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where, String[] selectionArgs) {
		int numRows = database.update(getTable(uri), values, where, selectionArgs);		
		return numRows;
	}
}
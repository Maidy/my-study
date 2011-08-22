package kr.yui.android.sqlitebasics;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyLocationDBHelper extends SQLiteOpenHelper {
	
	public static final int VERSION = 1;
	public static final String DB_NAME = "mylocation.sqlite";
	
	public static final String LOCATION_TABLE = "mylocation";
	public static final String LOCATION_SEQ = "seq";
	public static final String LOCATION_NAME = "name";
	public static final String LOCATION_LONGITUDE = "lon";
	public static final String LOCATION_LATITUDE = "lat";
	
	private SQLiteDatabase db;
	
	public MyLocationDBHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		this.db = this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		createTable();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + LOCATION_TABLE);
		createTable();
	}

	private void createTable() {
		db.execSQL("CREATE TABLE " + LOCATION_TABLE + "(" +
				LOCATION_SEQ + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +
				LOCATION_NAME + " TEXT, " +
				LOCATION_LATITUDE + "INTEGER, " + 
				LOCATION_LONGITUDE + "INTEGER " +
				")");
	}
	
	public MyLocation getLocation(String name) {
		MyLocation loc = null;
		
		Cursor cursor = db.query(LOCATION_TABLE,
				new String[] { LOCATION_NAME, LOCATION_LATITUDE, LOCATION_LONGITUDE },
				LOCATION_NAME + "= ?",
				new String[] { name },
				null,
				null,
				null);
		
		for (;!cursor.isAfterLast();cursor.moveToNext()) {
			String n = cursor.getString(0);
			int lat = cursor.getInt(1);
			int lon = cursor.getInt(2);
			loc = new MyLocation(n, lat, lon);
		}
		
		return loc;
	}
	
	public ArrayList<MyLocation> getLocations() {
		ArrayList<MyLocation> list = new ArrayList<MyLocation>();
		
		Cursor cursor = db.query(LOCATION_TABLE,
				new String[] { LOCATION_SEQ, LOCATION_NAME, LOCATION_LATITUDE, LOCATION_LONGITUDE },
				LOCATION_NAME + "= ?",
				null,
				null,
				null,
				LOCATION_SEQ);
		
		for (;!cursor.isAfterLast();cursor.moveToNext()) {
			int s = cursor.getInt(0);		// seq
			String n = cursor.getString(1);	// name
			int lat = cursor.getInt(2);		// latitude
			int lon = cursor.getInt(3);		// longitude
			
			MyLocation loc = new MyLocation(n, lat, lon);
			loc.setSeq(s);
			
			list.add(loc);
		}
		
		return list;
	}
	
	public void insertLocation(MyLocation loc) {
		
		assert (null != loc);
		
		ContentValues values = new ContentValues();
		values.put(LOCATION_NAME, loc.getName());
		values.put(LOCATION_LATITUDE, loc.getLatitude());
		values.put(LOCATION_LONGITUDE, loc.getLongitude());
		
		db.insert(LOCATION_TABLE, null, values);
	}
	
	public void deleteLocation(long id) {
		String where = LOCATION_SEQ + " in (" + id + ")";
		db.delete(LOCATION_TABLE, where, null);
	}
	
}

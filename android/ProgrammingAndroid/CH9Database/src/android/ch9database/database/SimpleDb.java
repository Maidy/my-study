package android.ch9database.database;

import android.ch9database.R;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SimpleDb extends SQLiteOpenHelper {
	
	private static final String TAG = SimpleDb.class.getSimpleName();
	
	public static final String VIDEO_TABLE_NAME = "video";
	private static final String SIMPLE_VIDEO = "my_simple_video";

	public static final String DATABASE_NAME = SIMPLE_VIDEO + ".db";
	private static final int DATABASE_VERSION = 1;
	
	public static final int COLUMN_ID = 0;
	public static final int COLUMN_TITLE = 1;
	public static final int COLUMN_DESCRIPTION = 2;
	public static final int COLUMN_TIMESTAMP = 3;
	public static final int COLUMN_QUERY_TEXT = 4;
	public static final int COLUMN_MEDIA_ID = 5;
	
	final private Context mContext;
	
	public SimpleDb(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "onCreate");
		
		String sql[] = mContext.getString(R.string.MicroJobsDatabase_onCreate).split("\n");
		
		db.beginTransaction();
		try {
			execMultipleSQL(db, sql);
			db.setTransactionSuccessful();
		} catch(SQLException e) {
			Log.e(TAG, e.toString());
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "onUpgrade");
		
		String sql[] = mContext.getString(R.string.MicroJobsDatabase_onUpgrade).split("\n");
		
		db.beginTransaction();
		try {
			execMultipleSQL(db, sql);
			db.setTransactionSuccessful();
		} catch(SQLException e) {
			Log.e(TAG, e.toString());
		} finally {
			db.endTransaction();
		}
		
		onCreate(db);
	}

	private void execMultipleSQL(SQLiteDatabase db, String[] sql){
        for( String s : sql ) {
            if (s.trim().length()>0) {
                db.execSQL(s);
            }
        }
    }
}

package android.ch9database;

import android.os.Bundle;
import android.app.Activity;
import android.ch9database.database.SimpleDb;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private SimpleDb sdb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.d(TAG, "onCreate");
		
		sdb = new SimpleDb(this);
		Cursor cursor = sdb.getReadableDatabase().rawQuery("SELECT * FROM jobs", new String[] { });
		if (cursor.moveToFirst()) {
			do {
				Log.d(TAG, cursor.getString(2) + " : " + cursor.getString(3));
			} while (cursor.moveToNext());
		}
		
//		ContentValues v = new ContentValues();
//		v.put(key, value)

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

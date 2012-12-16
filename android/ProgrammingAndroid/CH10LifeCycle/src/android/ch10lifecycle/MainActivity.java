package android.ch10lifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private final String TAG = MainActivity.class.getSimpleName();
	private final String TAG_STATE = "answer";
	private final String RESTORE = ", can restore state";
	
	private final String state = "fortytwo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String answer = null;
		if (null != savedInstanceState) {
			answer = savedInstanceState.getString(TAG_STATE);
		}
		Log.i(TAG, "OnCreate" + ((null != savedInstanceState) ? (RESTORE + answer) : ""));
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(TAG, "OnRestart");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "OnStart");
	}
	
	@Override
	protected void onResume() {
		Log.i(TAG, "OnResume");
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "OnPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy " + Integer.toString(getChangingConfigurations(), 16));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString(TAG_STATE, state);
		super.onSaveInstanceState(outState);
		Log.i(TAG, "OnRestart");
	}
	
	@Override
	@Deprecated
	public Object onRetainNonConfigurationInstance() {
		Log.i(TAG, "onRetainNonConfigurationInstance");
		return super.onRetainNonConfigurationInstance();
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		
		String answer = null != savedInstanceState ? savedInstanceState.getString(TAG_STATE) : "";
		
		Object oldTaskObject = getLastNonConfigurationInstance();
		if (null != oldTaskObject) {
			int oldTask = ((Integer) oldTaskObject).intValue();
			int currentTask = getTaskId();
			assert oldTask == currentTask;
		}
		
		Log.i(TAG, "onRestoreInstanceState" + 
				(null == savedInstanceState ? "" : RESTORE) + " " + answer);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		
		String answer = null;
		if (null != savedInstanceState) {
			answer = savedInstanceState.getString(TAG_STATE);
		}
		Log.i(TAG, "onPostCreate" +
				(null != savedInstanceState ? (RESTORE + answer) : ""));
	}
	
	@Override
	protected void onPostResume() {
		super.onPostResume();
		Log.i(TAG, "onPostResume");
	}
	
	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		Log.i(TAG, "onUserLeaveHint");
	}

}

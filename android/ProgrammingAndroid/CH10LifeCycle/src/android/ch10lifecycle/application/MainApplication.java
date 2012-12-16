package android.ch10lifecycle.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

public class MainApplication extends Application {
	
	public final String TAG = this.getClass().getSimpleName();

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "onCreate");
	}
	
	@Override
	public void onTerminate() {
//		super.onTerminate();
		Log.i(TAG, "onTerminate");
	}
	
	@Override
	public void onLowMemory() {
//		super.onLowMemory();
		Log.i(TAG, "onLowMemory");
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
//		super.onConfigurationChanged(newConfig);
		Log.i(TAG, "onConfigurationChanged");
		if (Log.isLoggable(TAG, Log.VERBOSE)) {
			Log.v(TAG, newConfig.toString());
		}
	}
	
	@SuppressLint("NewApi")
	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
		Log.i(TAG, "onTrimMemory : " + String.valueOf(level));
	}

}

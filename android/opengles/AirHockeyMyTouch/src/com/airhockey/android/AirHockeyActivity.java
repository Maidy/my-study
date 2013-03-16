package com.airhockey.android;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class AirHockeyActivity extends Activity {
	
	protected static final String TAG = "AirHockeyActivity";
	private GLSurfaceView glSurfaceView;
	private boolean rendererSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView = new GLSurfaceView(this);
        
        final ActivityManager activityManager =
        		(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        
        final ConfigurationInfo configurationInfo =
        		activityManager.getDeviceConfigurationInfo();
        
        // OpenGL ES2 지원 여부, Build 버전 체크는 simulator 때문
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000
        		|| (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
        		&& (Build.FINGERPRINT.startsWith("generic")
        				|| Build.MODEL.contains("google_sdk") 
        				|| Build.MODEL.contains("Emulator")));
        
        final AirHockeyRenderer airHockeyRenderer = new AirHockeyRenderer(this);
        
        if (supportsEs2) {
        	glSurfaceView.setEGLContextClientVersion(2);
        	glSurfaceView.setRenderer(airHockeyRenderer);
        	rendererSet = true;
        } else {
        	rendererSet = false;
        	Toast.makeText(this, "This device does not support OpenGL ES 2.0",
        			Toast.LENGTH_LONG).show();
        	return;
        }
        
        glSurfaceView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event == null) return false;
				
				// event x, y를 normalized device coordinate로 변환한다.
				// normalized device coordinate는 -1 ~ 1 범위를 가진다.
				// y축은 반대 방향이다.
				final float normalizedX = (event.getX() / (float) v.getWidth()) * 2 - 1;
				final float normalizedY = -((event.getY() / (float) v.getHeight()) * 2 - 1);
				
//				Log.d(TAG, "normalized: " + normalizedX + "/" + normalizedY);
				
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					glSurfaceView.queueEvent(new Runnable() {
						@Override
						public void run() {
							airHockeyRenderer.handleTouchPress(normalizedX, normalizedY);
						}
					});
					break;
				case MotionEvent.ACTION_MOVE:
					glSurfaceView.queueEvent(new Runnable() {
						@Override
						public void run() {
							airHockeyRenderer.handleTouchDrag(normalizedX, normalizedY);
						}
					});
					break;
				}
				
				return true;
			}
		});
        
        setContentView(glSurfaceView);
    }

    @Override
    protected void onPause() {
    	super.onPause();
    	if (rendererSet)
    		glSurfaceView.onPause();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	if (rendererSet)
    		glSurfaceView.onResume();
    }
}

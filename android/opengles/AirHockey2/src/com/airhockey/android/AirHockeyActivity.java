package com.airhockey.android;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class AirHockeyActivity extends Activity {
	
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
        
        if (supportsEs2) {
        	glSurfaceView.setEGLContextClientVersion(2);
        	glSurfaceView.setRenderer(new AirHockeyRenderer(this));
        	rendererSet = true;
        } else {
        	rendererSet = false;
        	Toast.makeText(this, "This device does not support OpenGL ES 2.0",
        			Toast.LENGTH_LONG).show();
        	return;
        }
        
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
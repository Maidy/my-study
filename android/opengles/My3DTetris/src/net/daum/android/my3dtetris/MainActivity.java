package net.daum.android.my3dtetris;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GLSurfaceView mGlSurfaceView;
	private boolean mRendererSet = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mGlSurfaceView = new GLSurfaceView(this);
		
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
        
        final MainRenderer mainRenderer = new MainRenderer(this);
		
        
        if (supportsEs2) {
        	mGlSurfaceView.setEGLContextClientVersion(2);
        	mGlSurfaceView.setRenderer(mainRenderer);
        	mRendererSet = true;
        } else {
        	mRendererSet = false;
        	Toast.makeText(this, "This device does not support OpenGL ES 2.0",
        			Toast.LENGTH_LONG).show();
        	return;
        }
        
        mGlSurfaceView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event == null) return false;
				
				// event x, y를 normalized device coordinate로 변환한다.
				// normalized device coordinate는 -1 ~ 1 범위를 가진다.
				// y축은 반대 방향이다.
				final float normalizedX = (event.getX() / (float) v.getWidth()) * 2 - 1;
				final float normalizedY = -((event.getY() / (float) v.getHeight()) * 2 - 1);
				
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					mGlSurfaceView.queueEvent(new Runnable() {
						@Override
						public void run() {
							mainRenderer.handleTouchPress(normalizedX, normalizedY);
						}
					});
					break;
				case MotionEvent.ACTION_MOVE:
					mGlSurfaceView.queueEvent(new Runnable() {
						@Override
						public void run() {
							mainRenderer.handleTouchDrag(normalizedX, normalizedY);
						}
					});
					break;
				}
				
				return true;
			}
		});
        
        setContentView(mGlSurfaceView);
        
	}


}

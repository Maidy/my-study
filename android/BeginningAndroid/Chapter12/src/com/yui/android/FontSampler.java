package com.yui.android;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class FontSampler extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		TextView tv = (TextView)findViewById(R.id.custom);
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Chalkduster.ttf");
		tv.setTypeface(tf);
	}
}
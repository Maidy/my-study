package com.yui.android.ch10;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class FilpperDemo extends Activity {
	
	ViewFlipper flipper;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filpperdemo);
		
		Button btn = (Button)findViewById(R.id.flip_me);
		flipper = (ViewFlipper)findViewById(R.id.details);
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				flipper.showNext();
			}
		});
	}
}

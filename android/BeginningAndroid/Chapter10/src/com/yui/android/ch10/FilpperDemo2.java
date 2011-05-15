package com.yui.android.ch10;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

public class FilpperDemo2 extends Activity {

	String[] items = { "lorem", "ipsum", "dolor", "sit", "amet",
			"consectetuer", "adipiscing", "elit", "morbi", "vel",
			"etiam", "vel", "erat", "placerat", "ante",
			"porttitor", "sodales", "pellentesque", "augue", "purus" };
	
	ViewFlipper flipper;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filpperdemo2);
		
		flipper = (ViewFlipper)findViewById(R.id.details2);
		flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
		flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
		
		System.out.println("===================");
		
		for (String item : items) {
			Button btn = new Button(this);
			btn.setText(item);
			
			flipper.addView(btn, new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.FILL_PARENT));
		}
		
		flipper.setFlipInterval(2000);
		flipper.startFlipping();
	}
}

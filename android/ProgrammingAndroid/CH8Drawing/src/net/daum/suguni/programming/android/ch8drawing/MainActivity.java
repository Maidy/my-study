package net.daum.suguni.programming.android.ch8drawing;

import net.daum.suguni.programming.android.ch8drawing.drawable.HelloAndroidTextDrawable;
import net.daum.suguni.programmingandroid.ch8drawing.view.TrivialWidget;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

//	@SuppressWarnings("deprecation")
//	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//				LayoutParams.MATCH_PARENT, 2000);
//		params.weight = 1;
//
//		TrivialWidget widget = (TrivialWidget) findViewById(R.id.main_trivialwidget);
//		widget.setText("HELLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//		widget.setLayoutParams(params);
		
//		LinearLayout container = (LinearLayout) findViewById(R.id.main_container);
//		container.addView(widget, 0);
		
		ImageView canvas = (ImageView) findViewById(R.id.main_canvas);
		
		AnimationDrawable ani = new AnimationDrawable();
		ani.addFrame(getResources().getDrawable(R.drawable.bh), 200);
		ani.addFrame(getResources().getDrawable(R.drawable.bw), 200);
		ani.addFrame(getResources().getDrawable(R.drawable.clojure), 200);
		ani.setOneShot(false);

//		int sdk = android.os.Build.VERSION.SDK_INT;
//		if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//			canvas.setBackgroundDrawable(ani);
//		} else {
//		    canvas.setBackground(ani);
//		}
		canvas.setImageDrawable(ani);
		
		findViewById(R.id.main_start).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ImageView canvas = (ImageView) findViewById(R.id.main_canvas);
				((AnimationDrawable)canvas.getDrawable()).start();
				
//				AnimationDrawable ani = ((AnimationDrawable) canvas.getBackground());
//				Drawable d = ani.getFrame(1);
//				canvas.setImageDrawable(d);
//				ani.start();
				
//				((AnimationDrawable) canvas.getBackground()).start();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

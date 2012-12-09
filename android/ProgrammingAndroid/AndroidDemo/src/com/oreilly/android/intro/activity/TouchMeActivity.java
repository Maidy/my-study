package com.oreilly.android.intro.activity;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.oreilly.android.intro.R;
import com.oreilly.android.intro.R.id;
import com.oreilly.android.intro.R.layout;
import com.oreilly.android.intro.R.menu;
import com.oreilly.android.intro.model.Dot;
import com.oreilly.android.intro.model.Dots;
import com.oreilly.android.intro.view.DotView;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class TouchMeActivity extends Activity {

	public static final int DOT_DIAMETER = 6;
    private Random rand = new Random();
    final Dots dotModel = new Dots();
    DotView dotView;
    
    DotGenerator dotGenerator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch_me);
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        		ViewGroup.LayoutParams.MATCH_PARENT, // ???
        		ViewGroup.LayoutParams.MATCH_PARENT, // ???
        		1.0f);
		params.gravity = Gravity.CENTER;
		dotView = new DotView(this, dotModel);
		dotView.setFocusable(true);
		dotView.setLayoutParams(params);
		((LinearLayout) findViewById(R.id.touchme_root)).addView(dotView, 0);
		
        ((Button) findViewById(R.id.touchme_button1)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				makeDot(dotModel, dotView, Color.RED);
				dotView.requestFocus();
			}
		});
        
        ((Button) findViewById(R.id.touchme_button2)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				makeDot(dotModel, dotView, Color.BLUE);
				dotView.clearFocus();
			}
		});

        final EditText tb1 = (EditText) findViewById(R.id.touchme_text1);
        final EditText tb2 = (EditText) findViewById(R.id.touchme_text2);
        
        dotModel.setDotsChangeListener(new Dots.DotsChangedListener() {
			
			@Override
			public void onDotsChange(Dots dots) {
				Dot d = dots.getLastDot();
				tb1.setText((null == d) ? "" : String.valueOf(d.getX()));
				tb2.setText((null == d) ? "" : String.valueOf(d.getY()));
				dotView.invalidate();
			}
		});
        
//        dotView.setOnTouchListener(new View.OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				if (MotionEvent.ACTION_DOWN == event.getAction()) {
//					dotModel.addDot(event.getX(), event.getY(), Color.CYAN, DOT_DIAMETER);
//					return true;
//				}
//				return false;
//			}
//		});
        
        dotView.setOnTouchListener(new TrackingTouchListener(dotModel));
//        dotView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//			
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				Log.d("ToucheMeActivity", "FOCUS!!!");
//				if (!hasFocus && (null != dotGenerator)) {
//					dotGenerator.done();
//					dotGenerator = null;
//				} else {
//					dotGenerator = new DotGenerator(dotModel, dotView, Color.CYAN);
//					new Thread(dotGenerator).run();
//				}
//			}
//		});
        
//        dotView.requestFocus();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_touch_me, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.touchme_menu_clear:
			dotModel.clearDots();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void makeDot(Dots dots, DotView view, int color) {
		Log.d("TouchMeActivity", "MAKEDOT");
		int pad = (DOT_DIAMETER + 2) * 2;
		dots.addDot(DOT_DIAMETER + (rand.nextFloat() * (view.getWidth() - pad)),
				DOT_DIAMETER + (rand.nextFloat() * (view.getHeight() - pad)),
				color,
				DOT_DIAMETER);
	}
	
	private static final class TrackingTouchListener implements View.OnTouchListener {
		
		private final Dots mDots;
		private LinkedList<Integer> tracks;
		
		TrackingTouchListener(Dots dots) {
			mDots = dots;
			tracks = new LinkedList<Integer>();
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			int n, idx, action = event.getAction();
			
			switch (action & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				idx = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
				tracks.add(Integer.valueOf(event.getPointerId(idx)));
				break;
			
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
				idx = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
				tracks.remove(Integer.valueOf(event.getPointerId(idx)));
				break;
				
			case MotionEvent.ACTION_MOVE:
				n = event.getHistorySize();
				for (Integer i : tracks) {
					idx = event.findPointerIndex(i.intValue());
					for (int j = 0; j < n; j++) {
						addDot(mDots,
								event.getHistoricalX(idx, j),
								event.getHistoricalY(idx, j),
								event.getHistoricalPressure(idx, j),
								event.getHistoricalSize(idx, j));
					}
				}
				break;
				
			default:
				return false;
			}
			
			for (Integer i : tracks) {
				idx = event.findPointerIndex(i.intValue());
				addDot(mDots,
						event.getX(idx),
						event.getY(idx),
						event.getPressure(idx),
						event.getSize(idx));
			}
			
			return true;
		}

		private void addDot(Dots dots, float x, float y, float pressure, float size) {
			dots.addDot(x, y, Color.BLACK, (int)((pressure * size * Dot.DIAMETER) + 1));
			
		}
		
	}
	
	// ?????
	private final class DotGenerator implements Runnable {

		final Dots dots;
		final DotView view;
		final int color;
		
		private final Handler handler = new Handler();
		
		private final Runnable makeDots = new Runnable() {
			
			@Override
			public void run() {
				makeDot(dots, view, color);
			}
		};
		
		private volatile boolean done;
		
		DotGenerator(Dots dots, DotView view, int color) {
			this.dots = dots;
			this.view = view;
			this.color =color;
		}
		
		public void done() {
			done = true;
		}
		
		@Override
		public void run() {
			while (!done) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
				}
				Log.d("DotGenerator", "Run");
				handler.post(makeDots);
			}
		}
		
	}
}

package net.daum.suguni.programming.android.ch8drawing;

import net.daum.suguni.programming.android.ch8drawing.drawable.HelloAndroidTextDrawable;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class TrasnformationalActivity extends Activity {
	
	private interface Transformation {
		void transform(Canvas canvas);
		String describe();
	}
	
	private static class TransformedViewWidget extends View {
		
		private static final String TAG = TransformedViewWidget.class.getName();
		
		private final Transformation transformation;
		private final Paint paint = new Paint();
		private final Rect rect = new Rect();
		
		private final Drawable drawable;
		
		private Bitmap cache;

		public TransformedViewWidget(Context context, Drawable drawable, Transformation transformation) {
			super(context);
			
			this.drawable =drawable; 
			this.transformation = transformation;
			
			setMinimumWidth(160);
			setMinimumHeight(105);
		}
		
		public void invalidateCache() {
			cache = null;
			invalidate();
		}
		
		@Override
		public void invalidate() {
			invalidateCache();
			super.invalidate();
		}
		
		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			setMeasuredDimension(
					getSuggestedMinimumWidth(),
					getSuggestedMinimumHeight());
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			
			if (null == cache) {
				cache = Bitmap.createBitmap(getMeasuredWidth(),
						getMeasuredHeight(),
						Bitmap.Config.ARGB_8888);
				drawCachedBitmap(new Canvas(cache));
			}
			
			canvas.drawBitmap(cache, 0, 0, paint);
		}

		private void drawCachedBitmap(Canvas canvas) {
			paint.reset();
			
			canvas.drawColor(Color.WHITE);
			
			canvas.save();
			transformation.transform(canvas);
			
			drawable.draw(canvas);
			
			canvas.restore();
			
			paint.setColor(Color.BLACK);
			paint.setStyle(Paint.Style.STROKE);
			
			canvas.getClipBounds(rect);
			canvas.drawRect(rect, paint);
			
			paint.setTextSize(10);
			paint.setColor(Color.BLUE);
			canvas.drawText(transformation.describe(), 5, 100, paint);
		}
		
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trasnformational);
		
		final LinearLayout container1 = (LinearLayout) findViewById(R.id.transformational_container1);
		final LinearLayout container2 = (LinearLayout) findViewById(R.id.transformational_container2);
		final Drawable drawable = new HelloAndroidTextDrawable();
		
		Button button = (Button) findViewById(R.id.transformational_refresh);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				container1.invalidate();
			}
		});
		
		container1.addView(new TransformedViewWidget(this,
				drawable,
				new Transformation() {
					@Override
					public void transform(Canvas canvas) { }
					@Override
					public String describe() { return "identity"; }
		}));
		
		container1.addView(new TransformedViewWidget(this,
				drawable,
				new Transformation() {
					@Override
					public void transform(Canvas canvas) { canvas.rotate(-30f); }
					@Override
					public String describe() { return "rotate(-30)"; }
		}));
		
		container1.addView(new TransformedViewWidget(this,
				drawable,
				new Transformation() {
					@Override
					public void transform(Canvas canvas) { canvas.scale(0.5f, 0.8f); }
					@Override
					public String describe() { return "scale(0.5, 0.8)"; }
		}));
		
		container1.addView(new TransformedViewWidget(this,
				drawable,
				new Transformation() {
					@Override
					public void transform(Canvas canvas) { canvas.skew(0.1f, 0.3f); }
					@Override
					public String describe() { return "skew(0.1, 0.3)"; }
		}));
		
		container2.addView(new TransformedViewWidget(this,
				drawable,
				new Transformation() {
					@Override
					public void transform(Canvas canvas) { canvas.translate(30f, 10f); }
					@Override
					public String describe() { return "translate(30, 10)"; }
		}));
		
		container2.addView(new TransformedViewWidget(this,
				drawable,
				new Transformation() {
					@Override
					public void transform(Canvas canvas) {
						canvas.translate(110f, -20f);
						canvas.rotate(85f);
					}
					@Override
					public String describe() { return "translate(110, -20), rotate(85)"; }
		}));
		
		container2.addView(new TransformedViewWidget(this,
				drawable,
				new Transformation() {
					@Override
					public void transform(Canvas canvas) {
						canvas.translate(-50f, -20f);
						canvas.scale(2f, 1.2f);
					}
					@Override
					public String describe() { return "translate(-50, -50), scale(2, 1.2)"; }
		}));
		
		container2.addView(new TransformedViewWidget(this,
				drawable,
				new Transformation() {
					@Override
					public void transform(Canvas canvas) {
						canvas.translate(-100f, -100f);
						canvas.scale(2.5f, 2f);
						canvas.skew(0.1f, 0.3f);
					}
					@Override
					public String describe() { return "complex"; }
		}));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_trasnformational, menu);
		return true;
	}

}

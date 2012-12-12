package net.daum.suguni.programming.android.ch8drawing.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

public class HelloAndroidTextDrawable extends Drawable {

	private Paint paint = new Paint();
	private ColorFilter colorFilter;
	private int alpha;

	@Override
	public void draw(Canvas canvas) {
		paint.reset();
		
		paint.setColorFilter(colorFilter);
		paint.setAlpha(alpha);
		
		paint.setTextSize(12);
		paint.setColor(Color.GREEN);
		canvas.drawText("Hello", 40, 55, paint);
		
		paint.setTextSize(16);
		paint.setColor(Color.RED);
		canvas.drawText("Android", 35, 65, paint);
	}

	@Override
	public int getOpacity() {
		return PixelFormat.TRANSLUCENT;
	}

	@Override
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		this.colorFilter = cf;
	}

}

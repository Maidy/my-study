package net.daum.suguni.programmingandroid.ch8drawing.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class EffectWidget extends View {
	
	public interface PaintEffect {
		void setEffect(Paint paint);
	}
	
	@SuppressWarnings("unused")
	private static final String TAG = EffectWidget.class.getSimpleName();

	private Paint paint = new Paint();
	private Rect bound = new Rect();
	
	private PaintEffect effect;
	private int id;
	
	public EffectWidget(Context context, int id, PaintEffect effect) {
		super(context);
		
		this.effect = effect;
		this.id = id;
		
		setMinimumWidth(200);
		setMinimumHeight(200);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(
				getSuggestedMinimumWidth(), 
				getSuggestedMinimumHeight());
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		paint.reset();
		paint.setAntiAlias(true);
		
		effect.setEffect(paint);
		paint.setColor(Color.DKGRAY);
		
		paint.setStrokeWidth(5f);
		canvas.drawLine(10f, 10f, 140f, 20f, paint);
		
		paint.setTextSize(30f);
		canvas.drawText("Android", 40f, 50f, paint);
		
		paint.reset();
		paint.setColor(Color.BLACK);
		canvas.drawText(String.valueOf(id), 2f, 12f, paint);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2f);

		canvas.getClipBounds(bound);
		canvas.drawRect(bound, paint);
	}
	
}

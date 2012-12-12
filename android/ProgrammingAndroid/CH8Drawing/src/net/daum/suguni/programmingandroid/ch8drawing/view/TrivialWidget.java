package net.daum.suguni.programmingandroid.ch8drawing.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class TrivialWidget extends View {
	
	private static final String TAG = TrivialWidget.class.getSimpleName();
	Paint paint = new Paint();
	private String text;

	public TrivialWidget(Context context) {
		super(context);
		
		setMinimumWidth(100);
		setMinimumHeight(100);
	}
	
	public TrivialWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public TrivialWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setText(attrs.getAttributeValue(null, "text"));
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // width ��¥ ũ�� ���ϱ�
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        
        Log.d(TAG, "widthMode : " + String.valueOf(widthMode));
        
        int widthSize = 0;
        switch(widthMode) {
        case MeasureSpec.UNSPECIFIED:    // mode �� ���õ��� ���� ũ�Ⱑ �Ѿ�ö�
            widthSize = getSuggestedMinimumWidth();
            break;
        case MeasureSpec.AT_MOST:        // wrap_content (�� ������ ũ�⿡ ���� ũ�Ⱑ �޶���)
//            if (null != text) {
//            	widthSize = (int) paint.measureText(text);
//            } else {
            	widthSize = getSuggestedMinimumWidth();
//            }
            break;
        case MeasureSpec.EXACTLY:        // fill_parent, match_parent (�ܺο��� �̹� ũ�Ⱑ �����Ǿ���)
            widthSize = MeasureSpec.getSize(widthMeasureSpec);
            break;
        default:
        	widthSize = getSuggestedMinimumWidth();
        	break;
        }
        
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = 0;
        switch(heightMode) {
        case MeasureSpec.UNSPECIFIED:    // mode �� ���õ��� ���� ũ�Ⱑ �Ѿ�ö�
            heightSize = getSuggestedMinimumHeight();
            break;
        case MeasureSpec.AT_MOST:        // wrap_content (�� ������ ũ�⿡ ���� ũ�Ⱑ �޶���)
            heightSize = getSuggestedMinimumHeight();
            break;
        case MeasureSpec.EXACTLY:        // fill_parent, match_parent (�ܺο��� �̹� ũ�Ⱑ �����Ǿ���)
            heightSize = MeasureSpec.getSize(heightMeasureSpec);
            break;
        default:        // wrap_content (�� ������ ũ�⿡ ���� ũ�Ⱑ �޶���)
            heightSize = getSuggestedMinimumHeight();
            break;
        }
        
        setMeasuredDimension(widthSize, heightSize);
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		int width = getWidth(), height = getHeight();
		
		paint.reset();
		
		canvas.drawColor(Color.GRAY);
		
		/*
		canvas.drawLine(33, 0, 33, height, paint);
		
		paint.setColor(Color.RED);
		paint.setStrokeWidth(10);
		canvas.drawLine(56, 0, 56, height, paint);
		
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(5f);
		
		for (int y = 30, alpha = 255; alpha > 2; alpha >>= 1, y += 10) {
			paint.setAlpha(alpha);
			canvas.drawLine(0, y, width, y, paint);
		}
		
		if (null != text) {
			paint.setColor(Color.WHITE);
			paint.setTextAlign(Align.CENTER);
			canvas.drawText(text, getWidth() / 2, getHeight() / 2, paint);
		}
		*/
		
		paint.setColor(Color.RED);
		canvas.drawText("Android", 25, 30, paint);
		
		Path path = new Path();
		path.addArc(new RectF(10, 50, 90, 200), 240, 90);
		paint.setColor(Color.CYAN);
		canvas.drawTextOnPath("Android", path, 0, 0, paint);
		
		float[] pos = new float[] {
				20, 80,
				29, 83,
				36, 80,
				46, 83,
				52, 80,
				62, 83,
				68, 80
		};
		paint.setColor(Color.GREEN);
		canvas.drawPosText("Android", pos, paint);
	
		
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}

package kr.yui.android.simplegooglemapviewer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class SimpleMapView extends MapView {

	private GeoPoint geoPoint;
	private boolean clicked = false;
	private Button getPositionBtn;
	
	public SimpleMapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public SimpleMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public SimpleMapView(Context context, String apiKey) {
		super(context, apiKey);
	}


	/**
	 * @return the gp
	 */
	public GeoPoint getGeoPoint() {
		return geoPoint;
	}
	
	/**
	 * @param gp the gp to set
	 */
	public void setGeoPoint(GeoPoint geoPoint) {
		this.geoPoint = geoPoint;
	}
	/**
	 * @param getPositionBtn the getPositionBtn to set
	 */
	public void setGetPositionBtn(Button getPositionBtn) {
		this.getPositionBtn = getPositionBtn;
	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.MapView#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		
		int action = ev.getAction();
		int t;
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			clicked = true;
			break;
			
		case MotionEvent.ACTION_MOVE:
			clicked = false;
			t = ev.getHistorySize();
			geoPoint = getProjection().fromPixels((int)ev.getX(t), (int)ev.getY(t));
			break;
			
		case MotionEvent.ACTION_UP:
			if (clicked) {
				t = ev.getHistorySize();
				geoPoint = getProjection().fromPixels((int)ev.getX(t), (int)ev.getY(t));
				getController().animateTo(geoPoint);
				invalidate();
				// getPositionBtn.setEnabled(true);
			}
			break;
		}
		
		// TODO Auto-generated method stub
		return super.onTouchEvent(ev);
	}
}

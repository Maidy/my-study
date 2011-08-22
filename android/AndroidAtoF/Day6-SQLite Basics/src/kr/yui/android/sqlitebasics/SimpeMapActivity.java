package kr.yui.android.sqlitebasics;

import android.os.Bundle;

import com.google.android.maps.MapActivity;

public class SimpeMapActivity extends MapActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplemap);
    }
    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}

package yui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.*;

public class LinearLayoutDemo extends Activity implements RadioGroup.OnCheckedChangeListener {
    
	RadioGroup orientation;
	RadioGroup gravity;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linearlayoutdemo);
        
        orientation = (RadioGroup)findViewById(R.id.orientation);
        orientation.setOnCheckedChangeListener(this);
        
        gravity = (RadioGroup)findViewById(R.id.gravity);
        gravity.setOnCheckedChangeListener(this);
    }

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (group == orientation) {
			if (checkedId == R.id.horizontal) {
				group.setOrientation(LinearLayout.HORIZONTAL);
			} else {
				group.setOrientation(LinearLayout.VERTICAL);
			}
		} else if (group == gravity) {
			if (checkedId == R.id.left) {
				group.setGravity(Gravity.LEFT);
			} else if (checkedId == R.id.center) {
				group.setGravity(Gravity.CENTER_HORIZONTAL);
			} else {
				group.setGravity(Gravity.RIGHT);
			}
		}
	}
	
}
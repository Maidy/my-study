package com.commonsware.android.ch06;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public class RadioButtonDemo extends Activity implements RadioGroup.OnCheckedChangeListener {
	
	RadioGroup rg;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiobuttondemo);
        
        rg = (RadioGroup)findViewById(R.id.radiogroup);
        rg.setOnCheckedChangeListener(this);
    }

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		RadioButton rb = (RadioButton)findViewById(checkedId);
		TextView tv = (TextView)findViewById(R.id.selectedText);
		tv.setText(rb.getText());
	}
}

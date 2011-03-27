package com.commonsware.android.ch06;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CheckBox;

public class CheckBoxDemo extends Activity implements CompoundButton.OnCheckedChangeListener {
	
	CheckBox cb;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkboxdemo);
        
        cb = (CheckBox)findViewById(R.id.check);
        cb.setOnCheckedChangeListener(this);
    }

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			cb.setText("체크된 상태");
		} else {
			cb.setText("체크되지 않은 상태");
		}
	}
}

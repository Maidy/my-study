package com.yui.android.ch10;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.TabHost;

public class DynamicTabDemo extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamictabdemo);
		
		final TabHost tabs = (TabHost)findViewById(R.id.tabhost_d);
		tabs.setup();
		
		TabHost.TabSpec spec = tabs.newTabSpec("buttontab");
		spec.setContent(R.id.buttontab);
		spec.setIndicator("¹öÆ°");
		tabs.addTab(spec);
		
		tabs.setCurrentTab(0);
		
		Button btn = (Button)findViewById(R.id.buttontab);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TabHost.TabSpec spec = tabs.newTabSpec("tag1");
				spec.setContent(new TabHost.TabContentFactory() {
					
					@Override
					public View createTabContent(String tag) {
						return (new AnalogClock(DynamicTabDemo.this));
					}
				});
				spec.setIndicator("Clock");
				tabs.addTab(spec);
			}
		});
	}
}

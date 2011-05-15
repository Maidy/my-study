package com.yui.android.ch10;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabDemo extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabdemo);
		
		TabHost tabs = (TabHost)findViewById(R.id.tabhost);
		tabs.setup();
		
		TabHost.TabSpec spec = tabs.newTabSpec("tag1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("시계");
		tabs.addTab(spec);
		
		spec = tabs.newTabSpec("tag2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("버튼");
		tabs.addTab(spec);
		
		tabs.setCurrentTab(0);
	}
}

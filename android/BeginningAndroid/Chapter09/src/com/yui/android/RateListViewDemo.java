package com.yui.android;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

public class RateListViewDemo extends ListActivity {
	TextView selection;
	String[] items = { "lorem", "ipsum", "dolor", "sit", "amet",
			"consectetuer", "adipiscing", "elit", "morbi", "vel",
			"etiam", "vel", "erat", "placerat", "ante",
			"porttitor", "sodales", "pellentesque", "augue", "purus" };
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ratelistviewdemo);
		
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				items));
		// selection = (TextView)findViewById(R.id.selection2);
	}
	
	public void onListItemClick(ListView parent, View v, int position, long id) {
		selection.setText(items[position]);
	}
}

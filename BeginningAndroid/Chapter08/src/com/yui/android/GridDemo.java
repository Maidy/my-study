package com.yui.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.GridView;
import android.widget.ArrayAdapter;

public class GridDemo extends Activity implements AdapterView.OnItemSelectedListener {
	
	TextView selection;
	String[] items = { "lorem", "ipsum", "dolor", "sit", "amet",
			"consectetuer", "adipiscing", "elit", "morbi", "vel",
			"etiam", "vel", "erat", "placerat", "ante",
			"porttitor", "sodales", "pellentesque", "augue", "purus" };
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.griddemo);

		selection = (TextView)findViewById(R.id.selection3);

		GridView grid = (GridView)findViewById(R.id.grid);
		grid.setAdapter(new FunnyLookingAdapter(this, android.R.layout.simple_list_item_1, items));
		grid.setOnItemSelectedListener(this);
		
	}

	public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
		selection.setText(items[position]);
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		selection.setText("");
	}
	
	private class FunnyLookingAdapter extends ArrayAdapter<String> {

		Context context;
		
		public FunnyLookingAdapter(Context context, int textViewResourceId, String[] items) {
			super(context, textViewResourceId, items);
			this.context = context;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			TextView label = (TextView)convertView;
			
			if (convertView == null) {
				convertView = new TextView(context);
				label = (TextView)convertView;
			}
			
			label.setText(items[position]);
			return convertView;
		}
	}

}


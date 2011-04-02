package com.yui.android;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StaticDemo extends ListActivity {
	TextView selection;
	String[] items = { "lorem", "ipsum", "dolor", "sit", "amet",
			"consectetuer", "adipiscing", "elit", "morbi", "vel",
			"etiam", "vel", "erat", "placerat", "ante",
			"porttitor", "sodales", "pellentesque", "augue", "purus" };
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdemo);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.row, R.id.label, items));
        
        selection = (TextView)findViewById(R.id.selection);
    }
    
    public void onListItemClick(ListView parent, View v, int position, long id) {
    	selection.setText(items[position]);
    }
}

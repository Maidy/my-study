package com.yui.android;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;

public class RecyclingDemo extends ListActivity {
	TextView selection;
	String[] items = { "lorem", "ipsum", "dolor", "sit", "amet",
			"consectetuer", "adipiscing", "elit", "morbi", "vel",
			"etiam", "vel", "erat", "placerat", "ante",
			"porttitor", "sodales", "pellentesque", "augue", "purus" };
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdemo);
        setListAdapter(new IconicAdapter(this));
        
        selection = (TextView)findViewById(R.id.selection);
    }
    
    public void onListItemClick(ListView parent, View v, int position, long id) {
    	selection.setText(items[position]);
    }
    
    class IconicAdapter extends ArrayAdapter<String> {
    	
    	Activity context;
    	
    	IconicAdapter(Activity context) {
    		super(context, R.layout.row, items);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
    		
    		View row = convertView;
    		if (row == null) {
    			LayoutInflater inflater = context.getLayoutInflater();
    			row = inflater.inflate(R.layout.row, null);
    		}
    		
    		TextView label = (TextView)row.findViewById(R.id.label);
    		
    		label.setText(items[position]);
    		ImageView icon = (ImageView)row.findViewById(R.id.icon);
    		
    		if (items[position].length() > 4) {
    			icon.setImageResource(R.drawable.delete);
    		} else {
    			icon.setImageResource(R.drawable.ok);
    		}
    		
    		return row;
    	}
    }
}

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

public class ViewWrapperDemo extends ListActivity {
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
    	selection.setText(getModel(position));
    }
    
    private String getModel(int position) {
    	return ((IconicAdapter)this.getListAdapter()).getItem(position);
    }
    
    class IconicAdapter extends ArrayAdapter<String> {
    	
    	Activity context;
    	
    	IconicAdapter(Activity context) {
    		super(context, R.layout.row, items);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
    		
    		View row = convertView;
    		ViewWrapper wrapper = null;
    		
    		if (row == null) {
    			LayoutInflater inflater = context.getLayoutInflater();
    			row = inflater.inflate(R.layout.row, null);
    			wrapper = new ViewWrapper(row);
    			row.setTag(wrapper);
    		} else {
    			wrapper = (ViewWrapper)row.getTag();
    		}
    		
    		TextView label = wrapper.getLabel();
    		ImageView icon = wrapper.getIcon();
    		
    		label.setText(getModel(position));
    		
    		if (items[position].length() > 4) {
    			icon.setImageResource(R.drawable.delete);
    		} else {
    			icon.setImageResource(R.drawable.ok);
    		}
    		
    		return row;
    	}
    }
}

class ViewWrapper {
	TextView label;
	ImageView icon;
	View base;
	
	public ViewWrapper(View context) {
		this.base = context;
	}
	
	public TextView getLabel() {
		if (label == null) {
			label = (TextView)base.findViewById(R.id.label);
		}
		return label;
	}
	
	public ImageView getIcon() {
		if (icon == null) {
			icon = (ImageView)base.findViewById(R.id.icon);
		}
		return icon;
	}
}

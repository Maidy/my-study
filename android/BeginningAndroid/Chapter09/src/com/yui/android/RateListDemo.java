package com.yui.android;

import android.app.Activity;
import android.app.ListActivity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;

public class RateListDemo extends ListActivity {
	TextView selection;
	String[] items = { "lorem", "ipsum", "dolor", "sit", "amet",
			"consectetuer", "adipiscing", "elit", "morbi", "vel",
			"etiam", "vel", "erat", "placerat", "ante",
			"porttitor", "sodales", "pellentesque", "augue", "purus" };
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdemo);
        
        ArrayList<RowModel> list = new ArrayList<RowModel>();
        
        for (String s : items) {
        	list.add(new RowModel(s));
        }
        
        setListAdapter(new CheckAdapter(this, list));
        selection = (TextView)findViewById(R.id.selection);
    }
    
    private RowModel getModel(int position) {
    	return ((CheckAdapter)this.getListAdapter()).getItem(position);
    }
    
    public void onListItemClick(ListView parent, View v, int position, long id) {
    	selection.setText(getModel(position).toString());
    }

    
    class CheckAdapter extends ArrayAdapter<RowModel> {
    	
    	Activity context;
    	
    	public CheckAdapter(Activity context, ArrayList<RowModel> list) {
    		super(context, R.layout.ratingrow, list);
    		
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
    		
    		View row = convertView;
    		ViewWrapper2 wrapper;
    		RatingBar rate;
    		TextView label;
    		
    		if (row == null) {
    			LayoutInflater inflater = context.getLayoutInflater();
    			row = inflater.inflate(R.layout.ratingrow, null);
    			wrapper = new ViewWrapper2(row);
    			row.setTag(wrapper);
    			
    			rate = wrapper.getRatingBar();
    			RatingBar.OnRatingBarChangeListener listener = new RatingBar.OnRatingBarChangeListener() {
					
					@Override
					public void onRatingChanged(RatingBar ratingBar, float rating,
							boolean fromUser) {
						Integer myPosition = (Integer)ratingBar.getTag();
						RowModel model = getModel(myPosition);
						
						model.rating = rating;
						
						LinearLayout parent = (LinearLayout)ratingBar.getParent();
						TextView label = (TextView)parent.findViewById(R.id.ratelabel);
						
						label.setText(model.toString());
					}
				};
				rate.setOnRatingBarChangeListener(listener);
    			
    		} else {
    			wrapper = (ViewWrapper2)row.getTag();
    			rate = wrapper.getRatingBar();
    		}
    		
    		RowModel model = getModel(position);
    		
    		label = wrapper.getLabel();
    		label.setText(model.toString());
    		
    		
    		rate.setTag(new Integer(position));
    		rate.setRating(model.rating);
    		
    		return row;
    	}
    }
    
    class RowModel {
    	String label;
    	float rating = 2.0f;
    	
    	public RowModel(String label) {
    		this.label = label;
    	}
    	
    	public String toString() {
    		if (rating >= 3.0) {
    			return label.toUpperCase();
    		}
    		return label;
    	}
    }
}

class ViewWrapper2 {
	
	View base;
	RatingBar rate = null;
	TextView label = null;
	
	public ViewWrapper2(View base) {
		this.base = base;
	}
	
	public RatingBar getRatingBar() {
		if (rate == null) {
			rate = (RatingBar)base.findViewById(R.id.rate);
		}
		return rate;
	}
	
	public TextView getLabel() {
		if (label == null) {
			label = (TextView)base.findViewById(R.id.ratelabel);
		}
		return label;
	}
}

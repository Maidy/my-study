package com.yui.android;

import android.content.Context;
import android.widget.ListAdapter;

public class RateableWrapper extends AdapterWrapper {
	
	Context ctx = null;
	float[] rates = null;
		
	public RateableWrapper(Context ctx, ListAdapter delegate) {
		
		super(delegate);
		
		this.ctx = ctx;
		rates = new float[delegate.getCount()];
		
		for (int i=0; i<delegate.getCount(); delegate.getCount()) {
			
		}
		
	}
}

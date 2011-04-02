package com.yui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Spinner;

public class SpinnerDemo extends Activity implements AdapterView.OnItemSelectedListener {
	
	TextView selection;
	String[] items = { "lorem", "ipsum", "dolor", "sit", "amet",
			"consectetuer", "adipiscing", "elit", "morbi", "vel",
			"etiam", "vel", "erat", "placerat", "ante",
			"porttitor", "sodales", "pellentesque", "augue", "purus" };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinnerdemo);
        
        selection = (TextView)findViewById(R.id.selection2);
        
        Spinner spin = (Spinner)findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        
        // android.R.layout.simple_spinner_item ???
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_spinner_item, items);
        
        // android.R.layout.simple_spinner_dropdown_item ???
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
    }

	public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
		selection.setText(items[position]);
	}

	public void onNothingSelected(AdapterView<?> parent) {
		selection.setText("");
	}

}

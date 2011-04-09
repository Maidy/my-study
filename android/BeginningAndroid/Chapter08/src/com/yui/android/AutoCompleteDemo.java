package com.yui.android;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

public class AutoCompleteDemo extends Activity implements TextWatcher {

	AutoCompleteTextView edit;
	TextView selection;
	String[] items = { "lorem", "ipsum", "dolor", "sit", "amet",
			"consectetuer", "adipiscing", "elit", "morbi", "vel",
			"ligula", "vitae", "arcu", "aliquet", "mollis",
			"etiam", "vel", "erat", "placerat", "ante",
			"porttitor", "sodales", "pellentesque", "augue", "purus" };
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autocompletedemo);
		
		selection = (TextView)findViewById(R.id.selection4);
		
		edit = (AutoCompleteTextView)findViewById(R.id.edit);
		edit.addTextChangedListener(this);
		edit.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, items));
	}
	
	public void afterTextChanged(Editable s) {

	}

	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	public void onTextChanged(CharSequence s, int start, int count, int after) {
		selection.setText(edit.getText());
	}

}

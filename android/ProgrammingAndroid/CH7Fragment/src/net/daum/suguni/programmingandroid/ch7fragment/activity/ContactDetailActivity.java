package net.daum.suguni.programmingandroid.ch7fragment.activity;

import net.daum.suguni.programmingandroid.ch7fragment.R;
import net.daum.suguni.programmingandroid.ch7fragment.R.layout;
import net.daum.suguni.programmingandroid.ch7fragment.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ContactDetailActivity extends Activity {
	
	public static final String EXTRA_DATA = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_detail);
		
		String value = getIntent().getStringExtra(EXTRA_DATA);
		TextView tv = (TextView) findViewById(R.id.contact_detail_textview);
		tv.setText(value);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_contact_detail, menu);
		return true;
	}

}

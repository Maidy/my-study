package net.daum.suguni.programmingandroid.ch7fragment.activity;

import java.util.Date;

import net.daum.suguni.programmingandroid.ch7fragment.R;
import net.daum.suguni.programmingandroid.ch7fragment.fragment.ContactDetailFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactViewerActivity extends FragmentActivity {

	private static final String TAG_FRAG_DETAIL = "CONTACT_VIEWER_FRAGMENT_DETAIL";

	private ListView contacts;
	
	private String[] mStrings = Cheeses.sCheeseStrings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_viewer);
		
		final boolean useFrag = null != findViewById(R.id.contact_viewer_fragment_contactdetail);
		
		if (useFrag) {
			installFragment();
		}
		
		contacts = (ListView) findViewById(R.id.contact_viewer_contactlist);
		setAdapter(contacts);
		contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (useFrag) {
					stackFragment(mStrings[position]);
				} else {
					stackActivity(mStrings[position]);
				}
				
			}

		});
		
	}

	private void stackActivity(String value) {
		Intent intent = new Intent(this, ContactDetailActivity.class);
		intent.putExtra(ContactDetailActivity.EXTRA_DATA, value);
		startActivity(intent);
	}
	
	protected void stackFragment(String value) {
		Date date = new Date();
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction trans = manager.beginTransaction();
		
		trans.replace(R.id.contact_viewer_fragment_contactdetail,
				ContactDetailFragment.getInstance(value),
				TAG_FRAG_DETAIL);
		
		trans.addToBackStack(null);
		trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		
		trans.commit();
	}

	private void setAdapter(ListView lv) {
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				mStrings); 
		lv.setAdapter(adapter);
		lv.setTextFilterEnabled(true);
	}

	private void installFragment() {
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction trans = manager.beginTransaction();
		
		if (null == manager.findFragmentByTag(TAG_FRAG_DETAIL)) {
			trans.add(R.id.contact_viewer_fragment_contactdetail,
					ContactDetailFragment.getInstance(""),
					TAG_FRAG_DETAIL);
		}
			
		trans.commit();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_contact_viewer, menu);
		return true;
	}

}

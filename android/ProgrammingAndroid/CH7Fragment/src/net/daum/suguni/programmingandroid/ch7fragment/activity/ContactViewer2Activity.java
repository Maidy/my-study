package net.daum.suguni.programmingandroid.ch7fragment.activity;

import net.daum.suguni.programmingandroid.ch7fragment.R;
import net.daum.suguni.programmingandroid.ch7fragment.fragment.ContactDetailFragment;
import net.daum.suguni.programmingandroid.ch7fragment.fragment.ContactListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class ContactViewer2Activity extends FragmentActivity {

	private static final String TAG_FRAG_DETAIL = "FRAG_DETAIL";
	private boolean useFrag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_viewer2);
		
		useFrag = null != findViewById(R.id.contact_viewer2_contactdetailfragment);
		
		if (useFrag) {
			installFragment();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_contact_viewer2, menu);
		return true;
	}
	
	@Override
	public void startActivityFromFragment(Fragment fragment, Intent intent,
			int requestCode) {
		
		if (!useFrag) {
			startActivity(intent);
		} else if (fragment instanceof ContactListFragment) {
			stackFragment(intent);
		}
	}
	
	private void installFragment() {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction trans = manager.beginTransaction();
		
		if (null == manager.findFragmentByTag(TAG_FRAG_DETAIL)) {
			trans.add(R.id.contact_viewer2_contactdetailfragment,
					ContactDetailFragment.getInstance(""),
					TAG_FRAG_DETAIL);
		}
		
		trans.commit();		
	}
	
	private void stackFragment(Intent intent) {
		
		String value = intent.getStringExtra(ContactDetailActivity.EXTRA_DATA);
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction trans = manager.beginTransaction();
		
		trans.replace(R.id.contact_viewer2_contactdetailfragment,
				ContactDetailFragment.getInstance(value),
				TAG_FRAG_DETAIL);
		
		trans.addToBackStack(null);
		trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		
		trans.commit();
	}

}

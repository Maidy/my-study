package net.daum.suguni.programmingandroid.ch7fragment.activity;

import java.util.Date;

import net.daum.suguni.programmingandroid.ch7fragment.R;
import net.daum.suguni.programmingandroid.ch7fragment.fragment.DateTimeFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class FirstFragmentActivity extends FragmentActivity {

	private static final String TAG_FRAG_DATETIME_1 = "FRAG_DATETIME_1";
	private static final String TAG_FRAG_DATETIME_2 = "FRAG_DATETIME_2";
	private static final String TAG = FirstFragmentActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_fragment);
		
		Date date = new Date();
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction trans = manager.beginTransaction();
		
		if (null == manager.findFragmentByTag(TAG_FRAG_DATETIME_1)) {
			
			trans.add(R.id.first_fragment_fragmentlayout_1,
					DateTimeFragment.getInstance(date),
					TAG_FRAG_DATETIME_1);
			
		}
		
		if (null == manager.findFragmentByTag(TAG_FRAG_DATETIME_2)) {
			
			trans.add(R.id.first_fragment_fragmentlayout_2,
					DateTimeFragment.getInstance(date),
					TAG_FRAG_DATETIME_2);
		}
		
		trans.commit();
		
		Button t = (Button) findViewById(R.id.first_fragment_doit);
		t.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				update();
			}

		});
		
//		if (null == manager.findFragmentById(R.id.activity_first_fragment_fragmentlayout)) {
//			Log.d(TAG, "manager found Tag");
//			trans.add(R.id.activity_first_fragment_fragmentlayout, new DateTimeFragment());
//		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_first_fragment, menu);
		return true;
	}
	
	private void update() {
		Date date = new Date();
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction trans = manager.beginTransaction();
		
		trans.replace(R.id.first_fragment_fragmentlayout_1,
				DateTimeFragment.getInstance(date),
				TAG_FRAG_DATETIME_1);
		
		trans.replace(R.id.first_fragment_fragmentlayout_2,
				DateTimeFragment.getInstance(date),
				TAG_FRAG_DATETIME_2);

		trans.addToBackStack(null);
		trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		
		trans.commit();
	}

}

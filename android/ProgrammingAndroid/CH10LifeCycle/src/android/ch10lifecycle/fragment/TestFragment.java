package android.ch10lifecycle.fragment;

import android.app.Activity;
import android.ch10lifecycle.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestFragment extends Fragment {
	
	private final String TAG = this.getClass().getSimpleName();

	public TestFragment() {
	}
	
	@Override
	public void onAttach(Activity activity) {	// 1
		super.onAttach(activity);
		Log.i(TAG, "onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {	// 2
		super.onCreate(savedInstanceState);
		if (null != savedInstanceState) {
		}
		Log.i(TAG, "onCreate");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	// 3
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_testfragment, container, false);
		Log.i(TAG, "onCreateView");
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {	// 4
		super.onActivityCreated(savedInstanceState);
		Log.i(TAG, "onActivityCreated");
	}
	
	@Override
	public void onStart() {	// 5
		super.onStart();
		Log.i(TAG, "onStart");
	}
	
	@Override
	public void onResume() {	// 6
		super.onResume();
		Log.i(TAG, "onResume");
	}
	
	@Override
	public void onPause() {	// 7
		super.onPause();
		Log.i(TAG, "onPause");
	}
	
	@Override
	public void onStop() {	// 8
		super.onStop();
		Log.i(TAG, "onStop");
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {	// 9
		super.onSaveInstanceState(outState);
		Log.i(TAG, "onSaveInstanceState");
	}
}

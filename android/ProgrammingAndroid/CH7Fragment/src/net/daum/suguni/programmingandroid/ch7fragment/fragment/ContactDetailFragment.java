package net.daum.suguni.programmingandroid.ch7fragment.fragment;

import net.daum.suguni.programmingandroid.ch7fragment.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactDetailFragment extends Fragment {
	
	private static final String TAG_DATA = "DETAIL_DATA";
	
	String data;
	
	public static ContactDetailFragment getInstance(String value) {
		Bundle bundle = new Bundle();
		bundle.putString(TAG_DATA, value);
		
		ContactDetailFragment inst = new ContactDetailFragment();
		inst.setArguments(bundle);
		
		return inst;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		data = (null == savedInstanceState)
				? getArguments().getString(TAG_DATA)
				: savedInstanceState.getString(TAG_DATA);

		if (data == null) {
			data = "";
		}
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString(TAG_DATA, data);
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.activity_contact_detail, container, false);
		((TextView) view.findViewById(R.id.contact_detail_textview)).setText(data);
		return view;
	}
}

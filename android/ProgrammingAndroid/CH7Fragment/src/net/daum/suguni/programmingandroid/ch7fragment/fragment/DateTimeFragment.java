package net.daum.suguni.programmingandroid.ch7fragment.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.daum.suguni.programmingandroid.ch7fragment.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DateTimeFragment extends Fragment {
	
	private static final String TAG_DATE_TIME = "DATETIME";
	String date;
	
	public static DateTimeFragment getInstance(Date date) {
		Bundle init = new Bundle();
		init.putString(TAG_DATE_TIME, getDateTimeString(date));
		
		DateTimeFragment frag = new DateTimeFragment();
		frag.setArguments(init);
		
		return frag;
	}
	
	private static String getDateTimeString(Date date) {
		return new SimpleDateFormat("d MMM yyyy HH:mm:ss").format(date);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		date = (null == savedInstanceState)
				? getArguments().getString(TAG_DATE_TIME)
				: savedInstanceState.getString(TAG_DATE_TIME);

		if (date == null) {
			date = new SimpleDateFormat("d MMM yyyy HH:mm:ss").format(new Date());
		}
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_datetime, container, false);
		
		((TextView) view.findViewById(R.id.fragment_datetime_textview)).setText(date);
		
		return view;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString(TAG_DATE_TIME, date);
		super.onSaveInstanceState(outState);
	}
}

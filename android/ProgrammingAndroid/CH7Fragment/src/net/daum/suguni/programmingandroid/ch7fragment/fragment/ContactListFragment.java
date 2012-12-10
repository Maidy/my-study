package net.daum.suguni.programmingandroid.ch7fragment.fragment;

import net.daum.suguni.programmingandroid.ch7fragment.activity.Cheeses;
import net.daum.suguni.programmingandroid.ch7fragment.activity.ContactDetailActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactListFragment extends ListFragment {
	
	private String[] mStrings = Cheeses.sCheeseStrings;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		installListAdapter(getActivity());
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		Intent intent = new Intent(getActivity(), ContactDetailActivity.class);
		intent.putExtra(ContactDetailActivity.EXTRA_DATA, mStrings[position]);
		startActivity(intent);
		
		super.onListItemClick(l, v, position, id);
	}

	private void installListAdapter(FragmentActivity activity) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				mStrings);
		this.setListAdapter(adapter);
	}
}

package android.ch12cp;

import android.os.Bundle;
import android.app.ListActivity;
import android.ch12cp.provider.FinchVideo;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SimpleFinchVideoListActivity extends ListActivity {


	public static final String TAG = SimpleFinchVideoListActivity.class.getSimpleName();

	public static final int TAG_CREATE = 10;
	public static final int TAG_UPDATE = 11;
	
	private SimpleCursorAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_finch_video_list);
		
		Cursor c = getContentResolver().query(
				FinchVideo.SimpleVideos.CONTENT_URI, null, null, null, null);
		
		mAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, c,
				new String[] { FinchVideo.SimpleVideos.TITLE_NAME },
				new int[] { android.R.id.text1 },
				SimpleCursorAdapter.NO_SELECTION);

		// ??? 어디 쓰는겨?
		SimpleCursorAdapter.ViewBinder vb = new SimpleCursorAdapter.ViewBinder() {
			
			@Override
			public boolean setViewValue(View view, Cursor cursor, int column) {
				switch (column) {
				case FinchVideo.TITLE_COLUMN:
					TextView tv = (TextView) view.findViewById(android.R.id.text1);
					tv.setText(cursor.getString(column));
					break;
				}
				return true;
			}
		};
		
		mAdapter.setViewBinder(vb);
		setListAdapter((ListAdapter) mAdapter);
		
		Button b = (Button) findViewById(R.id.simple_finch_video_list_additem);
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SimpleFinchVideoListActivity.this, SimpleFinchVideoActivity.class);
				startActivityForResult(intent, TAG_CREATE);
			}
		});
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Cursor cursor = (Cursor) mAdapter.getItem(position);
		Intent intent = new Intent(SimpleFinchVideoListActivity.this, SimpleFinchVideoActivity.class);
		intent.putExtra("ID", cursor.getLong(FinchVideo.ID_COLUMN));
		intent.putExtra("TITLE", cursor.getString(FinchVideo.TITLE_COLUMN));
		intent.putExtra("DESC", cursor.getString(FinchVideo.DESCRIPTION_COLUMN));
		startActivityForResult(intent, TAG_UPDATE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.activity_simple_finch_video_list, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case TAG_CREATE:
			if (null != data) {
				String title = data.getStringExtra("TITLE");
				String desc = data.getStringExtra("DESC");

				ContentValues values = new ContentValues();
				values.put(FinchVideo.SimpleVideos.TITLE_NAME, title);
				values.put(FinchVideo.SimpleVideos.DESCRIPTION_NAME, desc);
				
				// insert new row
				getContentResolver().insert(FinchVideo.SimpleVideos.CONTENT_URI, values);
//				mAdapter.notifyDataSetChanged();
				
				updateListAdapter();
			}
			break;
		case TAG_UPDATE:
			if (null != data) {
				long id = data.getLongExtra("ID", -1);
				
				// 잘못된 케이스
				if (id == -1) { return; }
				
				String title = data.getStringExtra("TITLE");
				String desc = data.getStringExtra("DESC");

				ContentValues values = new ContentValues();
				values.put(FinchVideo.SimpleVideos.TITLE_NAME, title);
				values.put(FinchVideo.SimpleVideos.DESCRIPTION_NAME, desc);
				
				// update row
				getContentResolver().update(
						ContentUris.withAppendedId(FinchVideo.SimpleVideos.CONTENT_URI, id),
						values, null, null);
				
				// update view
				updateListAdapter();
			}
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void updateListAdapter() {
		Cursor cursor = getContentResolver().query(
				FinchVideo.SimpleVideos.CONTENT_URI, null, null, null, null);
		mAdapter.changeCursor(cursor);
	}

}

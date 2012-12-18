package android.ch12cp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SimpleFinchVideoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_finch_video);
		
		final long id = getIntent().getLongExtra("ID", -1);
		
		// update인 경우 id 존재
		if (id != -1) {
			String title = getIntent().getStringExtra("TITLE");
			title = TextUtils.isEmpty(title) ? "" : title;
			((EditText) findViewById(R.id.simple_finch_video_title)).setText(title);
			
			String desc = getIntent().getStringExtra("DESC");
			desc = TextUtils.isEmpty(desc) ? "" : desc;
			((EditText) findViewById(R.id.simple_finch_video_desc)).setText(desc);
		}
		
		Button b = (Button) findViewById(R.id.simple_finch_video_ok);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = SimpleFinchVideoActivity.this.getIntent();
				EditText text;
				
				if (id != -1) {
					intent.putExtra("ID", id);
				}
				
				text = (EditText) findViewById(R.id.simple_finch_video_title);
				intent.putExtra("TITLE", text.getText().toString().trim());
				
				text = (EditText) findViewById(R.id.simple_finch_video_desc);
				intent.putExtra("DESC", text.getText().toString().trim());
				
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

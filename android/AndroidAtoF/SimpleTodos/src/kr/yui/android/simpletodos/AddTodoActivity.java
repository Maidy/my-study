package kr.yui.android.simpletodos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTodoActivity extends Activity {
	
	public static String ADDTODO_ARG = "INPUTTODO";
	
	private EditText todoEditText;
	private boolean todoChanged = false;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addtodo);
		
		init();
	}
	
	private void init() {
		Button ok = (Button)findViewById(R.id.addtodo_ok_id);
		Button cancel = (Button)findViewById(R.id.addtodo_cancel_id);
		todoEditText = (EditText)findViewById(R.id.addtodo_task_id);
		
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addTask();
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		todoEditText.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				todoChanged = true;
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
			
			public void afterTextChanged(Editable s) { }
		});
		
		
	}
	
	private void addTask() {
		String todo = todoEditText.getText().toString();
		if (todo.equals("") || !todoChanged) {
			finish();
		} else {
			Intent intent = getIntent();
			intent.putExtra(AddTodoActivity.ADDTODO_ARG, todo);
			setResult(RESULT_OK, intent);
			finish();
		}
	}

}

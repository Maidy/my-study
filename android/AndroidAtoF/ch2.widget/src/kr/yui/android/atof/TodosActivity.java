package kr.yui.android.atof;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class TodosActivity extends Activity {
	
	private final int REQUEST_CODE = 1;
	
	Button addTaskButton;
	TextView tasklistTextView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupWidgets();
        
    }
    
    private void setupWidgets() {
    	addTaskButton = (Button)findViewById(R.id.addtask_button);
    	tasklistTextView = (TextView)findViewById(R.id.tasklist_text);
    	
        addTaskButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(TodosActivity.this, AddTodoActivity.class);
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
    }

	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			String t = tasklistTextView.getText().toString();
			tasklistTextView.setText("* " + t + "\n" + data.getStringExtra("todo"));
		}
	}
    
}

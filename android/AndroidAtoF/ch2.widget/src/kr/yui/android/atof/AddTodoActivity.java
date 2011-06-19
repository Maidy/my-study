package kr.yui.android.atof;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class AddTodoActivity extends Activity {

	EditText taskEditText;
	Button okButton;
	Button cancelButton;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        setupWidgets();
    }
    
    private void setupWidgets() {
    	taskEditText = (EditText)findViewById(R.id.task_edit);
    	okButton = (Button)findViewById(R.id.ok_button);
    	cancelButton = (Button)findViewById(R.id.cancel_button);
    	
    	okButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.out.println("ok button clicked");
				addTodo();
			}
		});
    	
    	cancelButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.out.println("cancel button clicked");
				finish();
			}
		});
    }
    
    private void addTodo() {
    	String todo = taskEditText.getText().toString(); 
    	if (!todo.equals("")) {
    		Intent intent = getIntent();
    		intent.putExtra("todo", todo);
    		setResult(RESULT_OK, intent);
    		finish();
    	}
    }
    
}

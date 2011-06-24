package kr.yui.android.simpletodos;

import java.util.ArrayList;

import kr.yui.android.simpletodos.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private final int REQUEST_CODE = 1;
	
	private ListView todoList;
	private ArrayList<String> list;
	// private ArrayAdapter<String> listAdapter;
	private TodoListAdapter listAdapter;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    	list = new ArrayList<String>();
    	// listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, list);
    	listAdapter = new TodoListAdapter(this, R.layout.todolistitem, list);
    	
    	todoList = (ListView)findViewById(R.id.main_todolist_id);
    	todoList.setAdapter(listAdapter);
    	todoList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        
        init();
    }
    
    private void init() {
    	
    	Button addbtn = (Button)findViewById(R.id.main_addbtn_id);
    	Button delbtn = (Button)findViewById(R.id.main_delbtn_id);

    	addbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
    	
    	// TodoListAdapter 사용하면서 삭제가 안된다. 방법 없나???
    	
    	delbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (list.size() > 0) {
					SparseBooleanArray items = todoList.getCheckedItemPositions();
					int count = items.size();
					
					for (int i = 0; i < count; i++) {
						list.remove(items.keyAt(i));
					}
					
					// todoList.clearChoices();
					listAdapter.notifyDataSetChanged();
				}
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
			list.add(data.getStringExtra(AddTodoActivity.ADDTODO_ARG));
			listAdapter.notifyDataSetChanged();
		}
	}
    
}

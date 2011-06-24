package kr.yui.android.simpletodos;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class TodoListAdapter extends BaseAdapter {
	
	Context context;
	LayoutInflater inflater;
	ArrayList<String> todos;
	int layout;
	
	
	public TodoListAdapter(Context context, int layout, ArrayList<String> list) {
		this.context = context;
		this.layout = layout;
		this.todos = list;
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return todos.size();
	}

	public Object getItem(int position) {
		return todos.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		final int pos = position;
		
		if (convertView == null) {
			convertView = inflater.inflate(layout, parent, false);
		}
		
		// ImageView img = (ImageView)convertView.findViewById(R.id.todolistitem_image_id);
		
		TextView text = (TextView)convertView.findViewById(R.id.todolistitem_text_id);
		text.setText(todos.get(pos));
		
		CheckBox chk = (CheckBox)convertView.findViewById(R.id.todolistitem_check_id);
		chk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.out.println("checkbox clicked");
			}
		});
		
		return convertView;
	}
}

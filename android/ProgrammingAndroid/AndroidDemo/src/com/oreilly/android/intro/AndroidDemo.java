package com.oreilly.android.intro;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AndroidDemo extends Activity {

    private LinearLayout root;
    private Random mRand = new Random();

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_demo);
        
        root = (LinearLayout) findViewById(R.id.root);

        /*
        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(
        		ViewGroup.LayoutParams.MATCH_PARENT,
        		ViewGroup.LayoutParams.WRAP_CONTENT,
        		0.0f);
        
        LinearLayout.LayoutParams widgetParams = new LinearLayout.LayoutParams(
        		ViewGroup.LayoutParams.MATCH_PARENT,
        		ViewGroup.LayoutParams.MATCH_PARENT,
        		1.0f);
        
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.LTGRAY);
        root.setLayoutParams(containerParams);
        
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setBackgroundColor(Color.GRAY);
        ll.setLayoutParams(containerParams);
        root.addView(ll);
        
        EditText tb = new EditText(this);
        tb.setText(R.string.defaultLeftText);
        tb.setFocusable(false);
        tb.setLayoutParams(widgetParams);
        ll.addView(tb);
        
        tb = new EditText(this);
        tb.setText(R.string.defaultRightText);
        tb.setFocusable(false);
        tb.setLayoutParams(widgetParams);
        ll.addView(tb);
        
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setBackgroundColor(Color.DKGRAY);
        ll.setLayoutParams(containerParams);
        root.addView(ll);
        
        Button b = new Button(this);
        b.setText(R.string.labelRed);
        b.setTextColor(Color.RED);
        b.setLayoutParams(widgetParams);
        ll.addView(b);
        
        b = new Button(this);
        b.setText(R.string.labelBlue);
        b.setTextColor(Color.BLUE);
        b.setLayoutParams(widgetParams);
        ll.addView(b);
        
        setContentView(root);
        */
        
        final EditText tb1 = (EditText) findViewById(R.id.text1);
        final EditText tb2 = (EditText) findViewById(R.id.text2);
        
        View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tb1.setText(String.valueOf(mRand.nextInt(200)));
				tb2.setText(String.valueOf(mRand.nextInt(200)));
			}
		};
        
        ((Button) findViewById(R.id.button1)).setOnClickListener(listener);
        ((Button) findViewById(R.id.button2)).setOnClickListener(listener);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_android_demo, menu);
        return true;
    }
    
}

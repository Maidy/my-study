package com.yui.android.ch10;

import java.text.DateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Button;

public class Chrono extends Activity {
	
	DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
	TextView dateAndTimeLabel;
	Calendar dateAndTime = Calendar.getInstance();
	
	DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			dateAndTime.set(Calendar.YEAR, year);
			dateAndTime.set(Calendar.MONTH, monthOfYear);
			dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateLabel();
		}
	};
	
	TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
			dateAndTime.set(Calendar.MINUTE, minute);
			updateLabel();
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button btn = (Button)findViewById(R.id.dateBtn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new DatePickerDialog(Chrono.this,
						d,
						dateAndTime.get(Calendar.YEAR),
						dateAndTime.get(Calendar.MONTH),
						dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		
		btn = (Button)findViewById(R.id.timeBtn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new TimePickerDialog(Chrono.this,
						t,
						dateAndTime.get(Calendar.HOUR_OF_DAY),
						dateAndTime.get(Calendar.MINUTE),
						true).show();
			}
		});
		
		dateAndTimeLabel = (TextView)findViewById(R.id.dateAndTime);
		
		updateLabel();
	}
	
	public void updateLabel() {
		dateAndTimeLabel.setText(fmtDateAndTime.format(dateAndTime.getTime()));
	}
}

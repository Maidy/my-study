package kr.yuiworld.temperatureconverter;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditNumber extends EditText {

	private static final String DEFAULT_FORMAT = "%.2f";

	public EditNumber(Context context) {
		super(context);
		init();
	}
	
	public EditNumber(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init() {
		final InputFilter[] filters = new InputFilter[] {
				DigitsKeyListener.getInstance(true, true)
		};
		setFilters(filters);
	}
	
	public EditNumber(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public double getNumber() {
		return Double.valueOf(getText().toString());
	}

	public void setNumber(double f) {
		setText(String.format(DEFAULT_FORMAT, f));
	}

	public void clear() {
		setText("");
	}
}

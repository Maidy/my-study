package kr.yuiworld.temperatureconverter;

import kr.yuiworld.temperatureconverter.TemperatureConverter.OP;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private EditNumber mCelsius;
	private EditNumber mFahrenheit;
	
	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.d(TAG, "onCreate");
		
		mCelsius = (EditNumber) findViewById(R.id.main_celsius);
		mFahrenheit = (EditNumber) findViewById(R.id.main_fahrenheit);
		
		mCelsius.addTextChangedListener(
				new TemperatureChangedWatcher(TemperatureConverter.OP.C2F));
		mFahrenheit.addTextChangedListener(
				new TemperatureChangedWatcher(TemperatureConverter.OP.F2C));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class TemperatureChangedWatcher implements TextWatcher {
		
		private final EditNumber mDest;
		private TemperatureConverter.OP mOp;
		
		public TemperatureChangedWatcher(TemperatureConverter.OP op) {
			mOp = op;
			
			if (op == TemperatureConverter.OP.C2F)
				mDest = mFahrenheit;
			else
				mDest = mCelsius;
		}

		/* (non-Javadoc)
		 * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
		 */
		@Override
		public void afterTextChanged(Editable s) {
			
		}

		/* (non-Javadoc)
		 * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
		 */
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		/* (non-Javadoc)
		 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
		 */
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			
			if (!mDest.hasWindowFocus() || mDest.hasFocus() || s == null) return;
			
			final String str = s.toString();
			
			if (TextUtils.isEmpty(str)) {
				mDest.setText("");
				return;
			}
			
			try {
				final double temp = Double.parseDouble(str);
				final double result = (mOp == OP.C2F) ?
						TemperatureConverter.celsiusToFahrenheit(temp) :
							TemperatureConverter.fahrenheitToCelsius(temp);
				final String resultString = String.format("%.2f", result);
				Log.d(TAG, "onTextChanged, result: " + result + ", resultString: " + resultString);
				mDest.setNumber(result);
				mDest.setSelection(resultString.length());
			} catch (NumberFormatException e) {
				Log.d(TAG, e.getMessage());
			} catch (Exception e) {
				Log.d(TAG, e.getMessage());
			}
		}
	}

}

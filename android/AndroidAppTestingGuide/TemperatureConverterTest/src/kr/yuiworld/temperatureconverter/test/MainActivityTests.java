/**
 * 
 */
package kr.yuiworld.temperatureconverter.test;

import kr.yuiworld.temperatureconverter.EditNumber;
import kr.yuiworld.temperatureconverter.MainActivity;
import kr.yuiworld.temperatureconverter.TemperatureConverter;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author suguni
 *
 */
public class MainActivityTests extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	private static final String TAG = "MainActivityTests";
	
	private MainActivity mActivity;
	private EditNumber mCelsius;
	private EditNumber mFahrenheit;
	private TextView mCelsiusLabel;
	private TextView mFahrenheitLabel;

	public MainActivityTests() {
		this("MainActivityTest");
	}

	/**
	 * @param name
	 */
	public MainActivityTests(String name) {
		super(MainActivity.class);
		setName(name);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = getActivity();
		mCelsius = (EditNumber) mActivity.findViewById(
				kr.yuiworld.temperatureconverter.R.id.main_celsius);
		mFahrenheit = (EditNumber) mActivity.findViewById(
				kr.yuiworld.temperatureconverter.R.id.main_fahrenheit);
		mCelsiusLabel = (TextView) mActivity.findViewById(
				kr.yuiworld.temperatureconverter.R.id.main_celsius_label);
		mFahrenheitLabel = (TextView) mActivity.findViewById(
				kr.yuiworld.temperatureconverter.R.id.main_fahrenheit_label);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public final void testPreconditions() {
		assertNotNull(mActivity);
	}
	
	public final void testHasInputFields() {
		assertNotNull(mCelsius);
		assertNotNull(mFahrenheit);
		assertNotNull(mCelsiusLabel);
		assertNotNull(mFahrenheitLabel);
	}
	
	public final void testFieldsShouldStartEmpty() {
		assertEquals("", mCelsius.getText().toString());
		assertEquals("", mFahrenheit.getText().toString());
	}
	
	public final void testFieldsOnScreen() {
		final Window window = mActivity.getWindow();
		final View origin = window.getDecorView();
		ViewAsserts.assertOnScreen(origin, mCelsius);
		ViewAsserts.assertOnScreen(origin, mFahrenheit);
	}
	
	public final void testAlignment() {
		ViewAsserts.assertLeftAligned(mCelsiusLabel, mCelsius);
		ViewAsserts.assertLeftAligned(mCelsius, mFahrenheitLabel);
		ViewAsserts.assertLeftAligned(mFahrenheitLabel, mFahrenheit);
		ViewAsserts.assertRightAligned(mCelsius, mFahrenheit);
	}
	
	public final void testCelsiusInputFieldConverEntriesScreen() {
		final int expected = ViewGroup.LayoutParams.MATCH_PARENT;
		final ViewGroup.LayoutParams lp = mCelsius.getLayoutParams();
		assertEquals(expected, lp.width);
	}

	public final void testFahrenheitInputFieldConverEntriesScreen() {
		final int expected = LinearLayout.LayoutParams.MATCH_PARENT;
		final LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mFahrenheit.getLayoutParams();
		assertEquals(expected, lp.width);
	}
	
	public final void testFontSizes() {
		final float expected = 24.0f;
		assertEquals(expected, mCelsiusLabel.getTextSize());
		assertEquals(expected, mFahrenheitLabel.getTextSize());
	}
	
	public final void testMargins() {
		LinearLayout.LayoutParams lp;
		final int expected = 6;
		
		lp = (LinearLayout.LayoutParams) mCelsius.getLayoutParams();
		assertEquals(expected, lp.leftMargin);
		assertEquals(expected, lp.rightMargin);
		
		lp = (LinearLayout.LayoutParams) mFahrenheit.getLayoutParams();
		assertEquals(expected, lp.leftMargin);
		assertEquals(expected, lp.rightMargin);
	}
	
	public final void testJustification() {
		final int expected = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
		
		int actual = mCelsius.getGravity();
		assertEquals(expected, actual);
		
		actual = mFahrenheit.getGravity();
		assertEquals(expected, actual);
	}
	
	public final void testVirtualKeyboardSpaceReserved() {
		final int expected = 280;
		final int actual = mFahrenheit.getBottom();
		assertTrue(actual <= expected);
	}
	
	@UiThreadTest
	public final void testFahrenheitToCelciusConversion() {
		mCelsius.clear();
		mFahrenheit.clear();
		
		final double f = 32.5f;
		
		mFahrenheit.requestFocus();
		mFahrenheit.setNumber(f);
		
		mCelsius.requestFocus();
		
		final double expectedC = TemperatureConverter.fahrenheitToCelsius(f);
		
		final double actualC = mCelsius.getNumber();
		final double delta = Math.abs(expectedC - actualC);
		final String msg = "" + f + "F -> " + expectedC + "C but was "
				+ actualC + "C (delta " + delta + ")";
		assertTrue(msg, delta < 0.005);
	}
	
	public void testInputFilter() throws Throwable {
		runTestOnUiThread(new Runnable() {
			@Override
			public void run() {
				mCelsius.requestFocus();
			}
		});
		
		final double n = -1.234d;
		sendKeys("MINUS 1 PERIOD 2 PERIOD 3 PERIOD 4");
		Object nr = null;
		try {
			nr = mCelsius.getNumber();
		} catch (NumberFormatException e) {
			nr = mCelsius.getText();
		}
		
		final String msg = "-1.2.3.4 should be filtered to " + n +
				" but is " + nr;
		assertEquals(msg, n, nr);
	}
}

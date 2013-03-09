package kr.yuiworld.temperatureconverter.test;

import kr.yuiworld.temperatureconverter.EditNumber;
import android.test.AndroidTestCase;

public class EditNumberTest extends AndroidTestCase {
	
	private EditNumber mEditNumber;

	public EditNumberTest() {
		this("EditNumberText Test");
	}

	public EditNumberTest(String name) {
		super();
		setName(name);
	}

	protected void setUp() throws Exception {
		super.setUp();

		mEditNumber = new EditNumber(mContext);
		mEditNumber.setFocusable(true);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetNumber() {
		mEditNumber.setNumber(123.45);
		final double expected = 123.45;
		final double actual = mEditNumber.getNumber();
		final double delta = Math.abs(expected - actual);
		assertTrue(delta < 0.005);
	}

	public void testSetNumber() {
		mEditNumber.setNumber(123.45);
		final String expected = "123.45";
		final String actual = mEditNumber.getText().toString();
		assertEquals(expected, actual);
	}

	public void testClear() {
		final String value = "123.45";
		mEditNumber.setText(value);
		mEditNumber.clear();
		
		String expected = "";
		String actual = mEditNumber.getText().toString();
		assertEquals(expected, actual);
	}

}

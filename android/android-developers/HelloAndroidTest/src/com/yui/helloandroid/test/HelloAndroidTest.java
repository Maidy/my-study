package com.yui.helloandroid.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.yui.helloandroid.HelloAndroid;

public class HelloAndroidTest extends
		ActivityInstrumentationTestCase2<HelloAndroid> {

	private HelloAndroid mActivity;
	private TextView mView;
	private String resourceString;
	
	public HelloAndroidTest() {
		super("com.yui.helloandroid", HelloAndroid.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = this.getActivity();
		mView = (TextView)mActivity.findViewById(com.yui.helloandroid.R.id.textview);
		resourceString = mActivity.getString(com.yui.helloandroid.R.string.hello);
	}
	
	public void testPrecondition() {
		assertNotNull(mView);
	}
	
	public void testText() {
		assertEquals(resourceString, (String)mView.getText());
	}

}

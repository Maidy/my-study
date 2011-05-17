package com.yui.android;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserDemo3 extends Activity {
	
	WebView browser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		browser = (WebView)findViewById(R.id.webkit);
		browser.setWebViewClient(new Callback());
		
		loadTime();
	}

	private void loadTime() {
		String page = "<html><body><a href=\"clock\">"
			+ (new Date()).toString()
			+ "</a></body></html>";
		
		browser.loadDataWithBaseURL("x-data://base", page,
				"text/html", "UTF-8", null);
	}

	private class Callback extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			System.out.println(url);
			loadTime();
			return true;
		}
	}

}


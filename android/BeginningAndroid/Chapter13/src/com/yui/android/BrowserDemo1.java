package com.yui.android;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserDemo1 extends Activity {
	
	WebView wv;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        wv = (WebView)findViewById(R.id.webkit);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("http://m.daum.net/mini");
        
        WebViewClient wvc = new WebViewClient();
        wv.setWebViewClient(wvc);
        // 이렇게 하지 않으면, 다른 브라우저가 떠버린다.
        
        //wvc.
         
        
//        wvc.shouldOverrideUrlLoading(view, url)
        
//        wv.loadData("<html><body>Hello, World!</body></html>",
//        		"text/html", "UTF-8");
    }
}
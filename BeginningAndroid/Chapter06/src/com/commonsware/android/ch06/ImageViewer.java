package com.commonsware.android.ch06;

import android.app.Activity;
import android.os.Bundle;

public class ImageViewer extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageviewer);
    }
}

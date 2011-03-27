package com.commonsware.android.ch06;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class FieldDemo extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fielddemo);
        
        EditText fld = (EditText)findViewById(R.id.field);
        fld.setText("Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain of the License at http://www.apache.org/licenses/LICENSE-2.0");
    }
}

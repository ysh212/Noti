package com.team.seoul.myapplication123;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by 유석환 on 2017-10-29.
 */

public class SubActivity extends Activity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = (TextView) findViewById(R.id.textView);

    }
}

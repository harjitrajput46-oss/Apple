package com.iptv.glass;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MovieActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("Movies: authorized free sources only. Coming soon.");
        tv.setTextColor(0xFFFFFFFF);
        tv.setBackgroundColor(0xFF0B0B12);
        tv.setTextSize(24);
        tv.setPadding(48, 48, 48, 48);
        setContentView(tv);
    }
}

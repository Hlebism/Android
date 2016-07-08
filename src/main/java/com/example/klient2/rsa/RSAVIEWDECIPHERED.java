package com.example.klient2.rsa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Created by Klient 2 on 2016-06-26.
 */
public class RSAVIEWDECIPHERED extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdeciphered);
        Bundle extras = getIntent().getExtras();
        String value;
        if (extras != null) {
            value = extras.getString("result");
            TextView tv = (TextView) findViewById(R.id.view_result);
            tv.setText(value);
        }
    }

    @Override
    public void onBackPressed()
    {
        finish();
        Intent intent = new Intent(this, RSAMAIN.class);
        startActivity(intent);

    }
}

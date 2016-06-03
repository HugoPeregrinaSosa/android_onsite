package com.mvillasenor.twitter;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Shekomaru on 6/3/16.
 */
public class NewTweetActivity extends Activity {
    private static final String TAG = "NewTweetActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_new_tweet);

        super.onCreate(savedInstanceState);
    }
}

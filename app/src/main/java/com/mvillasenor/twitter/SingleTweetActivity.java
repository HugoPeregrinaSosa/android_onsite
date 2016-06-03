package com.mvillasenor.twitter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Shekomaru on 6/3/16.
 */
public class SingleTweetActivity extends Activity {
    private static final String TAG = "SingleTweetActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_singletweet);

        Long tweetId = getIntent().getLongExtra("tweetId", -1);

        if (tweetId != -1) {
            // TODO: 6/3/16 Load tweet data, and fill fields
            ((TextView)findViewById(R.id.tv_singletweet_content)).setText("Load data, please");

        }

        super.onCreate(savedInstanceState);
    }
}

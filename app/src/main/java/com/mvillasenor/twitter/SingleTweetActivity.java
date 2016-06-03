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

        String tweetContent = null;
        if (getIntent().getExtras() != null) {
            tweetContent = getIntent().getStringExtra("tweetId");
        }

        if (tweetContent != null) {
            // TODO: 6/3/16 Load tweet data, and fill fields
            ((TextView) findViewById(R.id.tv_singletweet_content)).setText(tweetContent);

        }

        super.onCreate(savedInstanceState);
    }
}

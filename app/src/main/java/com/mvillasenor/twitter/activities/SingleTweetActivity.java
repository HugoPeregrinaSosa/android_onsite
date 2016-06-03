package com.mvillasenor.twitter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mvillasenor.twitter.R;

/**
 * Created by Shekomaru on 6/3/16.
 */
public class SingleTweetActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SingleTweetActivity";

    private Toolbar mToolbar;
    private String tweetContent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_singletweet);

        if (getIntent().getExtras() != null) {
            tweetContent = getIntent().getStringExtra("tweetId");
        }

        if (tweetContent != null) {
            // TODO: 6/3/16 Load tweet data, and fill fields
            ((TextView) findViewById(R.id.tv_singletweet_content)).setText(tweetContent);

            findViewById(R.id.bt_singletweet_share).setOnClickListener(this);

            doSentimentAnalisys(tweetContent);
        }

        super.onCreate(savedInstanceState);
    }

    private void shareData(String tweetContent) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, tweetContent);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_singletweet_share:
                if (tweetContent != null) {
                    shareData(tweetContent);
                }
                break;
        }

    }

    private void doSentimentAnalisys(String textToAnalyze) {
        
    }
}

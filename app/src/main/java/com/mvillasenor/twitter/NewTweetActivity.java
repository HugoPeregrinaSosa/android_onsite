package com.mvillasenor.twitter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Shekomaru on 6/3/16.
 */
public class NewTweetActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "NewTweetActivity";

    private int MAX_TWEET_LENGHT = 140;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_new_tweet);

        findViewById(R.id.bt_newtweet_dotweet).setOnClickListener(this);

        final TextView charactersLeftTV = (TextView) findViewById(R.id.tv_newtweet_remainingcharacters);

        final EditText tweetContent = (EditText) findViewById(R.id.et_newtweet_tweetcontent);

        tweetContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int charactersLeft = MAX_TWEET_LENGHT - s.length();

                charactersLeftTV.setText(String.valueOf(charactersLeft));

                if (charactersLeft == 0) {
                    charactersLeftTV.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                } else if (charactersLeft <= 15) {
                    charactersLeftTV.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
                } else {
                    charactersLeftTV.setTextColor(getResources().getColor(android.R.color.black));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_newtweet_dotweet:
                doTweet(((TextView) findViewById(R.id.et_newtweet_tweetcontent)).toString());
                break;
        }
    }

    private void doTweet(String tweetContent) {
        //TweetsRepositoryProvider.getInstance().getTweetsRepository(true).pos
    }
}

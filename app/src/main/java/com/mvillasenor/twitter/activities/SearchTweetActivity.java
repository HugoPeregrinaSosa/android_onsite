package com.mvillasenor.twitter.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mvillasenor.twitter.R;
import com.mvillasenor.twitter.view.fragments.TweetsFragment;

/**
 * Created by Shekomaru on 6/3/16.
 */
public class SearchTweetActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SearchTweetActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_searchtweet);

        findViewById(R.id.b_searchtweet_search).setOnClickListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_searchtweet_container, TweetsFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_searchtweet_search:

                EditText textToSearchContainer = (EditText) findViewById(R.id.et_searchtweet_texttosearch);
                if (textToSearchContainer.getText().toString().length() == 0) {
                    textToSearchContainer.setError("Write what you want to search");
                } else {

                }

                break;
        }
    }

    private void searchData(String stringToSearch) {
        Log.d(TAG, "searchData() called with: " + "data = [" + stringToSearch + "]");
        if (getSupportFragmentManager().findFragmentById(R.id.fl_searchtweet_container) != null) {
            ((TweetsFragment) getSupportFragmentManager().findFragmentById(R.id.fl_searchtweet_container)).searchAndReplaceTweets(stringToSearch);
        }
    }
}

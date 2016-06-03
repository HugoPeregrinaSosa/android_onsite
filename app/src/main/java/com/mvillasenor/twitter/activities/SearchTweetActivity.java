package com.mvillasenor.twitter.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mvillasenor.twitter.R;
import com.mvillasenor.twitter.data.TweetsRepositoryProvider;
import com.mvillasenor.twitter.models.tweet.Tweet;
import com.mvillasenor.twitter.view.adapters.TweetAdapter;
import com.mvillasenor.twitter.view.fragments.TweetsFragment;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Shekomaru on 6/3/16.
 */
public class SearchTweetActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SearchTweetActivity";

    private Toolbar mToolbar;
    private RecyclerView mTweetList;
    private TweetAdapter adapter;
    private List<Tweet> tweetList = new ArrayList<>();

    private Subscription subscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_searchtweet);

        findViewById(R.id.b_searchtweet_search).setOnClickListener(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        setTitle(getString(R.string.search));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTweetList = (RecyclerView)findViewById(R.id.tweets_list);

        adapter = new TweetAdapter(getApplicationContext(), tweetList);
        mTweetList.setLayoutManager(new LinearLayoutManager(this));
        mTweetList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_searchtweet_search:

                EditText textToSearchContainer = (EditText) findViewById(R.id.et_searchtweet_texttosearch);
                if (textToSearchContainer.getText().toString().length() == 0) {
                    textToSearchContainer.setError("Write what you want to search");
                } else {
                    loadTweets(textToSearchContainer.getText().toString());
                }

                break;
        }
    }

    public void loadTweets(String query){
        TweetsRepositoryProvider.getInstance()
                .getTweetsRepository(true)
                .search(query)
                .subscribe(new Action1<List<Tweet>>() {
                    @Override
                    public void call(List<Tweet> tweets) {
                        tweetList.clear();
                        tweetList.addAll(tweets);
                        adapter.notifyDataSetChanged();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Snackbar.make(findViewById(R.id.main_view), throwable.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(subscription != null) {
            subscription.unsubscribe();
        }
    }


}

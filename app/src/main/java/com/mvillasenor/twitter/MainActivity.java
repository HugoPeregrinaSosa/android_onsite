package com.mvillasenor.twitter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mvillasenor.twitter.activities.NewTweetActivity;
import com.mvillasenor.twitter.activities.SearchTweetActivity;
import com.mvillasenor.twitter.view.fragments.ProfileFragment;
import com.mvillasenor.twitter.view.fragments.TweetsFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(mToolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, TweetsFragment.newInstance())
                    .replace(R.id.header, ProfileFragment.newInstance())
                    .commit();
        }

        findViewById(R.id.fab_main_addtweet).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_main_addtweet:
                addNewTweet();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Log.d(TAG, "onOptionsItemSelected: search");
                startActivity(new Intent(getApplicationContext(), SearchTweetActivity.class));
                break;
            case R.id.action_settings:
                Log.d(TAG, "onOptionsItemSelected: settings");

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addNewTweet() {
        startActivity(new Intent(getApplicationContext(), NewTweetActivity.class));
    }

    private void goToSearch() {

    }

    private void goToSettings() {

    }
}

package com.mvillasenor.twitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.mvillasenor.twitter.view.BaseFragment;
import com.mvillasenor.twitter.view.fragments.ProfileFragment;
import com.mvillasenor.twitter.view.fragments.TweetsFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, TweetsFragment.newInstance())
                    .replace(R.id.header, ProfileFragment.newInstance())
                    .commit();
        }
    }



}

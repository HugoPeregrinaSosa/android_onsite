package com.mvillasenor.twitter;

import android.app.Application;

import com.mvillasenor.twitter.data.TweetsRepositoryProvider;
import com.mvillasenor.twitter.data.UserRepositoryProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by migue on 31/05/2016.
 */
public class TwitterAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UserRepositoryProvider.init(this);
        TweetsRepositoryProvider.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
    }
}

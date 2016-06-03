package com.mvillasenor.twitter.data.cloud;

import com.mvillasenor.twitter.data.cloud.retrofit.interfaces.TweetsClient;
import com.mvillasenor.twitter.data.interfaces.TweetsRepository;
import com.mvillasenor.twitter.models.tweet.Tweet;

import java.util.List;

import io.realm.Realm;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by migue on 31/05/2016.
 */
public class TweetsRepositoryCloudImpl implements TweetsRepository {

    private TweetsClient tweetsClient;

    public TweetsRepositoryCloudImpl(TweetsClient tweetsClient) {
        this.tweetsClient = tweetsClient;
    }

    @Override
    public Observable<List<Tweet>> getTweets() {
        return tweetsClient.getTweets()
                .doOnNext(new Action1<List<Tweet>>() {
                    @Override
                    public void call(List<Tweet> tweets) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(tweets);
                        realm.commitTransaction();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Tweet> postTweet(String status) {
        return tweetsClient.postTweet(status)
                .doOnNext(new Action1<Tweet>() {
                    @Override
                    public void call(Tweet tweet) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(tweet);
                        realm.commitTransaction();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

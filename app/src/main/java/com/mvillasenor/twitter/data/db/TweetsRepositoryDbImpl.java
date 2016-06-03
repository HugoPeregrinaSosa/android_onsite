package com.mvillasenor.twitter.data.db;

import com.mvillasenor.twitter.data.interfaces.TweetsRepository;
import com.mvillasenor.twitter.data.interfaces.UserRepository;
import com.mvillasenor.twitter.models.sentiment.SentimentResult;
import com.mvillasenor.twitter.models.tweet.Tweet;
import com.mvillasenor.twitter.models.user.User;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by MarthaKarina on 03/06/2016.
 */
public class TweetsRepositoryDbImpl implements TweetsRepository {

    @Override
    public Observable<List<Tweet>> getTweets() {
        final Realm realm = Realm.getDefaultInstance();

        return realm.where(Tweet.class)
                .findAll()
                .sort("id", Sort.DESCENDING)
                .asObservable()
                .map(new Func1<RealmResults<Tweet>, List<Tweet>>() {
                    @Override
                    public List<Tweet> call(RealmResults<Tweet> tweets) {
                        return realm.copyFromRealm(tweets);
                    }
                });
    }

    @Override
    public Observable<Tweet> postTweet(String status) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public Observable<SentimentResult> getSentiment(String text) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public Observable<Tweet> getTweet(String id) {
        final Realm realm = Realm.getDefaultInstance();

        return realm.where(Tweet.class)
                .equalTo("idStr", id)
                .findFirst()
                .<Tweet>asObservable()
                .map(new Func1<Tweet, Tweet>() {
                    @Override
                    public Tweet call(Tweet tweet) {
                        return realm.copyFromRealm(tweet);
                    }
                });
    }
}

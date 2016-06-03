package com.mvillasenor.twitter.data.interfaces;

import com.mvillasenor.twitter.models.tweet.Tweet;
import com.mvillasenor.twitter.models.user.User;

import java.util.List;

import rx.Observable;

/**
 * Created by migue on 31/05/2016.
 */
public interface TweetsRepository {

    Observable<List<Tweet>> getTweets();
    Observable<Tweet> postTweet(String status);

}
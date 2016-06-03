package com.mvillasenor.twitter.data.interfaces;

import com.mvillasenor.twitter.models.sentiment.SentimentResult;
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
    Observable<SentimentResult> getSentiment(String text);
    Observable<Tweet> getTweet(String id);
    Observable<List<Tweet>> search(String search);

}

package com.mvillasenor.twitter.data.cloud.retrofit.interfaces;

import com.mvillasenor.twitter.models.tweet.Tweet;
import com.mvillasenor.twitter.models.user.User;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by migue on 31/05/2016.
 */
public interface TweetsClient {

    @GET("/api/statuses/user_timeline")
    Observable<List<Tweet>> getTweets();

    @POST("/api/statuses/update")
    @FormUrlEncoded
    Observable<Tweet> postTweet(@Field("status") String status);

}

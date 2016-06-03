package com.mvillasenor.twitter.data.cloud.retrofit.interfaces;

import com.mvillasenor.twitter.models.sentiment.SentimentResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MarthaKarina on 03/06/2016.
 */
public interface SentimentClient {

    @POST("/api/sentiment/")
    @FormUrlEncoded
    Observable<SentimentResult> getSentiment(@Field("text") String text);

}

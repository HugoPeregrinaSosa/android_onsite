package com.mvillasenor.twitter.data.cloud.retrofit.interfaces;

import com.mvillasenor.twitter.models.user.User;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by migue on 31/05/2016.
 */
public interface UserClient {

    @GET("/api/user")
    Observable<User> getUser();

}

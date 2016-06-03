package com.mvillasenor.twitter.data.interfaces;

import com.mvillasenor.twitter.models.user.User;

import rx.Observable;

/**
 * Created by migue on 31/05/2016.
 */
public interface UserRepository {

    Observable<User> getUser();

}

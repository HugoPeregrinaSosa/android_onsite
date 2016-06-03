package com.mvillasenor.twitter.data.db;

import com.mvillasenor.twitter.data.interfaces.UserRepository;
import com.mvillasenor.twitter.models.user.User;

import io.realm.Realm;
import rx.Observable;

/**
 * Created by MarthaKarina on 03/06/2016.
 */
public class UserRepositoryDbImpl implements UserRepository {

    @Override
    public Observable<User> getUser() {
        final Realm realm = Realm.getDefaultInstance();

        return realm.where(User.class)
                .findFirst()
                .asObservable();

    }
}

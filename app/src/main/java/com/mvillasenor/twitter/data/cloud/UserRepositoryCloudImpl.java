package com.mvillasenor.twitter.data.cloud;

import com.mvillasenor.twitter.data.CacheContainer;
import com.mvillasenor.twitter.data.cloud.retrofit.interfaces.UserClient;
import com.mvillasenor.twitter.data.interfaces.UserRepository;
import com.mvillasenor.twitter.models.user.User;

import io.realm.Realm;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by migue on 31/05/2016.
 */
public class UserRepositoryCloudImpl implements UserRepository {

    private UserClient userClient;

    public UserRepositoryCloudImpl(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public Observable<User> getUser() {
        return userClient.getUser()
                .doOnNext(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(user);
                        realm.commitTransaction();
                        CacheContainer.getInstance().setProfileCached(true);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

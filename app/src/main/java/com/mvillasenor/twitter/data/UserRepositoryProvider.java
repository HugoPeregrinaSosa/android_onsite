package com.mvillasenor.twitter.data;

import android.content.Context;
import android.util.Log;

import com.mvillasenor.twitter.data.cloud.CloudUtils;
import com.mvillasenor.twitter.data.cloud.UserRepositoryCloudImpl;
import com.mvillasenor.twitter.data.cloud.retrofit.RetrofitClient;
import com.mvillasenor.twitter.data.cloud.retrofit.interfaces.UserClient;
import com.mvillasenor.twitter.data.db.UserRepositoryDbImpl;
import com.mvillasenor.twitter.data.interfaces.UserRepository;
import com.mvillasenor.twitter.models.user.User;

import io.realm.Realm;

/**
 * Created by migue on 31/05/2016.
 */
public class UserRepositoryProvider {

    private static final String TAG = UserRepository.class.getSimpleName();

    private static UserRepositoryProvider instance;

    private RetrofitClient retrofitClient;
    private Context context;

    private UserRepositoryProvider(Context context) {
        this.context = context;
        this.retrofitClient = RetrofitClient.getInstance(context);
    }

    public static void init(Context context) {
        if (instance != null) {
            Log.e(TAG, "UserRepositoryProvider has already been initialized");
        } else {
            instance = new UserRepositoryProvider(context);
        }
    }

    public static UserRepositoryProvider getInstance() {
        if (instance == null) {
            throw new RuntimeException("UserRepositoryProvider has not been initialized");
        }
        return instance;
    }

    public UserRepository getUserRepository() {
        UserClient client = retrofitClient.getUserClient();

        if(shoudGetDb()){
            return new UserRepositoryDbImpl();
        }else{
            return new UserRepositoryCloudImpl(client);
        }
    }

    public boolean shoudGetDb(){
        Realm realm = Realm.getDefaultInstance();
        long users = realm.where(User.class).count();
        boolean isCached = CacheContainer.getInstance().isProfileCached();
        boolean isConnected = CloudUtils.isConnected(context);

        return users > 0 && (isCached || !isConnected);
    }
}

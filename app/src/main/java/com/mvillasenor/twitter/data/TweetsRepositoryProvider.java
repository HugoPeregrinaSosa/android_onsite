package com.mvillasenor.twitter.data;

import android.content.Context;
import android.util.Log;

import com.mvillasenor.twitter.data.cloud.CloudUtils;
import com.mvillasenor.twitter.data.cloud.TweetsRepositoryCloudImpl;
import com.mvillasenor.twitter.data.cloud.retrofit.RetrofitClient;
import com.mvillasenor.twitter.data.cloud.retrofit.interfaces.TweetsClient;
import com.mvillasenor.twitter.data.db.TweetsRepositoryDbImpl;
import com.mvillasenor.twitter.data.interfaces.TweetsRepository;
import com.mvillasenor.twitter.models.tweet.Tweet;
import com.mvillasenor.twitter.models.user.User;

import io.realm.Realm;

/**
 * Created by migue on 31/05/2016.
 */
public class TweetsRepositoryProvider {

    private static final String TAG = TweetsRepositoryProvider.class.getSimpleName();

    private static TweetsRepositoryProvider instance;

    private RetrofitClient retrofitClient;
    private Context context;

    private TweetsRepositoryProvider(Context context) {
        this.context = context;
        this.retrofitClient = RetrofitClient.getInstance(context);
    }

    public static void init(Context context) {
        if (instance != null) {
            Log.e(TAG, "TweetsRepositoryProvider has already been initialized");
        } else {
            instance = new TweetsRepositoryProvider(context);
        }
    }

    public static TweetsRepositoryProvider getInstance() {
        if (instance == null) {
            throw new RuntimeException("TweetsRepositoryProvider has not been initialized");
        }
        return instance;
    }

    public TweetsRepository getTweetsRepository(boolean forceCloud) {
        if (shoudGetDb() && !forceCloud) {
            return new TweetsRepositoryDbImpl();
        } else {
            TweetsClient client = retrofitClient.getTweetsClient();
            return new TweetsRepositoryCloudImpl(client);
        }
    }

    public boolean shoudGetDb(){
        Realm realm = Realm.getDefaultInstance();
        long tweetsNumber = realm.where(Tweet.class).count();
        boolean isCached = CacheContainer.getInstance().isTweetsCached();
        boolean isConnected = CloudUtils.isConnected(context);

        return tweetsNumber > 0 && (isCached || !isConnected);
    }
}

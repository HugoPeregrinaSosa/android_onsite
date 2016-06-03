package com.mvillasenor.twitter.data.cloud.retrofit;

import android.content.Context;

import com.mvillasenor.twitter.R;
import com.mvillasenor.twitter.data.cloud.retrofit.interfaces.TweetsClient;
import com.mvillasenor.twitter.data.cloud.retrofit.interfaces.UserClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by migue on 31/05/2016.
 */
public class RetrofitClient {

    private static RetrofitClient instance;

    private Retrofit retrofit;
    private Context context;

    private RetrofitClient(Context context) {
        this.context = context;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static RetrofitClient getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitClient(context);
        }
        return instance;
    }

    public UserClient getUserClient() {
        return retrofit.create(UserClient.class);
    }

    public TweetsClient getTweetsClient() {
        return retrofit.create(TweetsClient.class);
    }

}

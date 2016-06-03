package com.mvillasenor.twitter.data;

/**
 * Created by MarthaKarina on 03/06/2016.
 */
public class CacheContainer {

    private static CacheContainer instance;

    private boolean profileCached;
    private boolean tweetsCached;

    protected CacheContainer() {
    }

    public static CacheContainer getInstance(){
        if(instance == null){
            instance = new CacheContainer();
        }
        return instance;
    }

    public boolean isProfileCached() {
        return profileCached;
    }

    public void setProfileCached(boolean profileCached) {
        this.profileCached = profileCached;
    }


    public boolean isTweetsCached() {
        return tweetsCached;
    }

    public void setTweetsCached(boolean tweetsCached) {
        this.tweetsCached = tweetsCached;
    }
}

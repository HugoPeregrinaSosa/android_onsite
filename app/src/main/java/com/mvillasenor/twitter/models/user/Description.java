
package com.mvillasenor.twitter.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;

public class Description {

    @SerializedName("urls")
    @Expose
    private List<String> urls = new ArrayList<>();

    /**
     * 
     * @return
     *     The urls
     */
    public List<String> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

}

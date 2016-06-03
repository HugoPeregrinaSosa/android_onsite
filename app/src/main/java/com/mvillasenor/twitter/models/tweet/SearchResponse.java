package com.mvillasenor.twitter.models.tweet;

import java.util.List;

/**
 * Created by MarthaKarina on 03/06/2016.
 */
public class SearchResponse {

    private List<Tweet> statuses;

    public List<Tweet> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Tweet> statuses) {
        this.statuses = statuses;
    }
}

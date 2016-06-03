package com.mvillasenor.twitter.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvillasenor.twitter.R;
import com.mvillasenor.twitter.data.TweetsRepositoryProvider;
import com.mvillasenor.twitter.models.tweet.Tweet;
import com.mvillasenor.twitter.view.BaseFragment;
import com.mvillasenor.twitter.view.adapters.TweetAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by migue on 31/05/2016.
 */
public class TweetsFragment extends BaseFragment {

    private RecyclerView tweetsRecycler;
    private TweetAdapter adapter;
    private List<Tweet> tweetList = new ArrayList<>();;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Subscription tweetsSubscription;

    public static TweetsFragment newInstance() {

        Bundle args = new Bundle();

        TweetsFragment fragment = new TweetsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tweets_fragment, container, false);

        tweetsRecycler = (RecyclerView) view.findViewById(R.id.tweets_list);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);

        initializeListView();
        initializeSwipeRefresh();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (tweetList.size() <= 0) {
            requestTweets(false);
        }
    }

    private void initializeListView() {
        tweetsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TweetAdapter(getActivity(), tweetList);
        tweetsRecycler.setAdapter(adapter);
    }

    private void initializeSwipeRefresh(){
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestTweets(true);
            }
        });
    }

    private void requestTweets(boolean forceUpdate) {
        tweetsSubscription = TweetsRepositoryProvider.getInstance()
                .getTweetsRepository(forceUpdate)
                .getTweets()
                .subscribe(
                        new Action1<List<Tweet>>() {
                            @Override
                            public void call(List<Tweet> tweets) {
                                loadTweets(tweets);
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }
                        ,
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                showError(throwable.getMessage());
                                Log.e("TweetsFragment", throwable.getMessage(), throwable);
                            }
                        }

                );
    }

    @Override
    public void onPause() {
        super.onPause();
        tweetsSubscription.unsubscribe();
    }


    public void loadTweets(List<Tweet> tweets) {
        adapter = new TweetAdapter(getActivity(), tweets);
        tweetsRecycler.setAdapter(adapter);
    }

    public void showError(String error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
    }


}
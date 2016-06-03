package com.mvillasenor.twitter.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvillasenor.twitter.R;
import com.mvillasenor.twitter.SingleTweetViewLauncher;
import com.mvillasenor.twitter.models.tweet.Tweet;

import java.util.List;

/**
 * Created by migue on 31/05/2016.
 */
public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    private Context context;
    private List<Tweet> tweets;
    private SingleTweetViewLauncher singleTweetViewLauncher;

    public TweetAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
        this.singleTweetViewLauncher = null;
    }

    public TweetAdapter(Context context, List<Tweet> tweets, SingleTweetViewLauncher singleTweetViewLauncher) {
        this.context = context;
        this.tweets = tweets;
        this.singleTweetViewLauncher = singleTweetViewLauncher;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_tweet, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Tweet tweet = tweets.get(position);

        holder.username.setText(tweet.getUser().getName());
        holder.tweetText.setText(tweet.getText());
        Glide.with(context)
                .load(tweet.getUser().getProfileImageUrl())
                .placeholder(R.drawable.default_picture)
                .error(R.drawable.default_picture)
                .into(holder.profilePicture);

        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView profilePicture;
        public TextView username;
        public TextView tweetText;

        public ViewHolder(View itemView) {
            super(itemView);

            profilePicture = (ImageView) itemView.findViewById(R.id.profile_picture);
            username = (TextView) itemView.findViewById(R.id.user_name);
            tweetText = (TextView) itemView.findViewById(R.id.tweet_content);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (singleTweetViewLauncher != null) {
                singleTweetViewLauncher.showTweetInfo(tweets.get((int) v.getTag()).getText());
            }
        }
    }


}

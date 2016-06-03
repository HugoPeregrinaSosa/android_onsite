package com.mvillasenor.twitter.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvillasenor.twitter.R;
import com.mvillasenor.twitter.data.UserRepositoryProvider;
import com.mvillasenor.twitter.models.user.User;
import com.mvillasenor.twitter.view.BaseFragment;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by migue on 31/05/2016.
 */
public class ProfileFragment extends BaseFragment {

    private ImageView mProfilePicture;
    private ImageView mBackgroudPicture;
    private TextView mUserName;
    private TextView mDescription;
    private Subscription userSubscription;

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        mProfilePicture = (ImageView) view.findViewById(R.id.profile_picture);
        mBackgroudPicture = (ImageView) view.findViewById(R.id.background_image);
        mUserName = (TextView) view.findViewById(R.id.user_name);
        mDescription = (TextView) view.findViewById(R.id.description);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        requestUser();
    }


    private void requestUser() {

        userSubscription = UserRepositoryProvider.getInstance()
                .getUserRepository()
                .getUser()
                .subscribe(new Action1<User>() {
                               @Override
                               public void call(User user) {
                                   loadInfo(user);
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                showError(throwable.getMessage());
                                Log.e("TweetsFragment", throwable.getMessage(), throwable);
                            }
                        });

    }

    @Override
    public void onPause() {
        super.onPause();
        userSubscription.unsubscribe();
    }

    public void loadInfo(User user) {
        mUserName.setText(user.getName());
        if (getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            getActivity().setTitle(user.getName());
        }
        mDescription.setText(user.getDescription());
        loadProfilePicture(user.getProfileImageUrl());
        loadBackgroundPicture(user.getProfileBackgroundImageUrl());
    }

    public void loadProfilePicture(String url) {
        Glide.with(getActivity())
                .load(url)
                .placeholder(R.drawable.default_picture)
                .error(R.drawable.default_picture)
                .into(mProfilePicture);
    }

    public void loadBackgroundPicture(String url) {
        Glide.with(getActivity())
                .load(url)
                .centerCrop()
                .into(mBackgroudPicture);
    }


    public void showError(String error) {
        if (getView() != null) {
            Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
        }
    }


}

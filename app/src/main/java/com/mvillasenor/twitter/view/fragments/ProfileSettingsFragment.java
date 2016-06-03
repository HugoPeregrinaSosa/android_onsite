package com.mvillasenor.twitter.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.mvillasenor.twitter.R;
import com.mvillasenor.twitter.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MarthaKarina on 03/06/2016.
 */
public class ProfileSettingsFragment extends BaseFragment {

    @BindView(R.id.background_image)
    ImageView backgroundImage;
    @BindView(R.id.profile_picture)
    ImageView profilePicture;
    @BindView(R.id.user_name)
    TextInputEditText userName;
    @BindView(R.id.description)
    TextInputEditText description;
    @BindView(R.id.location)
    TextInputEditText location;
    @BindView(R.id.website)
    TextInputEditText website;
    @BindView(R.id.birthday)
    TextInputEditText birthday;
    @BindView(R.id.save)
    Button save;

    public static ProfileSettingsFragment newInstance() {

        Bundle args = new Bundle();

        ProfileSettingsFragment fragment = new ProfileSettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_settings_fragment, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.save)
    public void onClick() {
    }
}

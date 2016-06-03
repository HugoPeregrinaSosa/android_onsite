package com.mvillasenor.twitter.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mvillasenor.twitter.R;
import com.mvillasenor.twitter.data.UserRepositoryProvider;
import com.mvillasenor.twitter.models.user.User;
import com.mvillasenor.twitter.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import io.realm.Realm;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by MarthaKarina on 03/06/2016.
 */
public class ProfileSettingsFragment extends BaseFragment implements DatePickerFragment.OnBirthdaySelected {

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

    Subscription subscription;

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

    @Override
    public void onResume() {
        super.onResume();
        loadUser();
    }

    public void loadUser() {
        subscription = UserRepositoryProvider.getInstance()
                .getUserRepository()
                .getUser()
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        loadData(user);
                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        subscription.unsubscribe();
    }

    public void loadData(User user) {
        userName.setText(user.getName());
        description.setText(user.getDescription());
        location.setText(user.getLocation());
        website.setText(user.getWebsite());
        birthday.setText(user.getBirthday());

        Glide.with(getActivity())
                .load(user.getProfileImageUrl())
                .error(R.drawable.default_picture)
                .placeholder(R.drawable.default_picture)
                .into(profilePicture);

        Glide.with(getActivity())
                .load(user.getProfileBackgroundImageUrl())
                .centerCrop()
                .into(backgroundImage);


    }

    @OnFocusChange(R.id.birthday)
    public void setBirthday(View v) {
        if (v.isFocused()) {
            showDatePickerDialog();
        }
    }

    @OnClick(R.id.save)
    public void onClick() {
        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class)
                .findFirst();

        realm.beginTransaction();
        user.setName(userName.getText().toString());
        user.setDescription(description.getText().toString());
        user.setWebsite(website.getText().toString());
        user.setBirthday(birthday.getText().toString());
        user.setLocation(location.getText().toString());
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
        getActivity().finish();
    }



    public void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setListener(this);
        newFragment.show(getChildFragmentManager(), getString(R.string.birthday));
    }

    @Override
    public void onBirthdaySelected(String birthday) {
        this.birthday.setText(birthday);
    }


}

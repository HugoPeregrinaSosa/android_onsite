package com.mvillasenor.twitter.view;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

/**
 * Created by migue on 31/05/2016.
 */
public class BaseFragment extends Fragment {
    public void showError(String error) {
        if (getView() != null) {
            Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
        }
    }
}

package com.mvillasenor.twitter.view.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by MarthaKarina on 03/06/2016.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private OnBirthdaySelected listener;

    public DatePickerFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        listener.onBirthdaySelected(String.format(Locale.getDefault(), "%2d/%2d/%d", day, month, year));
    }

    public interface OnBirthdaySelected {
        void onBirthdaySelected(String birthday);
    }


    public void setListener(OnBirthdaySelected listener) {
        this.listener = listener;
    }
}
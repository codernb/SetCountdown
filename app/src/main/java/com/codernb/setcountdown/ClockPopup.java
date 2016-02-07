package com.codernb.setcountdown;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by cyril on 07.02.16.
 */
public class ClockPopup extends Popup {

    private EditText countdownTimeView;
    private EditText thresholdTimeView;

    public ClockPopup(Activity context, Countdown countdown, Callback callback) {
        super(context, countdown, callback);
    }

    @Override
    protected View initializeView() {
        View view = getView(R.layout.clock_popup);
        countdownTimeView = (EditText) view.findViewById(R.id.set_countdown_time);
        thresholdTimeView = (EditText) view.findViewById(R.id.set_threshold_time);
        countdownTimeView.setText(String.format("%d", countdown.getCountdownTime()));
        thresholdTimeView.setText(String.format("%d", countdown.getThreshold()));
        return view;
    }

    @Override
    protected void OKClicked() {
        String countdownText = countdownTimeView.getText().toString();
        String thresholdText = thresholdTimeView.getText().toString();
        int countdownTime = Integer.parseInt(countdownText.length() == 0 ? "0" : countdownText);
        int thresholdTime = Integer.parseInt(thresholdText.length() == 0 ? "0" : thresholdText);
        countdown.setCountdownTime(countdownTime);
        countdown.setThreshold(thresholdTime);
        Preferences.save(context, R.string.countdown_time_save_key, countdownTime);
        Preferences.save(context, R.string.threshold_time_save_key, thresholdTime);
        callback.onOK();
    }

}

package com.michaelvescovo.android.utils.network;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

/**
 * @author Michael Vescovo.
 */

public class NetworkReceiver extends BroadcastReceiver {

    private Activity mActivity;
    private String mSnackbarMessage;
    private int mSnackbarBgColour;
    private int mSnackbarTextColour;
    public boolean networkConnected;
    private Snackbar mSnackbar;

    public NetworkReceiver(Activity activity, String snackbarMessage, int snackbarBgColour,
                           int snackbarTextColour) {
        mActivity = activity;
        mSnackbarMessage = snackbarMessage;
        mSnackbarBgColour = snackbarBgColour;
        mSnackbarTextColour = snackbarTextColour;

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        activity.registerReceiver(this, filter);

        checkConnected();
    }

    private void checkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        networkConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        mSnackbar = networkSnackbar(networkConnected, mSnackbar,
                mActivity.findViewById(android.R.id.content));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        checkConnected();
    }

    public void resume() {
        checkConnected();
    }

    public void destroy() {
        mActivity.unregisterReceiver(this);
    }

    private Snackbar networkSnackbar(boolean isConnected, Snackbar snackbar, View view) {
        if (isConnected) {
            if (snackbar != null) {
                snackbar.dismiss();
            }
        } else {
            snackbar = Snackbar.make(view, mSnackbarMessage, Snackbar.LENGTH_INDEFINITE);
            snackbar.getView().setBackgroundColor(mSnackbarBgColour);
            TextView textView = (TextView) snackbar.getView()
                    .findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(mSnackbarTextColour);
            snackbar.show();
        }
        return snackbar;
    }
}

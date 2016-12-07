package com.michaelvescovo.android.utils.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

/**
 * @author Michael Vescovo.
 */

public class NetworkReceiver extends BroadcastReceiver {

    public static final String CONNECTED = "com.michaelvescovo.android.utils.network.CONNECTED";

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            editor.putBoolean(CONNECTED, true);
        } else {
            editor.putBoolean(CONNECTED, false);
        }
        editor.commit();
    }
}

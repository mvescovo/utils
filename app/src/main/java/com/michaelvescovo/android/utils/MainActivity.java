package com.michaelvescovo.android.utils;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.michaelvescovo.android.utils.network.NetworkReceiver;

public class MainActivity extends AppCompatActivity {

    NetworkReceiver mNetworkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNetworkReceiver = new NetworkReceiver(
                this,
                getString(R.string.network_disconnected),
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.white)
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNetworkReceiver.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNetworkReceiver.destroy();
    }
}

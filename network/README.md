# Network Utility Module
Android network utility library for sharing network utility code between projects.

# Features

- Monitors network connection status and shows a snackbar when disconnected.

# Screenshots

Network disconnected:

<!--![network-disconnected](https://cloud.githubusercontent.com/assets/15829736/20997335/9de752ac-bd58-11e6-88ff-e7bda1cf057b.png)-->
<img src="https://cloud.githubusercontent.com/assets/15829736/20997335/9de752ac-bd58-11e6-88ff-e7bda1cf057b.png" height="500" width="281">

# Install
Add the dependency to your app level build.gradle file:
~~~
compile 'com.michaelvescovo.android.utils:network:0.1.1'
~~~

# Usage

**Be sure to call "destroy" to unregister the Receiver.**

~~~
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
~~~

You can also check network status from the Activity as necessary:

~~~
if (mNetworkReceiver.networkConnected) {
    // Do something...
}
~~~

# Licence

[![AUR](https://img.shields.io/aur/license/yaourt.svg)]()

[GNU General Public License v3.0](http://choosealicense.com/licenses/gpl-3.0/)

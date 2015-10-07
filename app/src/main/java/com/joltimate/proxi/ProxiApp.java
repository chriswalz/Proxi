package com.joltimate.proxi;

import android.app.Application;

/**
 * Created by chris on 10/7/15.
 */
public class ProxiApp extends Application {
    public FirebaseWrapper firebaseWrapper;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the singletons so their instances
        // are bound to the application process.
        //initSingletons();
        //firebaseWrapper = new FirebaseWrapper(recyclerView, this);
    }
}

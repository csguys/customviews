package one.session.customviews;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * ********** Pawan ********
 * **********(20/12/17)*******
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}

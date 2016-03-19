package jlapp.de.startmypc;

import wearprefs.WearPrefs;


public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        WearPrefs.init(this, getString(R.string.preferences_shared_with_wear));
    }
}

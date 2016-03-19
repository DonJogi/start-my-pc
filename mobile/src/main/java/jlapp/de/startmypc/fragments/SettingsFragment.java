package jlapp.de.startmypc.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import jlapp.de.startmypc.R;

public class SettingsFragment extends PreferenceFragment {

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}

package jlapp.de.startmypc.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;

import com.example.app.supportv7.app.AppCompatPreferenceActivity;

import jlapp.de.startmypc.fragments.SettingsFragment;

public class SettingsActivity extends AppCompatPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionBar(this);

        SettingsFragment settingsFragment = SettingsFragment.newInstance();

        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, settingsFragment)
                .commit();
    }

    public static void initActionBar(@NonNull AppCompatPreferenceActivity activity) {
        final ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}

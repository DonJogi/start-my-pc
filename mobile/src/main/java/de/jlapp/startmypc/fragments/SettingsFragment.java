package de.jlapp.startmypc.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import de.jlapp.startmypc.R;


public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPreferenceManager().setSharedPreferencesName(getString(R.string.preferences_shared_with_wear));
        addPreferencesFromResource(R.xml.preferences);

        bindSummaryFor(R.string.pref_mac_address_key, R.string.pref_mac_address_summary_default);
//        bindSummaryFor(R.string.pref_broadcast_ip_key, R.string.pref_broadcast_ip_summary_default);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (getString(R.string.pref_mac_address_key).equals(key)) {
            bindSummaryFor(R.string.pref_mac_address_key, R.string.pref_mac_address_summary_default);
        }
//        else if (getString(R.string.pref_broadcast_ip_key).equals(key)) {
//            bindSummaryFor(R.string.pref_broadcast_ip_key, R.string.pref_broadcast_ip_summary_default);
//        }
    }

    private void bindSummaryFor(int id, int defaultSummary) {
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();

        getPreferenceScreen()
                .findPreference(getString(id))
                .setSummary(
                        sharedPreferences.getString(
                                getString(id),
                                getString(defaultSummary)));
    }
}

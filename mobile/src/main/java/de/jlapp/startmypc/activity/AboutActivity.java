package de.jlapp.startmypc.activity;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;

import de.jlapp.startmypc.R;
import de.jlapp.startmypc.loader.LicencesLoader;

public class AboutActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private int LOADER_LICENCES = 1;

    private AppCompatTextView licencesTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        licencesTextView = (AppCompatTextView) findViewById(R.id.licences);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getLoaderManager().initLoader(LOADER_LICENCES, null, this).forceLoad();
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        if (id == LOADER_LICENCES) {
            return new LicencesLoader(this);
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        licencesTextView.setText(data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}

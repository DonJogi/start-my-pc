package jlapp.de.startmypc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import de.jlapp.shared.MagicPacketAsyncTask;
import de.jlapp.shared.MagicPacketTuple;
import jlapp.de.startmypc.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews() {
        View startButton = findViewById(R.id.btn_start_my_pc);
        if (startButton == null) {
            return;
        }

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String macAddress = getSharedPreferences(getString(R.string.preferences_shared_with_wear), MODE_PRIVATE)
                        .getString(
                                getString(R.string.pref_mac_address_key),
                                getString(R.string.pref_mac_address_summary_default));

                Toast.makeText(MainActivity.this, "Sending MagicPacket to " + macAddress, Toast.LENGTH_SHORT).show();

               MagicPacketAsyncTask.executeNewTask(macAddress, "192.168.178.255");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}

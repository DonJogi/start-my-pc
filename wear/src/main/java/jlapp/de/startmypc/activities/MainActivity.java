package jlapp.de.startmypc.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.jlapp.shared.MagicPacketAsyncTask;
import jlapp.de.startmypc.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                initViews();
            }
        });
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
}

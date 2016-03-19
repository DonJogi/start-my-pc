package de.jlapp.startmypc.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.wearable.view.WatchViewStub;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import de.jlapp.shared.Constants;
import de.jlapp.startmypc.R;

public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient apiClient;
    private boolean apiClientConnected = false;

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

        initGoogleApiClient();
    }

    @Override
    protected void onDestroy() {
        apiClient.disconnect();
        super.onDestroy();
    }

    private void initViews() {
        View startButton = findViewById(R.id.btn_start_my_pc);
        if (startButton == null) {
            return;
        }

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!apiClientConnected) {
                    return;
                }

                sendMessage(Constants.START_ACTIVITY, "");
            }
        });
    }

    private void initGoogleApiClient() {
        apiClient = new GoogleApiClient.Builder(this, this, this)
                .addApi(Wearable.API)
                .build();

        apiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        apiClientConnected = true;
    }

    @Override
    public void onConnectionSuspended(int i) {
        apiClientConnected = false;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        apiClientConnected = false;
    }

    private void sendMessage(final String path, final String text) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes(apiClient).await();
                for (Node node : nodes.getNodes()) {
                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                            apiClient, node.getId(), path, text.getBytes()).await();
                }
            }
        }).start();
    }
}

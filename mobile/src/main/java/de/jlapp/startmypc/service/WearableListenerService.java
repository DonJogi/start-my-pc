package de.jlapp.startmypc.service;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;

import de.jlapp.shared.Constants;
import de.jlapp.shared.MagicPacketAsyncTask;
import de.jlapp.startmypc.facade.MagicPacketFacade;

public class WearableListenerService extends com.google.android.gms.wearable.WearableListenerService {

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if (messageEvent.getPath().equalsIgnoreCase(Constants.START_ACTIVITY)) {
            Log.d("WearableListenerService", "Received " + Constants.START_ACTIVITY);

            MagicPacketFacade.sendMagicPacket(this);
        } else {
            super.onMessageReceived(messageEvent);
        }
    }
}

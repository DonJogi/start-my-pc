package de.jlapp.shared;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

public class MagicPacketAsyncTask extends AsyncTask<MagicPacketTuple, Void, Void> {

    @Override
    protected Void doInBackground(MagicPacketTuple... params) {
//        MagicPacketHelper.sendMagicPacketTo(params[0]);

        try {
            MagicPacket.send(params[0].macAddress, params[0].broadcastIp);
        } catch (IOException e) {
            Log.d("MainActivity", e.toString());
        }

        return null;
    }

    public static void executeNewTask(String macAddress, String broadcastIp) {
        MagicPacketTuple magicPacketTuple = new MagicPacketTuple();
        magicPacketTuple.broadcastIp = broadcastIp;
        magicPacketTuple.macAddress = macAddress;
        new MagicPacketAsyncTask().execute(magicPacketTuple);
    }
}

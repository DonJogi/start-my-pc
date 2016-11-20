package de.jlapp.startmypc.facade;

import android.content.Context;

import de.jlapp.shared.MagicPacketAsyncTask;
import de.jlapp.startmypc.R;
import de.jlapp.startmypc.network.NetworkDetector;

public class MagicPacketFacade {

    public static void sendMagicPacket(Context context) {
        if (context == null) {
            return;
        }

        String macAddress = context.getSharedPreferences(context.getString(R.string.preferences_shared_with_wear), Context.MODE_PRIVATE)
                .getString(
                        context.getString(R.string.pref_mac_address_key),
                        context.getString(R.string.pref_mac_address_summary_default));

        // TODO Find the directed broadcast IP via IP and subnet mask
        //String broadcastIp = NetworkDetector.getNetworkLocalBroadcastAddressdAsInetAddress();

        MagicPacketAsyncTask.executeNewTask(macAddress, "255.255.255.255");
    }
}

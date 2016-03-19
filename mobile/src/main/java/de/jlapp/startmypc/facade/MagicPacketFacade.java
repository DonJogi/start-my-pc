package de.jlapp.startmypc.facade;

import android.content.Context;

import de.jlapp.shared.MagicPacketAsyncTask;
import de.jlapp.startmypc.R;

/**
 * Created by jogi on 19.03.2016.
 */
public class MagicPacketFacade {

    public static void sendMagicPacket(Context context) {
        if (context == null) {
            return;
        }

        String macAddress = context.getSharedPreferences(context.getString(R.string.preferences_shared_with_wear), Context.MODE_PRIVATE)
                .getString(
                        context.getString(R.string.pref_mac_address_key),
                        context.getString(R.string.pref_mac_address_summary_default));


        MagicPacketAsyncTask.executeNewTask(macAddress, "192.168.178.255");
    }
}

package de.jlapp.startmypc.service;

import android.app.IntentService;
import android.content.Intent;

import de.jlapp.startmypc.facade.MagicPacketFacade;

public class StartMyPcService extends IntentService {

    public StartMyPcService() {
        super(StartMyPcService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        MagicPacketFacade.sendMagicPacket(this);
    }
}

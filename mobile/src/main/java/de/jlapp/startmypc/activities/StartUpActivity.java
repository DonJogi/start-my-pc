package de.jlapp.startmypc.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.jlapp.startmypc.facade.MagicPacketFacade;

public class StartUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MagicPacketFacade.sendMagicPacket(this);
        finish();
    }
}

package ru.feytox.etherology.util.feyapi;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class EtherologyPreLaunch implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        MixinExtrasBootstrap.init();
    }
}

package ru.feytox.etherology.network.util;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.packet.CustomPayload;

public interface AbstractC2SPacket extends CustomPayload {

    default void sendToServer() {
        ClientPlayNetworking.send(this);
    }
}

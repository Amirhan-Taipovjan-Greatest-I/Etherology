package ru.feytox.etherology.network.util;

public abstract class AbstractS2CPacket implements EtherPacket {
    // TODO: 27/04/2023 добавить какой-нибудь функционал, а то грустно как-то
    @FunctionalInterface
    public interface S2CHandler {
        void receive(S2CPacketInfo packetInfo);
    }
}
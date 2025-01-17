package ru.feytox.etherology.network.animation;

import lombok.val;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;
import ru.feytox.etherology.network.util.AbstractS2CPacket;
import ru.feytox.etherology.util.gecko.EGeo2BlockEntity;
import ru.feytox.etherology.util.misc.EIdentifier;

public record SwitchBlockAnimS2C(BlockPos pos, String stopAnim, String startAnim) implements AbstractS2CPacket {

    public static final Id<SwitchBlockAnimS2C> ID = new Id<>(EIdentifier.of("switch_block_anim"));
    public static final PacketCodec<RegistryByteBuf, SwitchBlockAnimS2C> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, SwitchBlockAnimS2C::pos, PacketCodecs.STRING, SwitchBlockAnimS2C::stopAnim, PacketCodecs.STRING, SwitchBlockAnimS2C::startAnim, SwitchBlockAnimS2C::new);

    public static <T extends BlockEntity & EGeo2BlockEntity> void sendForTracking(T blockEntity, String stopAnim, String startAnim) {
        val packet = new SwitchBlockAnimS2C(blockEntity.getPos(), stopAnim, startAnim);
        packet.sendForTracking(blockEntity);
    }

    public static void receive(SwitchBlockAnimS2C packet, ClientPlayNetworking.Context context) {
        MinecraftClient client = context.client();

        client.execute(() -> {
            if (client.world == null) return;
            if (!(client.world.getBlockEntity(packet.pos) instanceof EGeo2BlockEntity animatable)) return;

            animatable.stopClientAnim(packet.stopAnim);
            animatable.triggerAnimByName(packet.startAnim);
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}

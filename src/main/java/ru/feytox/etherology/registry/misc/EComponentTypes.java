package ru.feytox.etherology.registry.misc;

import com.mojang.serialization.Codec;
import lombok.experimental.UtilityClass;
import net.minecraft.component.DataComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import ru.feytox.etherology.magic.corruption.Corruption;
import ru.feytox.etherology.magic.lens.LensComponent;
import ru.feytox.etherology.magic.staff.StaffComponent;
import ru.feytox.etherology.util.misc.EIdentifier;

import java.util.function.UnaryOperator;

@UtilityClass
public class EComponentTypes {


    public static final DataComponentType<Float> STORED_ETHER = register("stored_ether", builder -> builder.codec(Codec.FLOAT).packetCodec(PacketCodecs.FLOAT));
    public static final DataComponentType<LensComponent> LENS = register("lens", builder -> builder.codec(LensComponent.CODEC));
    public static final DataComponentType<ItemStack> STAFF_LENS = register("staff_lens", builder -> builder.codec(ItemStack.CODEC).packetCodec(ItemStack.PACKET_CODEC));
    public static final DataComponentType<StaffComponent> STAFF = register("staff", builder -> builder.codec(StaffComponent.CODEC));
    public static final DataComponentType<Corruption> CORRUPTION = register("corruption", builder -> builder.codec(Corruption.CODEC).packetCodec(Corruption.PACKET_CODEC));

    public static void registerAll() {

    }

    private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, EIdentifier.of(id), builderOperator.apply(DataComponentType.builder()).build());
    }
}

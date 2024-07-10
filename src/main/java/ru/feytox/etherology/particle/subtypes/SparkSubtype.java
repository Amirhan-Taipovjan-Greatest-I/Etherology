package ru.feytox.etherology.particle.subtypes;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.StringIdentifiable;
import org.apache.commons.lang3.EnumUtils;
import org.jetbrains.annotations.Nullable;
import ru.feytox.etherology.magic.zones.EssenceZoneType;
import ru.feytox.etherology.particle.SparkParticle;
import ru.feytox.etherology.particle.effects.SparkParticleEffect;
import ru.feytox.etherology.particle.info.SparkJewelryInfo;
import ru.feytox.etherology.particle.info.SparkRisingInfo;
import ru.feytox.etherology.particle.info.SparkSedimentaryInfo;
import ru.feytox.etherology.particle.utility.ParticleInfo;
import ru.feytox.etherology.particle.utility.ParticleInfoProvider;
import ru.feytox.etherology.util.misc.CodecUtil;

@Getter
@RequiredArgsConstructor
public enum SparkSubtype implements ParticleInfoProvider<SparkParticle, SparkParticleEffect>, StringIdentifiable {
    SIMPLE(null),
    KETA(SparkSedimentaryInfo.of(EssenceZoneType.KETA)),
    RELLA(SparkSedimentaryInfo.of(EssenceZoneType.RELLA)),
    VIA(SparkSedimentaryInfo.of(EssenceZoneType.VIA)),
    CLOS(SparkSedimentaryInfo.of(EssenceZoneType.CLOS)),
    RISING(SparkRisingInfo::new),
    JEWELRY(SparkJewelryInfo::new);

    public static final Codec<SparkSubtype> CODEC = StringIdentifiable.createBasicCodec(SparkSubtype::values);
    public static final PacketCodec<ByteBuf, SparkSubtype> PACKET_CODEC = CodecUtil.ofEnum(values());

    private final ParticleInfo.Factory<SparkParticle, SparkParticleEffect> factory;

    @Nullable
    public static SparkSubtype of(EssenceZoneType zoneType) {
        return EnumUtils.getEnum(SparkSubtype.class, zoneType.name(), null);
    }

    @Override
    public String asString() {
        return name();
    }
}

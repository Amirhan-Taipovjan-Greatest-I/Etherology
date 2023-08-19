package ru.feytox.etherology.particle.subtypes;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.feytox.etherology.particle.LightParticle;
import ru.feytox.etherology.particle.effects.LightParticleEffect;
import ru.feytox.etherology.particle.info.*;
import ru.feytox.etherology.particle.utility.ParticleInfo;
import ru.feytox.etherology.particle.utility.ParticleInfoProvider;

@RequiredArgsConstructor
public enum LightSubtype implements ParticleInfoProvider<LightParticle, LightParticleEffect> {
    SIMPLE(SimpleLightInfo::new),
    SPARK(SparkLightInfo::new),
    VITAL(VitalLightInfo::new),
    PUSHING(PushingLightInfo::new),
    ATTRACT(AttractLightInfo::new),
    BREWING(BrewingLightInfo::new);

    @Nullable
    private final ParticleInfo.Factory<LightParticle, LightParticleEffect> infoFactory;

    @Override
    @Nullable
    public ParticleInfo.Factory<LightParticle, LightParticleEffect> getFactory() {
        return infoFactory;
    }
}
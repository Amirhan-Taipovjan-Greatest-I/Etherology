package ru.feytox.etherology.particle;

import lombok.NonNull;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.Vec3d;
import ru.feytox.etherology.Etherology;
import ru.feytox.etherology.particle.utility.VerticalParticle;

public class PealWaveParticle extends VerticalParticle {
    private final SpriteProvider spriteProvider;

    protected PealWaveParticle(ClientWorld clientWorld, @NonNull Vec3d startCenter, @NonNull Vec3d endCenter, SpriteProvider spriteProvider) {
        super(clientWorld, startCenter, endCenter, 0.5);
        maxAge = 10;

        this.spriteProvider = spriteProvider;
        setSpriteForAge(spriteProvider);
    }

    @Override
    public void tick() {
        if (this.age++ >= this.maxAge) {
            this.markDead();
        }
        setSpriteForAge(spriteProvider);
    }

    @Override
    protected int getBrightness(float tint) {
        return 255;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    // TODO: 07.06.2023 replace ClientWorld argument to smth else (i use it to make sure that particles spawn on the client)
    public static void spawnWave(ClientWorld world, @NonNull Entity from, @NonNull Entity to) {
        Vec3d start = from.getBoundingBox().getCenter();
        Vec3d end = to.getBoundingBox().getCenter();

        ParticleManager particleManager = MinecraftClient.getInstance().particleManager;
        particleManager.addParticle(Etherology.PEAL_WAVE, start.x, start.y, start.z, end.x, end.y, end.z);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new PealWaveParticle(world, new Vec3d(x, y, z), new Vec3d(velocityX, velocityY, velocityZ), spriteProvider);
        }
    }
}
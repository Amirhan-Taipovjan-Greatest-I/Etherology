package ru.feytox.etherology.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import ru.feytox.etherology.enums.LightParticleType;
import ru.feytox.etherology.util.feyapi.FeyColor;
import ru.feytox.etherology.util.feyapi.RGBColor;

public class OldLightParticle extends OldMovingParticle {
    private final int startRed;
    private final int startGreen;
    private final int startBlue;
    private final LightParticleType lightType;
    private final SpriteProvider spriteProvider;

    public OldLightParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, LightParticleType lightType, SpriteProvider spriteProvider) {
        super(clientWorld, d, e, f, g, h, i);
        this.lightType = lightType;
        this.spriteProvider = spriteProvider;

        RGBColor color = null;
        switch (lightType) {
            case SIMPLE -> {
                color = new RGBColor(244, 194, 133);
                setSprite(spriteProvider);
            }
            case SPARK -> {
                this.scale(0.1f);
                setSprite(spriteProvider);
            }
            case ATTRACT -> {
                color = FeyColor.getRandomColor(RGBColor.of(0xCF70FF), RGBColor.of(0xCC3FFF), random);
                setSpriteForAge(spriteProvider);
                maxAge = 320;
            }
            case PUSHING -> {
                color = FeyColor.getRandomColor(RGBColor.of(0xA0FF55), RGBColor.of(0x71ED3D), random);
                setSpriteForAge(spriteProvider);
                maxAge = 320;
            }
        }
        if (color != null) {
            setRGB(color);
            this.scale(0.3f);
        }

        this.startRed = MathHelper.floor(this.red * 255);
        this.startGreen = MathHelper.floor(this.green * 255);
        this.startBlue = MathHelper.floor(this.blue * 255);
    }

    @Override
    protected int getBrightness(float tint) {
        return 255;
    }

    @Override
    public void tick() {
        if (!lightType.equals(LightParticleType.SPARK)) {
            boolean isSimple = lightType.equals(LightParticleType.SIMPLE);
            boolean deadOnEnd = lightType.equals(LightParticleType.SIMPLE) || lightType.equals(LightParticleType.ATTRACT);
            if (!isSimple) setSpriteForAge(spriteProvider);
            acceleratedMovingTick(isSimple ? 0.1f : 0.2f, 0.5f, deadOnEnd, !isSimple);
            return;
        }

        super.tick();
        Vec3d vec = new Vec3d(endX - x, endY - y, endZ - z);
        double vecLength = vec.length();
        double fullPath = new Vec3d(endX - startX, endY - startY, endZ - startZ).length();
        this.setRGB(startRed + (83 - startRed) * ((fullPath - vecLength) / fullPath),
                startGreen + (14 - startGreen) * ((fullPath - vecLength) / fullPath),
                startBlue + (255 - startBlue) * ((fullPath - vecLength) / fullPath));
    }

    @Environment(EnvType.CLIENT)
    public static class SimpleFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public SimpleFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new OldLightParticle(world, x, y, z, velocityX, velocityY, velocityZ, LightParticleType.SIMPLE, this.spriteProvider);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class SparkFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public SparkFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new OldLightParticle(world, x, y, z, velocityX, velocityY, velocityZ, LightParticleType.SPARK, this.spriteProvider);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class PushingFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public PushingFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new OldLightParticle(world, x, y, z, velocityX, velocityY, velocityZ, LightParticleType.PUSHING, this.spriteProvider);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class AttractFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public AttractFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new OldLightParticle(world, x, y, z, velocityX, velocityY, velocityZ, LightParticleType.ATTRACT, this.spriteProvider);
        }
    }
}
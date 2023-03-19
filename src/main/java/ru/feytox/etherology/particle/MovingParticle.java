package ru.feytox.etherology.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import ru.feytox.etherology.util.feyapi.RGBColor;

public class MovingParticle extends SpriteBillboardParticle {
    protected final double startX;
    protected final double startY;
    protected final double startZ;
    protected double endX;
    protected double endY;
    protected double endZ;


    public MovingParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
        // end coords = velocity
        super(clientWorld, d, e, f, g, h, i);
        this.x = d;
        this.y = e;
        this.z = f;
        this.startX = this.x;
        this.startY = this.y;
        this.startZ = this.z;
        this.endX = g;
        this.endY = h;
        this.endZ = i;
        this.maxAge = 80;
    }


    @Override
    public void tick() {
        acceleratedMovingTick(0.4f, 0.5f, true);
    }

    public void acceleratedMovingTick(float speed_k, float start_speed, boolean deadOnEnd) {
        if (this.age++ >= this.maxAge) {
            this.markDead();
            return;
        }

        prevPosX = x;
        prevPosY = y;
        prevPosZ = z;

        Vec3d vec = new Vec3d(endX-x, endY-y, endZ-z);
        double vecLength = vec.length();

        double fullPath = new Vec3d(endX-startX, endY-startY, endZ-startZ).length();
        double f = (fullPath - vecLength) / fullPath;
        double deltaC = speed_k * Math.pow(f+start_speed, 3);

        if (deadOnEnd && vecLength <= 0.5f) {
            this.markDead();
            return;
        }

        Vec3d deltaVec = vec.multiply(Math.min(deltaC / vecLength, 1));
        x += deltaVec.x;
        y += deltaVec.y;
        z += deltaVec.z;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            MovingParticle particle = new MovingParticle(world, x, y, z, velocityX, velocityY, velocityZ);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }

    public static void spawnParticles(ClientWorld world, ParticleEffect parameters, int count, double delta, double x1, double y1, double z1, double x2, double y2, double z2, Random random) {
        for (int i = 0; i < count; i++) {
            double x = x1 + random.nextDouble() * delta * random.nextBetween(-1, 1);
            double y = y1 + random.nextDouble() * delta * random.nextBetween(-1, 1);
            double z = z1 + random.nextDouble() * delta * random.nextBetween(-1, 1);
            world.addParticle(parameters, x, y, z, x2, y2, z2);
        }
    }

    public static void spawnParticles(ClientWorld world, ParticleEffect parameters, int count, double delta, Entity entity, double x2, double y2, double z2, Random random) {
        Box box = entity.getBoundingBox();
        double entityX = (box.minX + box.maxX) / 2;
        double entityY = (box.minY + box.maxY) / 2;
        double entityZ = (box.minZ + box.maxZ) / 2;
        spawnParticles(world, parameters, count, delta, entityX, entityY, entityZ, x2, y2, z2, random);
    }

    public void setRGB(double red, double green, double blue) {
        super.setColor((float) (red / 255d), (float) (green / 255d), (float) (blue / 255d));
    }

    public void setRGB(RGBColor rgbColor) {
        setRGB(rgbColor.r(), rgbColor.g(), rgbColor.b());
    }

    public void setAngle(float degrees) {
        angle = (float) (degrees * Math.PI / 180f);
        if (angle >= 2 * Math.PI) angle = (float) (angle - 2 * Math.PI);
    }

    public void updateAngle(float degrees) {
        prevAngle = angle;
        angle += (float) (degrees * Math.PI / 180f);
        if (angle >= 2 * Math.PI) angle = (float) (angle - 2 * Math.PI);
    }
}
package ru.feytox.etherology.particle.utility;

import lombok.Getter;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import ru.feytox.etherology.particle.types.misc.FeyParticleEffect;
import ru.feytox.etherology.util.feyapi.FeyColor;
import ru.feytox.etherology.util.feyapi.RGBColor;

public abstract class FeyParticle<T extends FeyParticleEffect<T>> extends SpriteBillboardParticle {
    @Getter
    protected final T parameters;
    @Getter
    protected final SpriteProvider spriteProvider;
    @Getter
    protected Vec3d startPos;

    public FeyParticle(ClientWorld clientWorld, double x, double y, double z, T parameters, SpriteProvider spriteProvider) {
        super(clientWorld, x, y, z, 0, 0, 0);
        this.parameters = parameters;
        this.spriteProvider = spriteProvider;
        this.startPos = new Vec3d(x, y, z);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getBrightness(float tint) {
        return 255;
    }

    public double getInverseLen(Vec3d vec) {
        return MathHelper.fastInverseSqrt(vec.lengthSquared());
    }

    public void modifyPos(Vec3d deltaVec) {
        x += deltaVec.x;
        y += deltaVec.y;
        z += deltaVec.z;
    }

    public void updatePos(Vec3d newPos) {
        x = newPos.x;
        y = newPos.y;
        z = newPos.z;
    }

    public boolean inverseCheckDeadPos(boolean deadOnEnd, double inverseLen) {
        if (!deadOnEnd || inverseLen < 2.0d) return false;
        this.markDead();
        return true;
    }

    public boolean checkDeadPos(boolean deadOnEnd, double pathLen) {
        if (!deadOnEnd || pathLen > 0.5d) return false;
        this.markDead();
        return true;
    }

    public boolean tickAge() {
        if (this.age++ < this.maxAge) return false;
        this.markDead();
        return true;
    }

    public void setRGB(double red, double green, double blue) {
        super.setColor((float) (red / 255d), (float) (green / 255d), (float) (blue / 255d));
    }

    public void setRGB(RGBColor rgbColor) {
        setRGB(rgbColor.r(), rgbColor.g(), rgbColor.b());
    }

    public void setRandomColor(RGBColor start, RGBColor end) {
        RGBColor color = FeyColor.getRandomColor(start, end, random);
        setRGB(color);
    }

    public void setAngle(float degrees) {
        angle = (float) (degrees * Math.PI / 180f);
        if (angle >= 2 * Math.PI) angle = (float) (angle - 2 * Math.PI);
    }

    public void modifyAngle(float deltaDegrees) {
        prevAngle = angle;
        angle += (float) (deltaDegrees * Math.PI / 180f);
        if (angle >= 2 * Math.PI) angle = (float) (angle - 2 * Math.PI);
    }

    public Random getRandom() {
        return random;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setAge(int newAge) {
        age = newAge;
    }

    public int getAge() {
        return age;
    }

    public void setSpriteForAge() {
        setSpriteForAge(spriteProvider);
    }

    public void setSpriteForAgeCycle(int cycleAge) {
        if (dead) return;

        int fakeAge = age % (cycleAge + 1);
        setSprite(spriteProvider.getSprite(fakeAge, cycleAge));
    }

    @Nullable
    public static <A extends FeyParticle<B>, B extends FeyParticleEffect<B>> ParticleInfo<A, B> buildFromInfo(ParticleInfoProvider<A, B> infoProvider, A particle, ClientWorld clientWorld, double x, double y, double z, B parameters, SpriteProvider spriteProvider) {
        ParticleInfo.Factory<A, B> infoFactory = infoProvider.getFactory();
        if (infoFactory == null) return null;

        ParticleInfo<A, B> particleInfo = infoFactory.createInfo(clientWorld, x, y, z, parameters, spriteProvider);
        particleInfo.extraInit(particle);
        particle.scale(particleInfo.getScale(particle.random));
        particle.setMaxAge(particleInfo.getMaxAge(particle.random));

        RGBColor color = particleInfo.getStartColor(particle.random);
        if (color != null) particle.setRGB(color);
        return particleInfo;
    }

    public static <A extends FeyParticle<B>, B extends FeyParticleEffect<B>> boolean tickFromInfo(@Nullable ParticleInfo<A, B> particleInfo, A particle) {
        if (particleInfo == null) return false;
        particleInfo.tick(particle);
        return true;
    }
}

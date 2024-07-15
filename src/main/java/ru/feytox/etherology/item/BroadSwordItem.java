package ru.feytox.etherology.item;

import lombok.val;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import ru.feytox.etherology.enums.EArmPose;
import ru.feytox.etherology.particle.effects.ScalableParticleEffect;
import ru.feytox.etherology.registry.misc.EtherSounds;
import ru.feytox.etherology.registry.particle.EtherParticleTypes;
import ru.feytox.etherology.util.misc.DoubleModel;

public class BroadSwordItem extends TwoHandheldSword implements DoubleModel {

    public BroadSwordItem() {
        super(ToolMaterials.IRON, new Settings().maxDamage(476).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON, 5, -3.1f)
                .with(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier("Reach Distance Modifier", 0.33f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.MAINHAND)));
    }

    public static boolean isUsing(PlayerEntity player) {
        return isUsing(player, BroadSwordItem.class);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return EArmPose.TWOHANDHELD_ETHEROLOGY.getUseAction();
    }

    public static float getBroadSwordSweeping(float original, LivingEntity entity) {
        if (!(entity instanceof PlayerEntity player)) return original;
        return BroadSwordItem.isUsing(player) ? original + 0.5f : original;
    }

    public static void replaceSweepParticle(ServerWorld world, double x, double y, double z) {
        val effect = new ScalableParticleEffect(EtherParticleTypes.SCALABLE_SWEEP, 2.0f);
        effect.spawnParticles(world, 1, 0, new Vec3d(x, y, z));
    }

    public static SoundEvent replaceAttackSound(PlayerEntity player, SoundEvent sound) {
        if (!BroadSwordItem.isUsing(player)) return sound;
        return EtherSounds.BROADSWORD;
    }
}

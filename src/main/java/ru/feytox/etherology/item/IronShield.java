package ru.feytox.etherology.item;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;

public class IronShield extends FabricShieldItem {

    public IronShield(Settings settings, int coolDownTicks, int enchantability, Item... repairItems) {
        super(settings, coolDownTicks, enchantability, repairItems);
    }

    public static boolean shieldBlockCheck(Vec3d entityRotation, Vec3d entityPos, Vec3d damagePos) {
        Vec3d damageVec = damagePos.relativize(entityPos);
        damageVec = new Vec3d(damageVec.x, 0, damageVec.z).normalize();
        return damageVec.dotProduct(entityRotation) < 0.135;
    }

    public static Optional<ItemStack> getUsingShield(LivingEntity user) {
        ItemStack activeStack = user.getActiveItem();
        if (!(activeStack.getItem() instanceof IronShield)) return Optional.empty();
        return Optional.of(activeStack);
    }
}

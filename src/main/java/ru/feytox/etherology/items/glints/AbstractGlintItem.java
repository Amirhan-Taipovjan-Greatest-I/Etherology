package ru.feytox.etherology.items.glints;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.BundleTooltipData;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.feytox.etherology.util.feyapi.NoDamageHandler;
import ru.feytox.etherology.util.registry.SimpleItem;

import java.util.List;
import java.util.Optional;

import static ru.feytox.etherology.ItemsRegistry.ETHER_SHARD;

public abstract class AbstractGlintItem extends SimpleItem {
    private final float maxEther;

    public AbstractGlintItem(String itemId, float maxEther) {
        super(itemId, new FabricItemSettings().maxDamage(MathHelper.floor(maxEther)).customDamage(NoDamageHandler::damage));
        this.maxEther = maxEther;
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = super.getDefaultStack();
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putFloat("stored_ether", 0);
        stack.setNbt(nbt);

        return stack;
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null) return Optional.empty();

        float storedEther = nbt.getFloat("stored_ether");
        int etherValue = MathHelper.floor(storedEther);

        DefaultedList<ItemStack> defaultedList = DefaultedList.of();
        for (int i = 0; i < 3 && etherValue > 0; i++) {
            int count = Math.min(64, etherValue);
            etherValue -= count;
            ItemStack etherStack = ETHER_SHARD.getDefaultStack();
            etherStack.setCount(count);
            defaultedList.add(etherStack);
        }

        return Optional.of(new BundleTooltipData(defaultedList, MathHelper.floor(storedEther)));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null) return;

        float storedEther = nbt.getFloat("stored_ether");
        int etherValue = MathHelper.floor(storedEther);

        tooltip.add(Text.translatable("item.etherology.glint.fullness", etherValue, MathHelper.floor(getMaxEther())).formatted(Formatting.GRAY));
    }

    public float getMaxEther() {
        return maxEther;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    /**
     * @return излишек, который не поместился в глинт
     */
    public static float increment(ItemStack stack, float maxEther, float value) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null) return 0;

        float storedEther = nbt.getFloat("stored_ether");
        float newEther = storedEther + value;
        newEther = Math.min(newEther, maxEther);
        nbt.putFloat("stored_ether", newEther);
        // TODO: 16/03/2023 проверить необходимость
        stack.setNbt(nbt);
        stack.setDamage(MathHelper.floor(maxEther - storedEther));

        return value - newEther + storedEther;
    }

    /**
     * @return количество забранного эфира
     */
    public static float decrement(ItemStack stack, float maxEther, float value) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null) return 0;

        float storedEther = nbt.getFloat("stored_ether");
        float newEther = storedEther - value;
        newEther = Math.max(newEther, 0);
        nbt.putFloat("stored_ether", newEther);
        // TODO: 16/03/2023 проверить необходимость
        stack.setNbt(nbt);
        stack.setDamage(MathHelper.floor(maxEther - storedEther));

        return storedEther - newEther;
    }
}
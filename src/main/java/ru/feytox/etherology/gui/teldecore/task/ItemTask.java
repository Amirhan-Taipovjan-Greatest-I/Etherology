package ru.feytox.etherology.gui.teldecore.task;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.RequiredArgsConstructor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import ru.feytox.etherology.gui.teldecore.misc.FeySlot;
import ru.feytox.etherology.gui.teldecore.page.TaskType;

@RequiredArgsConstructor
public class ItemTask extends AbstractTask {

    public static final MapCodec<ItemTask> CODEC;

    private final Item item;
    private final int count;
    private final boolean consume;

    @Override
    public boolean isCompleted(PlayerEntity player) {
        return player.getInventory().count(item) >= count;
    }

    @Override
    public String getType() {
        return "item";
    }

    @Override
    public boolean shouldConsume() {
        return consume;
    }

    @Override
    public boolean consume(PlayerEntity player) {
        return Inventories.remove(player.getInventory(), stack -> stack.isOf(item), count, false) == count;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.ITEM;
    }

    @Override
    public FeySlot toSlot(float x, float y, float width, float height) {
        return new FeySlot.ItemTask(new ItemStack(item, count), isClientCompleted(), x, y, width, height);
    }

    static {
        CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Registries.ITEM.getCodec().fieldOf("item").forGetter(t -> t.item),
                Codec.INT.optionalFieldOf("count", 1).forGetter(t -> t.count),
                Codec.BOOL.optionalFieldOf("consume", true).forGetter(t -> t.consume)
        ).apply(instance, ItemTask::new));
    }
}

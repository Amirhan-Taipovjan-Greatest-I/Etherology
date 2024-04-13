package ru.feytox.etherology.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.StringIdentifiable;
import org.jetbrains.annotations.Nullable;
import ru.feytox.etherology.block.closet.ClosetData;
import ru.feytox.etherology.block.furniture.FurnitureData;
import ru.feytox.etherology.block.shelf.ShelfData;
import ru.feytox.etherology.util.misc.Nbtable;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public enum FurnitureType implements StringIdentifiable, Nbtable {
    EMPTY(null, true),
    FURNITURE(null, false),
    CLOSET(ClosetData::new, false),
    SHELF(ShelfData::new, true);

    private final Factory<? extends FurnitureData> factory;
    @Getter
    private final boolean sidedTransparent;

    @Nullable
    public FurnitureData createDataInstance(boolean isBottom) {
        return factory == null ? null : factory.create(isBottom);
    }

    public boolean isEmpty() {
        return this.equals(EMPTY);
    }

    @Override
    public String asString() {
        return this.name().toLowerCase();
    }

    public static FurnitureType getByIndex(int index) {
        List<FurnitureType> values = Arrays.stream(FurnitureType.values()).toList();
        return index >= values.size() ? EMPTY : values.get(index);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("furniture_type", this.ordinal());
    }

    public static FurnitureType readFromNbt(NbtCompound nbt) {
        return getByIndex(nbt.getInt("furniture_type"));
    }

    @Override
    public Nbtable readNbt(NbtCompound nbt) {
        return readFromNbt(nbt);
    }


    @FunctionalInterface
    public interface Factory<T extends FurnitureData> {
        T create(boolean isBottom);
    }
}

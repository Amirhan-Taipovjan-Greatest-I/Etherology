package name.uwu.feytox.etherology.util;

import net.minecraft.nbt.NbtCompound;

public interface Nbtable {
    void writeNbt(NbtCompound nbt);
    Nbtable readNbt(NbtCompound nbt);
}

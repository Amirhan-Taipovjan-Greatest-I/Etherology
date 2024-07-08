package ru.feytox.etherology.gui.staff;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;
import ru.feytox.etherology.item.LensItem;
import ru.feytox.etherology.magic.lens.LensComponentNew;
import ru.feytox.etherology.magic.lens.LensMode;

@Getter
@RequiredArgsConstructor
public enum LensSelectionType {
    NONE(true, (p, s, l) -> {}),
    UP_ARROW(true, arrowHandler(LensMode.STREAM)),
    DOWN_ARROW(true, arrowHandler(LensMode.CHARGE)),
    ITEM(false, itemHandler());

    private final boolean emptySelectedItem;

    @NonNull
    private final SelectionHandler handler;

    private static SelectionHandler arrowHandler(LensMode lensMode) {
        return (player, staffStack, lensStack) -> {
            val lens = LensItem.getStaffLensWrapper(staffStack);
            if (lens != null) lens.set(lensMode, LensComponentNew::withMode).save();
        };
    }

    private static SelectionHandler itemHandler() {
        return (player, staffStack, lensStack) -> {
            if (lensStack == null || lensStack.isEmpty()) throw new NullPointerException("Null or empty lens stack provided for item selection handler");
            ItemStack prevLens = LensItem.takeLensFromStaff(staffStack);
            if (prevLens != null) player.giveItemStack(prevLens);

            LensItem.placeLensOnStaff(staffStack, lensStack);
        };
    }

    @FunctionalInterface
    public interface SelectionHandler {

        void handle(ServerPlayerEntity player, ItemStack staffStack, @Nullable ItemStack lensStack);
    }
}

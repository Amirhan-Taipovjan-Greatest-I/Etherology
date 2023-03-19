package ru.feytox.etherology.gui.teldecore;

import io.wispforest.owo.ui.component.ItemComponent;
import io.wispforest.owo.ui.core.Sizing;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import ru.feytox.etherology.client.EtherologyClient;
import ru.feytox.etherology.util.feyapi.EIngredient;

public class IngredientComponent extends ItemComponent {
    EIngredient eIngredient;

    public IngredientComponent(EIngredient eIngredient) {
        super(eIngredient.getNextStack());
        this.showOverlay(true);
        this.sizing(Sizing.content());
        this.eIngredient = eIngredient;
        this.ingredient(eIngredient.current());
    }

    public IngredientComponent ingredient(ItemStack itemStack) {
        this.stack(itemStack);
        this.tooltip(itemStack
                .getTooltip(MinecraftClient.getInstance().player, TooltipContext.BASIC));
        return this;
    }

    public IngredientComponent ingredient(EIngredient eIngredient) {
        return this.ingredient(eIngredient.getNextStack());
    }

    public IngredientComponent ingredient() {
        return this.ingredient(this.eIngredient);
    }

    public IngredientComponent blink() {
        EtherologyClient.timer3_supps.add(() -> {
            if (this.hasParent()) {
                this.ingredient();
                return true;
            }
            return false;
        });
        return this;
    }
}
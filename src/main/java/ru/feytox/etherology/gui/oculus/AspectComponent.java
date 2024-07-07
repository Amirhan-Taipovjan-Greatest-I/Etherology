package ru.feytox.etherology.gui.oculus;

import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.component.TextureComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.Positioning;
import io.wispforest.owo.ui.core.Sizing;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ru.feytox.etherology.magic.aspects.Aspect;
import ru.feytox.etherology.magic.aspects.EtherologyAspect;
import ru.feytox.etherology.util.misc.EIdentifier;

public class AspectComponent extends FlowLayout {

    public static final Identifier TEXTURE = EIdentifier.of("textures/gui/aspects.png");

    public AspectComponent(Aspect aspect, int value) {
        super(Sizing.content(), Sizing.content(), Algorithm.VERTICAL);

        TextureComponent aspectTexture = Components.texture(TEXTURE, aspect.getTextureMinX(), aspect.getTextureMinY(), 32, 32, EtherologyAspect.TEXTURE_WIDTH, EtherologyAspect.TEXTURE_HEIGHT);
        LabelComponent valueComponent = Components.label(Text.of(String.valueOf(value))).shadow(true);

        this.child(aspectTexture.blend(true).sizing(Sizing.fixed(28))).child(valueComponent.positioning(Positioning.relative(95, 95)));
    }
}

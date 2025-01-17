package ru.feytox.etherology.registry.misc;

import lombok.experimental.UtilityClass;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import ru.feytox.etherology.block.brewingCauldron.BrewingCauldronRenderer;
import ru.feytox.etherology.block.crate.CrateBlockRenderer;
import ru.feytox.etherology.block.etherealSocket.EtherealSocketRenderer;
import ru.feytox.etherology.block.etherealStorage.EtherealStorageRenderer;
import ru.feytox.etherology.block.furniture.FurnitureBlockEntityRenderer;
import ru.feytox.etherology.block.generators.metronome.MetronomeRenderer;
import ru.feytox.etherology.block.generators.spinner.SpinnerRenderer;
import ru.feytox.etherology.block.jewelryTable.JewelryTableRenderer;
import ru.feytox.etherology.block.matrix.MatrixRenderer;
import ru.feytox.etherology.block.pedestal.PedestalRenderer;

import static ru.feytox.etherology.registry.block.EBlocks.*;

@UtilityClass
public class BlockRenderersRegistry {

    public static void registerAll() {
        register(FURNITURE_BLOCK_ENTITY, FurnitureBlockEntityRenderer::new);
        register(ETHEREAL_STORAGE_BLOCK_ENTITY, EtherealStorageRenderer::new);
        register(ETHEREAL_SOCKET_BLOCK_ENTITY, EtherealSocketRenderer::new);
        register(SPINNER_BLOCK_ENTITY, SpinnerRenderer::new);
        register(METRONOME_BLOCK_ENTITY, MetronomeRenderer::new);
        register(CRATE_BLOCK_ENTITY, CrateBlockRenderer::new);
        register(BREWING_CAULDRON_BLOCK_ENTITY, BrewingCauldronRenderer::new);
        register(PEDESTAL_BLOCK_ENTITY, PedestalRenderer::new);
        register(ARMILLARY_SPHERE_BLOCK_ENTITY, MatrixRenderer::new);
        register(JEWELRY_TABLE_BLOCK_ENTITY, JewelryTableRenderer::new);
    }

    private static <T extends BlockEntity> void register(BlockEntityType<? extends T> blockEntityType, BlockEntityRendererFactory<T> rendererFactory) {
        BlockEntityRendererFactories.register(blockEntityType, rendererFactory);
    }
}

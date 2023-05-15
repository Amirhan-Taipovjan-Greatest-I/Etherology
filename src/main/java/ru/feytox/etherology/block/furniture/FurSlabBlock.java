package ru.feytox.etherology.block.furniture;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import ru.feytox.etherology.enums.FurnitureType;
import ru.feytox.etherology.furniture.AbstractFurSlabBlock;

public class FurSlabBlock extends AbstractFurSlabBlock {
    public FurSlabBlock() {
        super("furniture_slab", FabricBlockSettings.of(Material.WOOD), FurnitureType.FURNITURE);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
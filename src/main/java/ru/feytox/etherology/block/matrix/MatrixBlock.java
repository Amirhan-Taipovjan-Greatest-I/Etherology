package ru.feytox.etherology.block.matrix;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.feytox.etherology.registry.block.EBlocks;
import ru.feytox.etherology.util.misc.RegistrableBlock;

import static ru.feytox.etherology.registry.block.EBlocks.ARMILLARY_SPHERE_BLOCK_ENTITY;

public class MatrixBlock extends Block implements RegistrableBlock, BlockEntityProvider {

    public static final EnumProperty<MatrixState> MATRIX_STATE = EnumProperty.of("matrix_state", MatrixState.class);
    private static final VoxelShape OUTLINE_SHAPE;

    public MatrixBlock() {
        super(Settings.copy(EBlocks.PEDESTAL_BLOCK).nonOpaque());
        setDefaultState(getDefaultState().with(MATRIX_STATE, MatrixState.IDLE));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient || !(world.getBlockEntity(pos) instanceof MatrixBlockEntity matrix)) return ActionResult.CONSUME;
        matrix.onHandUse((ServerWorld) world, state, player, player.getActiveHand());
        matrix.syncData((ServerWorld) world);
        return ActionResult.CONSUME;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock() && !moved) {
            if (world.getBlockEntity(pos) instanceof MatrixBlockEntity matrix) {
                ItemScatterer.spawn(world, pos, matrix);
            }
            super.onStateReplaced(state, world, pos, newState, false);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MATRIX_STATE);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (!type.equals(ARMILLARY_SPHERE_BLOCK_ENTITY)) return null;

        return world.isClient ? MatrixBlockEntity::clientTicker : MatrixBlockEntity::serverTicker;
    }

    @Override
    public String getBlockId() {
        return "armillary_sphere";
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MatrixBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE_SHAPE;
    }

    static {
        OUTLINE_SHAPE = VoxelShapes.union(
                Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
                Block.createCuboidShape(3.0, 5.0, 3.0, 13.0, 10.0, 13.0),
                Block.createCuboidShape(2.0, 10.0, 2.0, 14.0, 14.0, 14.0)
        );
    }
}

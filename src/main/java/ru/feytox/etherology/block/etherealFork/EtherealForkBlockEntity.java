package ru.feytox.etherology.block.etherealFork;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import ru.feytox.etherology.magic.ether.EtherDisplay;
import ru.feytox.etherology.magic.ether.EtherFork;
import ru.feytox.etherology.util.misc.TickableBlockEntity;

import java.util.ArrayList;
import java.util.List;

import static ru.feytox.etherology.block.etherealChannel.EtherealChannel.ACTIVATED;
import static ru.feytox.etherology.registry.block.EBlocks.ETHEREAL_FORK_BLOCK_ENTITY;

public class EtherealForkBlockEntity extends TickableBlockEntity implements EtherFork, EtherDisplay {

    private float storedEther = 0;
    private List<Direction> cachedOutputSides = new ArrayList<>();

    public EtherealForkBlockEntity(BlockPos pos, BlockState state) {
        super(ETHEREAL_FORK_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void serverTick(ServerWorld world, BlockPos blockPos, BlockState state) {
        transferTick(world);
    }

    @Override
    public List<Direction> getCachedOutputSides() {
        return cachedOutputSides;
    }

    @Override
    public void setCachedOutputSides(List<Direction> outputSides) {
        cachedOutputSides = outputSides;
    }

    @Override
    public float getMaxEther() {
        return 8;
    }

    @Override
    public float getStoredEther() {
        return storedEther;
    }

    @Override
    public float getTransferSize() {
        return 1;
    }

    @Override
    public void setStoredEther(float value) {
        storedEther = value;
    }

    @Nullable
    @Override
    public Direction getOutputSide() {
        return null;
    }

    @Override
    public BlockPos getStoragePos() {
        return pos;
    }

    @Override
    public void transferTick(ServerWorld world) {
        if (world.getTime() % 5 == 0) transfer(world);
    }

    @Override
    public boolean isActivated() {
        return getCachedState().get(ACTIVATED);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putFloat("stored_ether", storedEther);

        super.writeNbt(nbt, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        storedEther = nbt.getFloat("stored_ether");
    }

    @Override
    public float getDisplayEther() {
        return getStoredEther();
    }

    @Override
    public float getDisplayMaxEther() {
        return getMaxEther();
    }
}

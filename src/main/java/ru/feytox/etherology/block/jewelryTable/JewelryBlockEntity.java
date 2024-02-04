package ru.feytox.etherology.block.jewelryTable;

import io.wispforest.owo.util.ImplementedInventory;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import ru.feytox.etherology.block.etherealChannel.EtherealChannelBlockEntity;
import ru.feytox.etherology.magic.ether.EtherStorage;
import ru.feytox.etherology.particle.effects.ElectricityParticleEffect;
import ru.feytox.etherology.particle.effects.SparkParticleEffect;
import ru.feytox.etherology.particle.subtypes.ElectricitySubtype;
import ru.feytox.etherology.particle.subtypes.SparkSubtype;
import ru.feytox.etherology.recipes.jewelry.JewelryRecipe;
import ru.feytox.etherology.registry.particle.ServerParticleTypes;
import ru.feytox.etherology.util.feyapi.TickableBlockEntity;
import ru.feytox.etherology.util.feyapi.UniqueProvider;

import static ru.feytox.etherology.registry.block.EBlocks.JEWELRY_TABLE_BLOCK_ENTITY;

public class JewelryBlockEntity extends TickableBlockEntity implements EtherStorage, ImplementedInventory, UniqueProvider, NamedScreenHandlerFactory {

    private final JewelryTableInventory inventory;
    private float storedEther = 0;
    @Getter
    @Setter
    private Float cachedUniqueOffset = null;

    public JewelryBlockEntity(BlockPos pos, BlockState state) {
        super(JEWELRY_TABLE_BLOCK_ENTITY, pos, state);
        inventory = new JewelryTableInventory(this);
    }

    // STOPSHIP: сделать так, чтобы было сохранение предмета, а то он просто пропадает

    @Override
    public void serverTick(ServerWorld world, BlockPos blockPos, BlockState state) {
        if (inventory.isEmpty() || !inventory.hasRecipe()) {
            inventory.resetRecipe();
            // TODO: 30.01.2024 hard code to cross-block useless transfer
            decrement(1f);
        }
        if (world.getTime() % 40 == 0) decrement(1);
        if (!inventory.hasRecipe() || world.getTime() % 5 != 0) return;

        inventory.updateRecipe(world);
        JewelryRecipe recipe = inventory.getRecipe(world);
        if (recipe == null) return;
        if (storedEther < recipe.getEther()) return;

        storedEther = 0.0f;
        inventory.tryCraft(world);
        decrement(recipe.getEther());
        inventory.resetRecipe();

        Vec3d particlePos = blockPos.toCenterPos().add(0, 0.75d, 0);
        val effect = new SparkParticleEffect(ServerParticleTypes.SPARK, particlePos.add(0, 1.5d, 0), SparkSubtype.SIMPLE);
        effect.spawnParticles(world, 10, 0.25d, particlePos);
    }

    @Override
    public void clientTick(ClientWorld world, BlockPos blockPos, BlockState state) {
        if (inventory.isEmpty()) {
            EtherealChannelBlockEntity.spawnParticles(pos, world, Direction.UP);
            return;
        }

        if (world.getTime() % 7 != 0) return;
        val effect = ElectricityParticleEffect.of(world.getRandom(), ElectricitySubtype.SIMPLE);
        effect.spawnParticles(world, 3, 0.2d, blockPos.toCenterPos().add(0, 0.75d, 0));
    }

    @Override
    public float getMaxEther() {
        return Integer.MAX_VALUE;
    }

    @Override
    public float getStoredEther() {
        return storedEther;
    }

    @Override
    public float getTransferSize() {
        return 1.0f;
    }

    @Override
    public void setStoredEther(float value) {
        storedEther = value;
    }

    @Override
    public boolean isInputSide(Direction side) {
        return side.equals(Direction.DOWN);
    }

    @Override
    public @Nullable Direction getOutputSide() {
        return null;
    }

    @Override
    public BlockPos getStoragePos() {
        return pos;
    }

    @Override
    public void transferTick(ServerWorld world) {}

    @Override
    public boolean isActivated() {
        return false;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory.getItems();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        inventory.writeNbt(nbt);
        nbt.putFloat("ether", storedEther);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        inventory.readNbt(nbt);
        storedEther = nbt.getFloat("ether");
    }

    public void trySyncData() {
        markDirty();
        if (world instanceof ServerWorld serverWorld) syncData(serverWorld);
    }

    @Override
    public Text getDisplayName() {
        return Text.empty();
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new JewelryTableScreenHandler(syncId, inv, inventory);
    }
}

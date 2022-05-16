package net.brdle.delightful.common.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.entity.HeatableBlockEntity;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;

public class PizzaPeelBlockEntity extends SyncedBlockEntity implements HeatableBlockEntity {
    private final ItemStackHandler inventory = createHandler();
    private ItemStack stack;

    public PizzaPeelBlockEntity(BlockPos pos, BlockState state) {
        super(DelightfulTiles.PIZZA_PEEL.get(), pos, state);
        stack = ItemStack.EMPTY;
    }

    @Override
    public void load(@NotNull CompoundTag compound) {
        super.load(compound);
        inventory.deserializeNBT(compound.getCompound("Inventory"));
        stack = ItemStack.of(compound.getCompound("Skillet"));
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("Inventory", inventory.serializeNBT());
        compound.put("Skillet", stack.save(new CompoundTag()));
    }

    public CompoundTag writePizzaStoneItem(CompoundTag compound) {
        compound.put("Stone", stack.save(new CompoundTag()));
        return compound;
    }

    public void setPizzaStoneItem(ItemStack cop) {
        stack = cop.copy();
        inventoryChanged();
    }

    public ItemStack removeItem() {
        return inventory.extractItem(0, getStoredStack().getMaxStackSize(), false);
    }

    public IItemHandler getInventory() {
        return inventory;
    }

    public ItemStack getStoredStack() {
        return inventory.getStackInSlot(0);
    }

    public boolean hasStoredStack() {
        return !getStoredStack().isEmpty();
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler()
        {
            @Override
            protected void onContentsChanged(int slot) {
                inventoryChanged();
            }
        };
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
    }
}
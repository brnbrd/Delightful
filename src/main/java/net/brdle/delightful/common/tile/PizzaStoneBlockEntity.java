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

public class PizzaStoneBlockEntity extends SyncedBlockEntity implements HeatableBlockEntity {
    private final ItemStackHandler inventory = createHandler();
    private int cookingTime;
    private int cookingTimeTotal;
    private ItemStack stack;

    public PizzaStoneBlockEntity(BlockPos pos, BlockState state) {
        super(DelightfulTiles.PIZZA_STONE.get(), pos, state);
        stack = ItemStack.EMPTY;
    }

    public static void cookingTick(Level level, BlockPos pos, BlockState state, PizzaStoneBlockEntity stone) {
        boolean isHeated = stone.isHeated(level, pos);
        if (isHeated) {
            ItemStack cookingStack = stone.getStoredStack();
            if (cookingStack.isEmpty()) {
                stone.cookingTime = 0;
            } else {
                stone.cookAndOutputItems(cookingStack);
            }
        } else if (stone.cookingTime > 0) {
            stone.cookingTime = Mth.clamp(stone.cookingTime - 2, 0, stone.cookingTimeTotal);
        }
    }

    private void cookAndOutputItems(ItemStack cookingStack) {
        if (level == null) return;

        ++cookingTime;
        if (cookingTime >= cookingTimeTotal) {
            SimpleContainer wrapper = new SimpleContainer(cookingStack);
            /*Optional<CampfireCookingRecipe> recipe = getMatchingRecipe(wrapper);
            if (recipe.isPresent()) {
                ItemStack resultStack = recipe.get().assemble(wrapper);
                Direction direction = getBlockState().getValue(SkilletBlock.FACING).getClockWise();
                ItemUtils.spawnItemEntity(level, resultStack.copy(),
                        worldPosition.getX() + 0.5, worldPosition.getY() + 0.3, worldPosition.getZ() + 0.5,
                        direction.getStepX() * 0.08F, 0.25F, direction.getStepZ() * 0.08F);

                cookingTime = 0;
                inventory.extractItem(0, 1, false);
            }*/
        }
    }

    public boolean isCooking() {
        return isHeated() && hasStoredStack();
    }

    public boolean isHeated() {
        return level != null && isHeated(level, worldPosition);
    }

    @Override
    public void load(@NotNull CompoundTag compound) {
        super.load(compound);
        inventory.deserializeNBT(compound.getCompound("Inventory"));
        cookingTime = compound.getInt("CookTime");
        cookingTimeTotal = compound.getInt("CookTimeTotal");
        stack = ItemStack.of(compound.getCompound("Skillet"));
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("Inventory", inventory.serializeNBT());
        compound.putInt("CookTime", cookingTime);
        compound.putInt("CookTimeTotal", cookingTimeTotal);
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

    /*public ItemStack addItemToCook(ItemStack addedStack, @Nullable Player player) {
        Optional<CampfireCookingRecipe> recipe = getMatchingRecipe(new SimpleContainer(addedStack));
        if (recipe.isPresent()) {
            cookingTimeTotal = SkilletBlock.getSkilletCookingTime(recipe.get().getCookingTime(), fireAspectLevel);
            boolean wasEmpty = getStoredStack().isEmpty();
            ItemStack remainderStack = inventory.insertItem(0, addedStack.copy(), false);
            if (!ItemStack.matches(remainderStack, addedStack)) {
                lastRecipeID = recipe.get().getId();
                cookingTime = 0;
                if (wasEmpty && level != null && isHeated(level, worldPosition)) {
                    level.playSound(null, worldPosition.getX() + 0.5F, worldPosition.getY() + 0.5F, worldPosition.getZ() + 0.5F, ModSounds.BLOCK_SKILLET_ADD_FOOD.get(), SoundSource.BLOCKS, 0.8F, 1.0F);
                }
                return remainderStack;
            }
        } else if (player != null) {
            player.displayClientMessage(TextUtils.getTranslation("block.skillet.invalid_item"), true);
        }
        return addedStack;
    }*/

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
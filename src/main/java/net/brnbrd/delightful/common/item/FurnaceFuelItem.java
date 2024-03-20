package net.brnbrd.delightful.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class FurnaceFuelItem extends DItem {
  private final int fuelTime;

  public FurnaceFuelItem(Properties pProperties, int fuelTime) {
    super(pProperties);
    this.fuelTime = fuelTime;
  }

  @Override
  public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
    if (this.enabled()) {
      comps.add(Component.translatable("tooltip.sneak_right").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.UNDERLINE));
      comps.add(
        Component.literal(String.valueOf(this.fuelTime / 20)).withStyle(ChatFormatting.YELLOW)
          .append(Component.translatable("tooltip.furnace_fuel_burn_time").withStyle(ChatFormatting.WHITE))
      );
    }
    super.appendHoverText(stack, level, comps, pIsAdvanced);
  }

  @Override
  public @NotNull InteractionResult useOn(UseOnContext con) {
    return useFuel(con.getItemInHand(),
      con.getLevel().getExistingBlockEntity(con.getClickedPos()),
      con.getPlayer());
  }

  public static InteractionResult useFuel(ItemStack stack, BlockEntity entity, Player player) {
    if (
      stack.getItem() instanceof FurnaceFuelItem fuel &&
      entity instanceof AbstractFurnaceBlockEntity furnace
    ) {
      ContainerData data = furnace.dataAccess;
      int newTime = data.get(0) + fuel.fuelTime + 1;
      data.set(0, newTime);
      data.set(1, newTime);
      furnace.setChanged();
      shrinkAdd(player, stack);
      return InteractionResult.CONSUME_PARTIAL;
    }
    return InteractionResult.FAIL;
  }

  private static void shrinkAdd(Player p, ItemStack i) {
    if (i.hasCraftingRemainingItem()) {
      p.addItem(i.getCraftingRemainingItem());
    }
    i.shrink(1);
  }

  @Override
  public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
    return this.fuelTime;
  }
}

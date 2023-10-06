package net.brnbrd.delightful.common.item.knife.compat.twilightforest;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SteeleafKnifeItem extends CompatKnifeItem {
  public SteeleafKnifeItem(Properties properties) {
    super("twilightforest", Util.it("forge", "ingots/steeleaf"), DelightfulTiers.STEELEAF, properties, ChatFormatting.AQUA);
  }

  @Override
  public @Nullable RecipeType<?> getRecipeType() {
    return null;
  }

  @Override
  public void fillItemCategory(@NotNull CreativeModeTab tab, @NotNull NonNullList<ItemStack> list) {
    if (this.allowedIn(tab)) {
      ItemStack stack = new ItemStack(this);
      stack.enchant(Enchantments.SMITE, 2);
      list.add(stack);
    }
  }
}

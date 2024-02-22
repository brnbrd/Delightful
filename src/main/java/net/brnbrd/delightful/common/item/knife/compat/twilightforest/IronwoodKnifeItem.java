package net.brnbrd.delightful.common.item.knife.compat.twilightforest;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModEnchantments;

public class IronwoodKnifeItem extends CompatKnifeItem {
  public IronwoodKnifeItem(Item.Properties properties) {
    super("twilightforest", DelightfulItems.ingot("ironwood"), DelightfulTiers.IRONWOOD, properties, ChatFormatting.AQUA);
  }

  @Override
  public String[] getConflicts() {
    return new String[]{"twilightdelight"};
  }

  @Override
  public @Nullable RecipeType<?> getRecipeType() {
    return null;
  }

  @Override
  public @NotNull ItemStack getDefaultInstance() {
    return Util.enchant(super.getDefaultInstance(), ModEnchantments.BACKSTABBING.get(), 1);
  }
}

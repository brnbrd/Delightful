package net.brnbrd.delightful.common.item.knife.twilightforest;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModEnchantments;

public class IronwoodKnifeItem extends CompatKnifeItem {
  public IronwoodKnifeItem(Item.Properties properties) {
    super("twilightforest", Util.it("forge", "ingots/ironwood"), DelightfulTiers.IRONWOOD, properties, null, ChatFormatting.AQUA);
  }

  @Override
  public void fillItemCategory(@NotNull CreativeModeTab tab, @NotNull NonNullList<ItemStack> list) {
    if (this.allowedIn(tab)) {
      ItemStack stack = new ItemStack(this);
      stack.enchant(ModEnchantments.BACKSTABBING.get(), 1);
      list.add(stack);
    }
  }

  @Override
  public boolean genRecipe() {
    return false;
  }
}

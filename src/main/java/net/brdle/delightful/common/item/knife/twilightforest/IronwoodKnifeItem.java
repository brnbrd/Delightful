package net.brdle.delightful.common.item.knife.twilightforest;

import net.brdle.delightful.Util;
import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.registry.ModEnchantments;
import java.util.Optional;

public class IronwoodKnifeItem extends CompatKnifeItem {
  public IronwoodKnifeItem(Item.Properties properties) {
    super("twilightforest", Util.it("forge", "ingots/ironwood"), DelightfulTiers.IRONWOOD, 0.5F, -2.0F, properties, Optional.empty(), ChatFormatting.AQUA);
  }

  @Override
  public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
    if (this.allowedIn(tab)) {
      ItemStack stack = new ItemStack(this);
      stack.enchant(ModEnchantments.BACKSTABBING.get(), 1);
      list.add(stack);
    }
  }
}

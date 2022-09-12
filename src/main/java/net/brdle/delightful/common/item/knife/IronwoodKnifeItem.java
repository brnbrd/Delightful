package net.brdle.delightful.common.item.knife;

import net.brdle.delightful.common.item.DelightfulTiers;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.registry.ModEnchantments;

public class IronwoodKnifeItem extends CompatKnifeItem {
  public IronwoodKnifeItem(Properties properties) {
    super("twilightforest", new ResourceLocation("forge", "ingots/ironwood"), DelightfulTiers.IRONWOOD, 0.5F, -2.0F, properties);
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

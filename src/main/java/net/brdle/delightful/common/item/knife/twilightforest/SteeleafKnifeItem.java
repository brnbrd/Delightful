package net.brdle.delightful.common.item.knife.twilightforest;

import net.brdle.delightful.Util;
import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;

public class SteeleafKnifeItem extends CompatKnifeItem {
  public SteeleafKnifeItem(Properties properties) {
    super("twilightforest", Util.rl("forge", "ingots/steeleaf"), DelightfulTiers.STEELEAF, 0.5F, -2.0F, properties);
  }

  @Override
  public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
    if (this.allowedIn(tab)) {
      ItemStack stack = new ItemStack(this);
      stack.enchant(Enchantments.SMITE, 2);
      list.add(stack);
    }
  }
}

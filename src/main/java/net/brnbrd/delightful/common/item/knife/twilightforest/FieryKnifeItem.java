package net.brnbrd.delightful.common.item.knife.twilightforest;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

public class FieryKnifeItem extends CompatKnifeItem {
  public FieryKnifeItem(Properties properties) {
    super("twilightforest", Util.it("forge", "ingots/fiery"), DelightfulTiers.FIERY, properties, Component.translatable("item.twilightforest.fiery_sword.tooltip").withStyle(ChatFormatting.GRAY), null, ChatFormatting.YELLOW);
  }

  @Override
  public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack, @NotNull Enchantment enchantment) {
    return enchantment != Enchantments.FIRE_ASPECT && super.canApplyAtEnchantingTable(stack, enchantment);
  }

  @Override
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return !EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.FIRE_ASPECT) && super.isBookEnchantable(stack, book);
  }

  @Override
  public Ingredient getRod() {
    return Ingredient.of(Items.BLAZE_ROD);
  }
}

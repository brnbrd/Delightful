package net.brnbrd.delightful.common.item.knife.compat.twilightforest;

import net.brnbrd.delightful.common.item.DelightfulItems;
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
import java.util.List;

public class FieryKnifeItem extends CompatKnifeItem {
  public FieryKnifeItem(Properties properties) {
    super("twilightforest", DelightfulItems.ingot("fiery"), DelightfulTiers.FIERY, properties, ChatFormatting.YELLOW);
  }

  @Override
  public List<Component> getTools() {
    return List.of(
        Component.translatable("item.twilightforest.fiery_sword.tooltip").withStyle(ChatFormatting.GRAY)
    );
  }

  @Override
  public Ingredient getRod() {
    return Ingredient.of(Items.BLAZE_ROD);
  }

  @Override
  public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack, @NotNull Enchantment enchantment) {
    return enchantment != Enchantments.FIRE_ASPECT && super.canApplyAtEnchantingTable(stack, enchantment);
  }

  @Override
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return !EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.FIRE_ASPECT) && super.isBookEnchantable(stack, book);
  }
}

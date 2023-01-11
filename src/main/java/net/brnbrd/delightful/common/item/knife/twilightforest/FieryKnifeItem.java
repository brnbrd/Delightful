package net.brnbrd.delightful.common.item.knife.twilightforest;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;

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
  public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
    boolean result = super.hurtEnemy(stack, target, attacker);
    if (result && !target.getLevel().isClientSide() && !target.fireImmune()) {
      target.setSecondsOnFire(15);
    } else {
      for (int var1 = 0; var1 < 20; ++var1) {
        double px = target.getX() + target.getLevel().getRandom().nextFloat() * target.getBbWidth() * 2.0F - target.getBbWidth();
        double py = target.getY() + target.getLevel().getRandom().nextFloat() * target.getBbHeight();
        double pz = target.getZ() + target.getLevel().getRandom().nextFloat() * target.getBbWidth() * 2.0F - target.getBbWidth();
        target.getLevel().addParticle(ParticleTypes.FLAME, px, py, pz, 0.02, 0.02, 0.02);
      }
    }
    return result;
  }

  @Override
  public Supplier<Ingredient> getRod() {
    return Util.ing(Items.BLAZE_ROD);
  }
}

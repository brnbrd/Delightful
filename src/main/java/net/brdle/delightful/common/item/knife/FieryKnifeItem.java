package net.brdle.delightful.common.item.knife;

import net.brdle.delightful.common.item.DelightfulTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import java.util.function.Supplier;

public class FieryKnifeItem extends CompatKnifeItem {
  public FieryKnifeItem(Properties properties) {
    super("twilightforest", new ResourceLocation("forge", "ingots/fiery"), DelightfulTiers.FIERY, 0.5F, -2.0F, properties, Component.translatable("item.twilightforest.fiery_sword.tooltip").withStyle(ChatFormatting.GRAY));
  }

  @Override
  public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
    return enchantment != Enchantments.FIRE_ASPECT && super.canApplyAtEnchantingTable(stack, enchantment);
  }

  @Override
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return !EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.FIRE_ASPECT) && super.isBookEnchantable(stack, book);
  }

  @Override
  public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
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
    return () -> Ingredient.of(Items.BLAZE_ROD);
  }
}

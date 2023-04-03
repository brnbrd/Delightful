package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.FRCompat;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public class CaffeinatedItem extends DrinkItem {

  public CaffeinatedItem(Properties properties, Supplier<MobEffect> effect, int duration, int amplifier, float heal, int feed) {
    super(properties, effect, duration, amplifier, heal, feed, true, false);
  }

  public CaffeinatedItem(Properties properties, Supplier<MobEffect> effect, int duration, int amplifier, float heal, int feed, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
    super(properties, effect, duration, amplifier, heal, feed, hasPotionEffectTooltip, hasCustomTooltip);
  }

  @Override
  public void affectConsumer(@NotNull ItemStack stack, @NotNull Level worldIn, LivingEntity consumer) {
    super.affectConsumer(stack, worldIn, consumer);
    if (Mods.loaded(Mods.FR)) {
      consumer.addEffect(new MobEffectInstance(FRCompat.CAFFEINATED.get(), 1200, 0));
    }
  }
}

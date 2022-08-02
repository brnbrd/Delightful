package net.brdle.delightful.common.item.food;

import net.brdle.delightful.compat.CompatEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import java.util.function.Supplier;

public class CaffeinatedItem extends DrinkItem {
  public CaffeinatedItem(Properties properties, Supplier<MobEffect> effect, int duration, int amplifier, float heal, int feed) {
    super(properties, effect, duration, amplifier, heal, feed);
  }

  @Override
  public void affectConsumer(ItemStack stack, Level worldIn, LivingEntity consumer) {
    super.affectConsumer(stack, worldIn, consumer);
    if (ModList.get().isLoaded("farmersrespite")) {
      consumer.addEffect(new MobEffectInstance(CompatEffects.CAFFEINATED.get(), 1200, 0));
    }
  }
}

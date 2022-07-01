package net.brdle.delightful.common.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.MelonJuiceItem;

public class PricklyPearJuiceItem extends MelonJuiceItem {
    public PricklyPearJuiceItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level worldIn, LivingEntity consumer) {
        consumer.addEffect(new MobEffectInstance(MobEffects.SATURATION, 400, 1));
    }
}

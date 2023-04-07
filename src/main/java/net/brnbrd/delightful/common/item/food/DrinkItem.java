package net.brnbrd.delightful.common.item.food;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class DrinkItem extends DrinkableItem {
    private final float heal;

    public DrinkItem(Item.Properties properties, float heal, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasPotionEffectTooltip, hasCustomTooltip);
        this.heal = heal;
    }

    @Override
    public void affectConsumer(@NotNull ItemStack stack, @NotNull Level worldIn, @NotNull LivingEntity consumer) {
        if (this.heal > 0.0F) consumer.heal(this.heal);
        super.affectConsumer(stack, worldIn, consumer);
    }
}

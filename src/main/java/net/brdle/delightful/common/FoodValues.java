package net.brdle.delightful.common;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FoodValues {
    public static final FoodProperties CHEESEBURGER = (new FoodProperties.Builder()).nutrition(11).saturationMod(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0), 1.0F).build();
    public static final FoodProperties DELUXE_CHEESEBURGER = (new FoodProperties.Builder()).nutrition(11).saturationMod(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0), 1.0F).build();
    public static final FoodProperties MARSHMALLOW_STICK = (new FoodProperties.Builder()).nutrition(11).saturationMod(0.8F).build();
    public static final FoodProperties COOKED_MARSHMALLOW_STICK = (new FoodProperties.Builder()).nutrition(11).saturationMod(0.8F).build();
    public static final FoodProperties SMORE = (new FoodProperties.Builder()).nutrition(11).saturationMod(0.8F).build();
}
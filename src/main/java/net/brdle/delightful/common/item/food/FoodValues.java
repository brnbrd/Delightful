package net.brdle.delightful.common.item.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class FoodValues {
    public static final FoodProperties CHEESEBURGER = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 1.0F).build();
    public static final FoodProperties DELUXE_CHEESEBURGER = (new FoodProperties.Builder()).nutrition(13).saturationMod(0.9F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 900, 0), 1.0F).build();
    public static final FoodProperties MARSHMALLOW_STICK = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).build();
    public static final FoodProperties COOKED_MARSHMALLOW_STICK = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.4F).build();
    public static final FoodProperties SMORE = (new FoodProperties.Builder()).nutrition(9).saturationMod(0.5F).build();
    public static final FoodProperties CRAB_RANGOON = (new FoodProperties.Builder()).nutrition(9).saturationMod(0.4F).build();
    public static final FoodProperties CHUNKWICH = (new FoodProperties.Builder()).nutrition(11).saturationMod(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 10, 0), 0.5F).build();
    public static final FoodProperties JELLY_BOTTLE = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.3F).build();
    public static final FoodProperties NUT_BUTTER_BOTTLE = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.3F).build();
    public static final FoodProperties NUT_BUTTER_AND_JELLY_SANDWICH = (new FoodProperties.Builder()).nutrition(10).saturationMod(0.5F)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 3600, 0), 1.0F).build();
}
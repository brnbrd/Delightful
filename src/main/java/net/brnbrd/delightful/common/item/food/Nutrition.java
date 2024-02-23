package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Mods;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class Nutrition {
    public static final FoodProperties CHEESEBURGER = (new FoodProperties.Builder()).nutrition(13).saturationMod(0.8F)
        .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 600, 0), 1.0F)
        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 1.0F).build();
    public static final FoodProperties DELUXE_CHEESEBURGER = (new FoodProperties.Builder()).nutrition(16).saturationMod(1.0F)
        .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 4800, 0), 1.0F)
        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0), 1.0F).build();
    public static final FoodProperties MARSHMALLOW_STICK = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).build();
    public static final FoodProperties COOKED_MARSHMALLOW_STICK = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.4F).build();
    public static final FoodProperties SMORE = (new FoodProperties.Builder()).nutrition(9).saturationMod(0.5F)
        .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 3600, 0), 1.0F).build();
    public static final FoodProperties CRAB_RANGOON = (new FoodProperties.Builder()).nutrition(9).saturationMod(0.4F).build();
    public static final FoodProperties CHUNKWICH = (new FoodProperties.Builder()).nutrition(11).saturationMod(0.3F)
        .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 600, 0), 1.0F).build();
    public static final FoodProperties CHUNK_NUGGET = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.4F).build();
    public static final FoodProperties JELLY_BOTTLE = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.3F).build();
    public static final FoodProperties GLOW_JELLY_BOTTLE = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.3F)
        .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 900, 0), 1.0F).build();
    public static final FoodProperties NUT_BUTTER_BOTTLE = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.3F).build();
    public static final FoodProperties NUT_BUTTER_AND_JELLY_SANDWICH = (new FoodProperties.Builder()).nutrition(10).saturationMod(0.5F)
        .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 3600, 0), 1.0F).build();
    public static final FoodProperties HONEY_GLAZED_WALNUT = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.5F).build();
    public static final FoodProperties ACORN = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties GREEN_TEA_LEAF = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.2F).build();
    public static final FoodProperties ANIMAL_FAT = (new FoodProperties.Builder()).nutrition(1).saturationMod(2.0F).alwaysEat().fast().build();
    public static final FoodProperties CHOPPED_CLOVER = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).fast().build();
    public static final FoodProperties CACTUS_FLESH = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).build();
    public static final FoodProperties CACTUS_STEAK = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.5F).build();
    public static final FoodProperties FIELD_SALAD = (new FoodProperties.Builder()).nutrition(10).saturationMod(0.55F)
        .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1.0F)
        .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 600, 0), 1.0F).build();
    public static final FoodProperties ROCK_CANDY = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.2F).fast()
        .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 0), 1.0F).build();
    public static final FoodProperties VENISON_CHOPS = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).meat().fast().build();
    public static final FoodProperties COOKED_VENISON_CHOPS = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.8F).meat().fast().build();
    public static final FoodProperties RAW_GOAT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();
    public static final FoodProperties COOKED_GOAT = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.8F).meat().build();
    public static final FoodProperties CANTALOUPE_SLICE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.8F)
        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0), 1.0F).build();
    public static final FoodProperties COCONUT_CURRY = (new FoodProperties.Builder()).nutrition(15).saturationMod(0.9F)
        .effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 6000, 0), 1.0F)
        .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 1.0F).build();
    public static final FoodProperties SINIGANG = (new FoodProperties.Builder()).nutrition(15).saturationMod(0.9F)
        .effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 6000, 0), 1.0F)
        .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 1.0F).build();
    public static final FoodProperties ENDER_NECTAR = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.1F).alwaysEat()
        .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 60, 0), 1.0F).build();
    public static final FoodProperties PRICKLY_PEAR_JUICE = (new FoodProperties.Builder())
        .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 400, 0), 1.0F).build();
    public static final FoodProperties LAVENDER_TEA = (new FoodProperties.Builder()).alwaysEat()
        .effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 1200, 0), 1.0F)
        .effect(() -> new MobEffectInstance(Mods.getCaffeinated().get(), 200, 0), 1.0F).build();
    public static final FoodProperties AZALEA_TEA = (new FoodProperties.Builder()).alwaysEat()
        .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 1200, 0), 1.0F)
        .effect(() -> new MobEffectInstance(Mods.getCaffeinated().get(), 200, 0), 1.0F).build();
    public static final FoodProperties MATCHA_LATTE = (new FoodProperties.Builder()).alwaysEat().nutrition(6).saturationMod(0.5F)
        .effect(() -> new MobEffectInstance(Mods.getVitality().get(), 1200, 1), 1.0F)
        .effect(() -> new MobEffectInstance(Mods.getCaffeinated().get(), 1200, 0), 1.0F).build();
    public static final FoodProperties BERRY_MATCHA_LATTE = (new FoodProperties.Builder()).alwaysEat().nutrition(8).saturationMod(0.65F)
        .effect(() -> new MobEffectInstance(Mods.getVitality().get(), 1200, 1), 1.0F)
        .effect(() -> new MobEffectInstance(Mods.getCaffeinated().get(), 1200, 0), 1.0F).build();
    public static final FoodProperties MATCHA_ICE_CREAM = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.5F)
        .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0F)
        .effect(() -> new MobEffectInstance(Mods.getVitality().get(), 1200, 1), 1.0F)
        .effect(() -> new MobEffectInstance(Mods.getCaffeinated().get(), 1200, 0), 1.0F).build();
    public static final FoodProperties SALMONBERRY_ICE_CREAM = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.4F)
        .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2), 1.0F)
        .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 1200), 1.0F).build();
    public static final FoodProperties SALMONBERRIES = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.6F)
        .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 100, 0), 1.0F).build();
    public static final FoodProperties SALMONBERRY_PIE_SLICE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast()
        .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 600, 0), 1.0F).build();
    public static final FoodProperties SALMONBERRY_GUMMY = (new FoodProperties.Builder())
        .nutrition(2).saturationMod(0.0F).alwaysEat()
        .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 400, 0), 1.0F).build();
}
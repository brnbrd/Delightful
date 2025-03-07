package net.brnbrd.delightful.compat.ars_nouveau;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Modid;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ArsNouveauCompat {
	public static MobEffect getManaRegen() {
		return Util.effect(Modid.AN, "mana_regen", MobEffects.ABSORPTION);
	}

	public static final FoodProperties SOURCE_BERRY_PIE_SLICE = (new FoodProperties.Builder())
			.nutrition(3).saturationMod(0.3F).fast()
			.effect(() -> new MobEffectInstance(getManaRegen(), 200, 1), 1F).build();

	public static final FoodProperties SOURCE_BERRY_COOKIE = (new FoodProperties.Builder())
			.nutrition(2).saturationMod(0.1F).fast()
			.effect(() -> new MobEffectInstance(getManaRegen(), 60, 0), 1F).build();

	public static final FoodProperties SOURCE_BERRY_ICE_CREAM = (new FoodProperties.Builder())
			.nutrition(6).saturationMod(0.4F)
			.effect(() -> new MobEffectInstance(getManaRegen(), 1200, 0), 1F).build();

	public static final FoodProperties SOURCE_BERRY_MILKSHAKE = (new FoodProperties.Builder())
			.nutrition(2).saturationMod(1.5F).alwaysEat()
			.effect(() -> new MobEffectInstance(getManaRegen(), 600, 0), 1F).build();

	public static final FoodProperties SOURCE_BERRY_GUMMY = (new FoodProperties.Builder())
			.nutrition(2).saturationMod(0F).alwaysEat()
			.effect(() -> new MobEffectInstance(getManaRegen(), 80, 2), 1F).build();
}
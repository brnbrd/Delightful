package net.brnbrd.delightful.compat.abnormals;

import net.brnbrd.delightful.compat.Modid;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class AtmosphericCompat {
	public static final String YUCCA = "yucca_fruit";
	public static final String PASSION_FRUIT_TART = "passion_fruit_tart";

	public static MobEffect getSpitting() {
		return Modid.AT.effect("spitting", MobEffects.MOVEMENT_SPEED);
	}

	public static final FoodProperties PASSION_FRUIT_TART_SLICE = (new FoodProperties.Builder())
		.nutrition(3)
		.saturationMod(0.3F)
		.fast()
		.effect(() -> new MobEffectInstance(getSpitting(), 160, 0, false, false), 1F)
		.build();
}
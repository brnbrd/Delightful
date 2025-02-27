package net.brnbrd.delightful.compat;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import java.util.function.Supplier;

public class BWGCompat {
	public static final String green_apple = "green_apple";
	public static final String yucca = "yucca_fruit";
	public static final String baobab = "baobab_fruit";
	public static final String prairie_grass = "prairie_grass";
	public static final String tall_prairie_grass = "tall_prairie_grass";
	public static final String beach_grass = "beach_grass";
	public static final String blueberry_pie = "blueberry_pie";
	public static final String green_apple_pie = "green_apple_pie";

	public static final Supplier<FoodProperties> GREEN_APPLE_PIE_SLICE = () -> (new FoodProperties.Builder())
		.nutrition(3)
		.saturationMod(0.3F)
		.fast()
		.effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 600, 0, false, true), 1.0F)
		.build();
}
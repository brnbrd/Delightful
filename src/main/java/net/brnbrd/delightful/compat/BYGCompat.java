package net.brnbrd.delightful.compat;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import java.util.function.Supplier;

public class BYGCompat {

	public static final String pendorite_ingot = "pendorite_ingot";
	public static final String green_apple = "green_apple";
	public static final String prairie_grass = "prairie_grass";
	public static final String tall_prairie_grass = "tall_prairie_grass";
	public static final String beach_grass = "beach_grass";
	public static final String blueberry_pie = "blueberry_pie";
	public static final String green_apple_pie = "green_apple_pie";
	public static final String nightshade_berry_pie = "nightshade_berry_pie";
	public static final String crimson_berry_pie = "crimson_berry_pie";
	public static final String blueberry_pie_slice = "blueberry_pie_slice";
	public static final String green_apple_pie_slice = "green_apple_pie_slice";
	public static final String nightshade_berry_pie_slice = "nightshade_berry_pie_slice";
	public static final String crimson_berry_pie_slice = "crimson_berry_pie_slice";

	public static final Supplier<FoodProperties> BLUEBERRY_PIE_SLICE = () -> (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast()
		.effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1), 1.0F)
		.build();

	public static final Supplier<FoodProperties> GREEN_APPLE_PIE_SLICE = () -> (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast()
		.effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 1.0F)
		.effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 400, 0), 1.0F)
		.build();

	public static final Supplier<FoodProperties> CRIMSON_BERRY_PIE_SLICE = () -> (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast()
		.effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 1.0F)
		.effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400, 0), 1.0F)
		.build();

	public static final Supplier<FoodProperties> NIGHTSHADE_BERRY_PIE_SLICE = () -> (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast()
		.effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 1.0F)
		.effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0), 1.0F)
		.build();
}

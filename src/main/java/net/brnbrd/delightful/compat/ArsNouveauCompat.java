package net.brnbrd.delightful.compat;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class ArsNouveauCompat {

	public static final String pie = "source_berry_pie";

	public static final FoodProperties SOURCE_BERRY_PIE_SLICE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast()
		.effect(() -> new MobEffectInstance(Mods.getManaRegen().get(), 300, 1), 1.0F).build();
}

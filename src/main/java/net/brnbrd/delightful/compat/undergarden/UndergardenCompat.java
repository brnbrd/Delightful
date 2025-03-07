package net.brnbrd.delightful.compat.undergarden;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Modid;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import java.util.function.Supplier;

public class UndergardenCompat {
	public static MobEffect getVirulentResistance() {
		return Util.effect(Modid.UG, "virulent_resistance", MobEffects.FIRE_RESISTANCE);
	}

	public static final Supplier<FoodProperties> GLOOMGOURD_PIE_SLICE = () -> new FoodProperties.Builder()
		.nutrition(3).saturationMod(0.3F)
		.fast()
		.effect(() -> new MobEffectInstance(getVirulentResistance(), 600, 0), 1F).build();
}
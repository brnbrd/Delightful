package net.brnbrd.delightful.compat;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import java.util.function.Supplier;

public class UndergardenCompat {

	public static final String pie = "gloomgourd_pie";

	public static final Supplier<FoodProperties> GLOOMGOURD_PIE_SLICE = () -> (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast()
		.effect(() -> new MobEffectInstance(Mods.getVirulentResistance().get(), 160, 0), 1.0F).build();
}

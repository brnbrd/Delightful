package net.brnbrd.delightful.compat;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import quek.undergarden.registry.UGEffects;

import java.util.function.Supplier;

public class UndergardenSuppliers {

	public static final Supplier<MobEffectInstance> VIRULENT_RESISTANCE = () -> new MobEffectInstance(UGEffects.VIRULENT_RESISTANCE.get(), 160, 0);

	public static final Supplier<FoodProperties> PIE_SLICE = () -> (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast()
		.effect(VIRULENT_RESISTANCE, 1.0F)
		.build();
}

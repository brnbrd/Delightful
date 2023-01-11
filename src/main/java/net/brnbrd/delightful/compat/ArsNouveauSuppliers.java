package net.brnbrd.delightful.compat;

import com.hollingsworth.arsnouveau.common.potions.ModPotions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import java.util.function.Supplier;

public class ArsNouveauSuppliers {

	public static final Supplier<MobEffectInstance> MANA_REGEN = () -> new MobEffectInstance(ModPotions.MANA_REGEN_EFFECT.get(), 300, 1);

	public static final Supplier<FoodProperties> PIE_SLICE = () -> (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast()
		.effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 1.0F)
		.effect(MANA_REGEN, 1.0F)
		.build();
}
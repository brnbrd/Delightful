package net.brnbrd.delightful.compat.brewinandchewin;

import net.brnbrd.delightful.compat.Modid;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;

public class BrewinChewinCompat {
	public static final ResourceLocation glowMarmalade = Modid.BC.rl("glow_berry_marmalade");
	public static final TagKey<Item> FERMENTED_DRINKS = Modid.BC.it("fermented_drinks");
	public static final TagKey<Item> PIZZA_TOPPINGS = Modid.BC.it("pizza_toppings");

	public static MobEffect getTipsy() {
		return Modid.BC.effect("tipsy", MobEffects.WEAKNESS);
	}

	// Bad Luck I is backup effect, with half duration (rounded down)
	public static MobEffectInstance getIntoxicationOrHalfBadLuck(int duration, int amplifier) {
		return Modid.BC.effectInstance(
			"intoxication", duration, amplifier,
			MobEffects.UNLUCK, duration / 2, 0
		);
	}

	public static Item getEnderNectar() {
		return BCEnderNectarCompat.ENDER_NECTAR.get();
	}
}
package net.brnbrd.delightful.compat.collectorsreap;

import net.brnbrd.delightful.compat.Modid;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class CRCompat {
	public static final String CORROSION = "corrosion";

	// Resistance I is backup effect, with half duration (rounded down)
	public static MobEffectInstance getCorrosionOrResistance(int duration, int amplifier) {
		return Modid.CR.effectInstance(
			CORROSION, duration, amplifier,
			MobEffects.DAMAGE_RESISTANCE, duration / 2, 0
		);
	}

	// Fire Resistance is backup effect, with half duration (rounded down)
	public static MobEffectInstance getCorrosionOrFireResistance(int duration, int amplifier) {
		return Modid.CR.effectInstance(
			CORROSION, duration, amplifier,
			MobEffects.FIRE_RESISTANCE, duration / 2, 0
		);
	}
}
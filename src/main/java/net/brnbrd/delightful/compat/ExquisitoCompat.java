package net.brnbrd.delightful.compat;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

public class ExquisitoCompat {
	public static MobEffect getResonance() {
		return Modid.EXQ.effect("resonance", MobEffects.DIG_SPEED);
	}
}
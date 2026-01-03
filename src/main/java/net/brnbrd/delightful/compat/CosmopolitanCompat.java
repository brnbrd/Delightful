package net.brnbrd.delightful.compat;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

public class CosmopolitanCompat {
	public static MobEffect getTracer() {
		return Modid.COS.effect("tracer", MobEffects.GLOWING);
	}
}
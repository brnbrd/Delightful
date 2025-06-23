package net.brnbrd.delightful.compat;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import org.jetbrains.annotations.NotNull;

public class CosmopolitanCompat {
	public static @NotNull MobEffect getTracer() {
		return Modid.COS.effect("tracer", MobEffects.GLOWING);
	}
}
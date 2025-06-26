package net.brnbrd.delightful.compat;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public class CosmopolitanCompat {
	public static @NotNull Supplier<MobEffect> getTracer() {
		return () -> Modid.COS.effect("tracer", MobEffects.GLOWING);
	}
}
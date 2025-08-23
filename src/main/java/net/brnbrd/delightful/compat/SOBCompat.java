package net.brnbrd.delightful.compat;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;

public class SOBCompat {
	public static @NotNull Supplier<MobEffect> getToughness() {
		return () -> Modid.SOB.effect("toughness", MobEffects.DAMAGE_RESISTANCE);
	}

	public static @NotNull Supplier<MobEffect> getSpite() {
		return () -> Modid.SOB.effect("spite", MobEffects.FIRE_RESISTANCE);
	}
}
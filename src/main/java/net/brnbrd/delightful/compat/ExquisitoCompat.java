package net.brnbrd.delightful.compat;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public class ExquisitoCompat {
	public static @NotNull Supplier<MobEffect> getResonance() {
		return () -> Modid.EXQ.effect("resonance", MobEffects.WEAKNESS);
	}
}
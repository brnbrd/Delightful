package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.Util;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import java.util.function.Supplier;

public class TeaCompat {
	public static Supplier<MobEffect> getGreenTeaEffect() {
		return () ->
			Modid.FR.effect("vitality",
			Modid.YH.effect("tea_polyphenols",
			MobEffects.REGENERATION // Backup effect
		));
	}

	public static Supplier<MobEffect> getYellowTeaEffect() {
		return () -> MobEffects.DAMAGE_RESISTANCE;
	}

	public static Supplier<MobEffect> getCaffeinated() {
		return () ->
			Util.effect(Modid.FR, "caffeinated",
			Util.effect(Modid.YH, "sober",
			MobEffects.DIG_SPEED // Backup effect
		));
	}
}
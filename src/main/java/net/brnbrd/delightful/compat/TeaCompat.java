package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.Util;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import java.util.function.Supplier;

public class TeaCompat {
	public static Supplier<MobEffect> getGreenTeaEffect() {
		return () ->
			Util.effect(Modid.FR, "vitality",
			Util.effect(Modid.YH, "tea_polyphenols",
			MobEffects.REGENERATION // Backup effect
		));
	}

	public static Supplier<MobEffect> getCaffeinated() {
		return () ->
			Util.effect(Modid.FR, "caffeinated",
			Util.effect(Modid.YH, "sober",
			MobEffects.DIG_SPEED // Backup effect
		));
	}
}
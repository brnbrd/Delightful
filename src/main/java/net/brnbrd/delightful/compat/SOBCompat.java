package net.brnbrd.delightful.compat;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

public class SOBCompat {
	public static MobEffect getToughness() {
		return Modid.SOB.effect("toughness", MobEffects.DAMAGE_RESISTANCE);
	}

	public static MobEffect getSpite() {
		return Modid.SOB.effect("spite", MobEffects.FIRE_RESISTANCE);
	}
}
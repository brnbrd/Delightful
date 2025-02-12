package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.Util;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

public class NeapolitanCompat {
	public static MobEffect getSugarRush() {
		return Util.effect(Mods.N, "sugar_rush", MobEffects.MOVEMENT_SPEED);
	}
}
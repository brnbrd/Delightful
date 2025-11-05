package net.brnbrd.delightful.compat.abnormals;

import net.brnbrd.delightful.compat.Modid;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

public class NeapolitanCompat {
	public static MobEffect getSugarRush() {
		return Modid.N.effect("sugar_rush", MobEffects.MOVEMENT_SPEED);
	}
}
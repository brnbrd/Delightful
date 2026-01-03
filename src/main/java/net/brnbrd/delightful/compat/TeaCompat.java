package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.Util;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

public class TeaCompat {
	public static MobEffect getGreenTeaEffect() {
		return Modid.RESP.effect("vitality", Modid.YH.effect("tea_polyphenols", MobEffects.REGENERATION));
	}

	public static MobEffect getYellowTeaEffect() {
		return MobEffects.DAMAGE_RESISTANCE;
	}

	public static MobEffect getCaffeinated() {
		return Util.effect(Modid.FR, "caffeinated", Util.effect(Modid.YH, "sober", MobEffects.DIG_SPEED));
	}
}
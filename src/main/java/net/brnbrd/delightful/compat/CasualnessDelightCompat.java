package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.Util;
import net.minecraft.world.effect.MobEffect;
import org.jetbrains.annotations.Nullable;

public class CasualnessDelightCompat {
	@Nullable
	public static MobEffect getRotten() {
		return Util.effect(Mods.CAD, "rotten");
	}
}
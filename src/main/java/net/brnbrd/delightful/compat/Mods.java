package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.Delightful;
import net.minecraftforge.fml.ModList;
import vectorwing.farmersdelight.FarmersDelight;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class Mods {
	public static String[] names(@NotNull Modid[] modids) {
		return Arrays.stream(modids).map(Modid::get).toArray(String[]::new);
	}

	public static boolean stringLoaded(@NotNull String stringId) {
		return (
			stringId.equals(Delightful.MODID) ||
			stringId.equals(FarmersDelight.MODID) ||
			ModList.get().isLoaded(stringId)
		);
	}

	// Can check for a single mod (strategy ignored) or multiple mods
	public static boolean loaded(Strategy strategy, @NotNull Modid... modids) {
		if (modids.length < 1) return true;
		return switch (strategy) {
			case AND -> { // All must be loaded
				for (Modid mod : modids) if (!mod.loaded()) yield false;
				yield true;
			}
			case OR -> { // Only one must be loaded
				for (Modid mod : modids) if (mod.loaded()) yield true;
				yield false;
			}
		};
	}

	public static boolean loaded(@NotNull Modid... modids) {
		return loaded(Strategy.AND, modids);
	}
}
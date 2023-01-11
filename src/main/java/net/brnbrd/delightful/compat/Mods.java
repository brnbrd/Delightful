package net.brnbrd.delightful.compat;

import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

public class Mods {
	public static final String AN = "ars_nouveau";
	public static final String BOP = "biomesoplenty";
	public static final String BYG = "byg";
	public static final String DD = "deeperdarker";
	public static final String ECO = "ecologics";
	public static final String FA = "forbidden_arcanus";
	public static final String FR = "farmersrespite";
	public static final String FU = "frozenup";
	public static final String FUD = "frozen_delight";
	public static final String FUS = "fusion";
	public static final String LE = "lolenderite";
	public static final String MEKT = "mekanismtools";
	public static final String RC = "rootsclassic";
	public static final String RL = "rottenleather";
	public static final String SO = "simpleores";
	public static final String TF = "twilightforest";
	public static final String WB = "wildberries";

	public static boolean loaded(@NotNull String modid) {
		return ModList.get().isLoaded(modid);
	}

	public static boolean loaded(@NotNull String... modids) {
		for (String mod : modids) {
			if (!loaded(mod)) {
				return false;
			}
		}
		return true;
	}
}

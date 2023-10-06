package net.brnbrd.delightful.compat;

import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.FoodValues;
import java.util.function.Supplier;

public class ArsNouveauCompat {

	public static final String pie = "source_berry_pie";

	public static Supplier<FoodProperties> getPieSlice() {
		return (Mods.loaded(Mods.AN)) ?
			ArsNouveauSuppliers.PIE_SLICE :
			() -> FoodValues.PIE_SLICE;
	}
}

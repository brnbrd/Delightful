package net.brnbrd.delightful.compat;

import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.FoodValues;
import java.util.function.Supplier;

public class ArsNouveauCompat {

	public static final String pie = "source_berry_pie";
	public static final String slice = "source_berry_pie_slice";

	public static Supplier<FoodProperties> getPieSlice() {
		if (Mods.loaded(Mods.AN)) {
			return ArsNouveauSuppliers.PIE_SLICE;
		} else {
			return () -> FoodValues.PIE_SLICE;
		}
	}
}

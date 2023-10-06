package net.brnbrd.delightful.compat;

import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.FoodValues;

import java.util.function.Supplier;

public class UndergardenCompat {

	public static final String pie = "gloomgourd_pie";

	public static Supplier<FoodProperties> getPieSlice() {
		return (Mods.loaded(Mods.UG)) ?
			UndergardenSuppliers.PIE_SLICE :
			() -> FoodValues.PIE_SLICE;
	}
}

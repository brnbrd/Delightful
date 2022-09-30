package net.brdle.delightful.compat;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.fml.ModList;
import vectorwing.farmersdelight.common.registry.ModEffects;
import java.util.function.Supplier;

public class ArsNouveauCompat {

	public static final String modid = "ars_nouveau";
	public static final String pie = "source_berry_pie";
	public static final String slice = "source_berry_pie_slice";

	public static Supplier<FoodProperties> getPieSlice() {
		if (ModList.get().isLoaded(modid)) {
			return ArsNouveauSuppliers.PIE_SLICE;
		} else {
			return () -> vectorwing.farmersdelight.common.FoodValues.PIE_SLICE;
		}
	}

	public static Supplier<MobEffectInstance> getPieEffect() {
		if (ModList.get().isLoaded(modid)) {
			return ArsNouveauSuppliers.MANA_REGEN;
		} else {
			return () -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 300, 1);
		}
	}
}

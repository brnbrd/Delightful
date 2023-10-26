package net.brnbrd.delightful.common.world;

import net.brnbrd.delightful.Delightful;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class DelightfulWildCropGeneration {
	public static ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PATCH_WILD_SALMONBERRIES = configured("patch_wild_salmonberries");
	public static ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PATCH_MINI_MELON = configured("patch_mini_melon");
	public static ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PATCH_CANTALOUPE = configured("patch_cantaloupe");

	public static ResourceKey<PlacedFeature> PATCH_WILD_SALMONBERRIES = placed("patch_wild_salmonberries");
	public static ResourceKey<PlacedFeature> PATCH_MINI_MELON = placed("patch_mini_melon");
	public static ResourceKey<PlacedFeature> PATCH_CANTALOUPE = placed("patch_cantaloupe");

	private static ResourceKey<ConfiguredFeature<?, ?>> configured(String id) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Delightful.MODID, id));
	}

	private static ResourceKey<PlacedFeature> placed(String id) {
		return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Delightful.MODID, id));
	}

	public static void load() {
	}
}
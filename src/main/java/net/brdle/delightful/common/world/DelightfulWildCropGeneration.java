package net.brdle.delightful.common.world;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.data.DelightfulBlockTags;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import vectorwing.farmersdelight.common.registry.ModBiomeFeatures;
import vectorwing.farmersdelight.common.world.WildCropGeneration;
import vectorwing.farmersdelight.common.world.configuration.WildCropConfiguration;
import java.util.List;

public class DelightfulWildCropGeneration extends WildCropGeneration {
	public static Holder<ConfiguredFeature<WildCropConfiguration, ?>> FEATURE_PATCH_WILD_SALMONBERRIES;
	public static Holder<PlacedFeature> PATCH_WILD_SALMONBERRIES;
	public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> FEATURE_PATCH_MINI_MELON;
	public static Holder<PlacedFeature> PATCH_MINI_MELON;
	public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> FEATURE_PATCH_CANTALOUPE;
	public static Holder<PlacedFeature> PATCH_CANTALOUPE;

	private static Holder<PlacedFeature> registerPlacement(ResourceLocation id, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
		return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, id, new PlacedFeature(Holder.hackyErase(feature), List.of(modifiers)));
	}

	public static void registerWildCropGeneration() {
		FEATURE_PATCH_WILD_SALMONBERRIES = register(Util.rl(Delightful.MODID, "patch_wild_salmonberries"),
			ModBiomeFeatures.WILD_CROP.get(), wildCropConfig(DelightfulBlocks.WILD_SALMONBERRIES.get(), Blocks.GRASS, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT)));

		PATCH_WILD_SALMONBERRIES = registerPlacement(Util.rl(Delightful.MODID, "patch_wild_salmonberries"),
			FEATURE_PATCH_WILD_SALMONBERRIES, RarityFilter.onAverageOnceEvery(DelightfulConfig.CHANCE_WILD_SALMONBERRIES.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

		FEATURE_PATCH_MINI_MELON = register(Util.rl(Delightful.MODID, "patch_mini_melon"),
			Feature.RANDOM_PATCH, randomPatchConfig(DelightfulBlocks.MINI_MELON.get(), 64, 4, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT)));

		PATCH_MINI_MELON = registerPlacement(Util.rl(Delightful.MODID, "patch_mini_melon"),
			FEATURE_PATCH_MINI_MELON, RarityFilter.onAverageOnceEvery(DelightfulConfig.CHANCE_MINI_MELON.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

		FEATURE_PATCH_CANTALOUPE = register(Util.rl(Delightful.MODID, "patch_cantaloupe"),
			Feature.RANDOM_PATCH, randomPatchConfig(DelightfulBlocks.CANTALOUPE.get(), 64, 3, BlockPredicate.matchesTag(BLOCK_BELOW, DelightfulBlockTags.CANTALOUPE_SPAWNS)));

		PATCH_CANTALOUPE = registerPlacement(Util.rl(Delightful.MODID, "patch_cantaloupe"),
			FEATURE_PATCH_CANTALOUPE, RarityFilter.onAverageOnceEvery(DelightfulConfig.CHANCE_CANTALOUPE.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	}
}
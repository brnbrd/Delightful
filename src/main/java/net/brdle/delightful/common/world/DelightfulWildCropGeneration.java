package net.brdle.delightful.common.world;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import vectorwing.farmersdelight.common.registry.ModBiomeFeatures;
import vectorwing.farmersdelight.common.world.WildCropGeneration;
import vectorwing.farmersdelight.common.world.configuration.WildCropConfiguration;
import java.util.List;

public class DelightfulWildCropGeneration extends WildCropGeneration {
	public static Holder<ConfiguredFeature<WildCropConfiguration, ?>> FEATURE_PATCH_WILD_SALMONBERRIES;
	public static Holder<PlacedFeature> PATCH_WILD_SALMONBERRIES;
	public static Holder<ConfiguredFeature<WildCropConfiguration, ?>> FEATURE_PATCH_MINI_MELON;
	public static Holder<PlacedFeature> PATCH_MINI_MELON;

	private static Holder<PlacedFeature> registerPlacement(ResourceLocation id, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
		return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, id, new PlacedFeature(Holder.hackyErase(feature), List.of(modifiers)));
	}

	public static void registerWildCropGeneration() {
		FEATURE_PATCH_WILD_SALMONBERRIES = register(new ResourceLocation(Delightful.MODID, "patch_wild_salmonberries"),
			ModBiomeFeatures.WILD_CROP.get(), wildCropConfig(DelightfulBlocks.WILD_SALMONBERRIES.get(), Blocks.GRASS, BlockPredicate.matchesTag(BlockTags.DIRT, BLOCK_BELOW)));

		PATCH_WILD_SALMONBERRIES = registerPlacement(new ResourceLocation(Delightful.MODID, "patch_wild_salmonberries"),
			FEATURE_PATCH_WILD_SALMONBERRIES, RarityFilter.onAverageOnceEvery(DelightfulConfig.CHANCE_WILD_SALMONBERRIES.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

		FEATURE_PATCH_MINI_MELON = register(new ResourceLocation(Delightful.MODID, "patch_mini_melon"),
			ModBiomeFeatures.WILD_CROP.get(), wildCropConfig(DelightfulBlocks.MINI_MELON.get(), Blocks.GRASS, BlockPredicate.matchesTag(BlockTags.DIRT, BLOCK_BELOW)));

		PATCH_MINI_MELON = registerPlacement(new ResourceLocation(Delightful.MODID, "patch_mini_melon"),
			FEATURE_PATCH_MINI_MELON, RarityFilter.onAverageOnceEvery(DelightfulConfig.CHANCE_MINI_MELON.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	}
}
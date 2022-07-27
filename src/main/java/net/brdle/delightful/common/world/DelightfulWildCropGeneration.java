package net.brdle.delightful.common.world;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import vectorwing.farmersdelight.common.world.WildCropGeneration;
import java.util.List;

public class DelightfulWildCropGeneration extends WildCropGeneration {
	public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> FEATURE_PATCH_WILD_SALMONBERRIES;
	public static Holder<PlacedFeature> PATCH_WILD_SALMONBERRIES;

	private static Holder<PlacedFeature> registerPlacement(ResourceLocation id, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
		return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, id, new PlacedFeature(Holder.hackyErase(feature), List.of(modifiers)));
	}

	public static void registerWildCropGeneration() {
		BlockPos BLOCK_BELOW = new BlockPos(0, -1, 0);
		FEATURE_PATCH_WILD_SALMONBERRIES = register(new ResourceLocation(Delightful.MODID, "patch_wild_salmonberries"),
			Feature.RANDOM_PATCH, getWildCropConfiguration(DelightfulBlocks.WILD_SALMONBERRIES.get(), 64, 3, BlockPredicate.matchesBlocks(List.of(Blocks.GRASS_BLOCK, Blocks.DIRT), BLOCK_BELOW)));

		PATCH_WILD_SALMONBERRIES = registerPlacement(new ResourceLocation(Delightful.MODID, "patch_wild_salmonberries"),
			FEATURE_PATCH_WILD_SALMONBERRIES, RarityFilter.onAverageOnceEvery(DelightfulConfig.CHANCE_WILD_SALMONBERRIES.get()), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	}
}
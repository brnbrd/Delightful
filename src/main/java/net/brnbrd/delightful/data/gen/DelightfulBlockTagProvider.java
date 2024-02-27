package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.compat.BYGCompat;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ModTags;
import java.util.concurrent.CompletableFuture;

public class DelightfulBlockTagProvider extends BlockTagsProvider {

	protected DelightfulBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		// Farmer's Delight
		this.tag(ModTags.MINEABLE_WITH_KNIFE)
			.add(DelightfulBlocks.MINI_MELON.get())
			.add(DelightfulBlocks.SLICED_MINI_MELON.get())
			.add(DelightfulBlocks.CANTALOUPE.get())
			.add(DelightfulBlocks.SLICED_CANTALOUPE.get())
			.add(DelightfulBlocks.SALMONBERRY_PIE.get())
			.add(DelightfulBlocks.PUMPKIN_PIE.get())
			.add(DelightfulBlocks.BLUEBERRY_PIE.get())
			.add(DelightfulBlocks.CRIMSON_BERRY_PIE.get())
			.add(DelightfulBlocks.GREEN_APPLE_PIE.get())
			.add(DelightfulBlocks.NIGHTSHADE_BERRY_PIE.get())
			.add(DelightfulBlocks.SOURCE_BERRY_PIE.get())
			.add(DelightfulBlocks.GLOOMGOURD_PIE.get());
		this.tag(ModTags.WILD_CROPS)
			.add(DelightfulBlocks.WILD_SALMONBERRIES.get());

		// Delightful
		this.tag(DelightfulBlockTags.DROPS_STRAW)
			.addOptional(Util.rl("sprout", "sprouts"))
			.addOptional(Util.rl(Mods.BYG, BYGCompat.prairie_grass))
			.addOptional(Util.rl(Mods.BYG, BYGCompat.tall_prairie_grass))
			.addOptional(Util.rl(Mods.BYG, BYGCompat.beach_grass));
		this.tag(DelightfulBlockTags.DROPS_ACORN)
			.add(Blocks.OAK_LEAVES)
			.add(Blocks.DARK_OAK_LEAVES);
		this.tag(DelightfulBlockTags.ADD_ACORN)
			.addOptional(Util.rl("natural_decoration", "oak_acorn"));
		this.tag(DelightfulBlockTags.DROPS_GREEN_TEA_LEAF)
			.addTag(BlockTags.LEAVES);
		this.tag(DelightfulBlockTags.CANTALOUPE_SPAWNS)
			.addTag(BlockTags.SAND);

		// Minecraft
		this.tag(BlockTags.SWORD_EFFICIENT)
			.add(DelightfulBlocks.MINI_MELON.get())
			.add(DelightfulBlocks.SLICED_MINI_MELON.get())
			.add(DelightfulBlocks.CANTALOUPE.get())
			.add(DelightfulBlocks.SLICED_CANTALOUPE.get())
			.add(DelightfulBlocks.SLICED_MELON.get())
			.add(DelightfulBlocks.SLICED_PUMPKIN.get());
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(DelightfulBlocks.BASALT_CABINET.get())
			.add(DelightfulBlocks.QUARTZ_CABINET.get());
		this.tag(BlockTags.MINEABLE_WITH_AXE)
			.add(DelightfulBlocks.MINI_MELON.get())
			.add(DelightfulBlocks.SLICED_MINI_MELON.get())
			.add(DelightfulBlocks.CANTALOUPE.get())
			.add(DelightfulBlocks.SLICED_CANTALOUPE.get())
			.add(DelightfulBlocks.STUFFED_CANTALOUPE_BLOCK.get())
			.add(DelightfulBlocks.SLICED_MELON.get())
			.add(DelightfulBlocks.SLICED_PUMPKIN.get())
			.add(DelightfulBlocks.SLICED_GLOOMGOURD.get())
			.add(DelightfulBlocks.GREEN_APPLE_CRATE.get())
			.add(DelightfulBlocks.JOSHUA_FRUIT_CRATE.get())
			.add(DelightfulBlocks.BAOBAB_FRUIT_CRATE.get())
			.addOptional(Util.rl("fruittrees", "citrus_cabinet"))
			.addOptional(Util.rl("fruittrees", "cherry_cabinet"));
		this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
			.add(DelightfulBlocks.MATCHA_ICE_CREAM_BLOCK.get())
			.add(DelightfulBlocks.SALMONBERRY_ICE_CREAM_BLOCK.get());
		this.tag(BlockTags.MINEABLE_WITH_HOE)
			.add(DelightfulBlocks.ACORN_SACK.get())
			.add(DelightfulBlocks.SALMONBERRY_SACK.get())
			.add(DelightfulBlocks.BLUEBERRY_SACK.get())
			.add(DelightfulBlocks.CRIMSON_BERRY_SACK.get())
			.add(DelightfulBlocks.NIGHTSHADE_BERRY_SACK.get());
		this.tag(BlockTags.SMALL_FLOWERS)
			.add(DelightfulBlocks.WILD_SALMONBERRIES.get());

		// Serene Seasons
		this.tag(DelightfulBlockTags.SUMMER_CROPS)
			.add(DelightfulBlocks.SALMONBERRY_BUSH.get());
	}
}

package net.brdle.delightful.data.gen;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.compat.BYGCompat;
import net.brdle.delightful.data.DelightfulBlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ModTags;

public class DelightfulBlockTagProvider extends BlockTagsProvider {

	protected DelightfulBlockTagProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
		super(pGenerator, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {
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
			.add(DelightfulBlocks.SOURCE_BERRY_PIE.get());

		// Delightful
		this.tag(DelightfulBlockTags.DROPS_STRAW)
			.addOptional(Util.rl(BYGCompat.modid, "prairie_grass"))
			.addOptional(Util.rl(BYGCompat.modid, "tall_prairie_grass"))
			.addOptional(Util.rl(BYGCompat.modid, "beach_grass"));
		this.tag(DelightfulBlockTags.DROPS_ACORN)
			.add(Blocks.OAK_LEAVES)
			.add(Blocks.DARK_OAK_LEAVES);
		this.tag(DelightfulBlockTags.DROPS_GREEN_TEA_LEAF)
			.addTag(BlockTags.LEAVES);
		this.tag(DelightfulBlockTags.CANTALOUPE_SPAWNS)
			.add(Blocks.GRASS_BLOCK)
			.addTag(BlockTags.DIRT)
			.addTag(BlockTags.SAND);

		// Minecraft
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(DelightfulBlocks.BASALT_CABINET.get())
			.add(DelightfulBlocks.QUARTZ_CABINET.get());
		this.tag(BlockTags.MINEABLE_WITH_AXE)
			.add(DelightfulBlocks.MINI_MELON.get())
			.add(DelightfulBlocks.SLICED_MINI_MELON.get())
			.add(DelightfulBlocks.SLICED_MELON.get())
			.add(DelightfulBlocks.SLICED_PUMPKIN.get())
			.add(DelightfulBlocks.CANTALOUPE.get())
			.add(DelightfulBlocks.SLICED_CANTALOUPE.get())
			.add(DelightfulBlocks.SALMONBERRY_SACK.get())
			.add(DelightfulBlocks.ACORN_SACK.get());
		this.tag(BlockTags.MINEABLE_WITH_HOE)
			.add(DelightfulBlocks.SALMONBERRY_SACK.get())
			.add(DelightfulBlocks.ACORN_SACK.get());
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public String getName() {
		return Delightful.MODID;
	}
}

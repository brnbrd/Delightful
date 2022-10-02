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
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class DelightfulBlockTagProvider extends BlockTagsProvider {

	protected DelightfulBlockTagProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
		super(pGenerator, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {
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
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public String getName() {
		return Delightful.MODID;
	}
}

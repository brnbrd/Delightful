package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.compat.BYGCompat;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulBlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
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
			.addOptional(Util.rl("fruittrees", "citrus_cabinet"))
			.addOptional(Util.rl("fruittrees", "cherry_cabinet"));
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public @NotNull String getName() {
		return Delightful.MODID;
	}
}

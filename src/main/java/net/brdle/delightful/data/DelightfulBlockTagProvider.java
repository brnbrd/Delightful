package net.brdle.delightful.data;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.tag.DelightfulBlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class DelightfulBlockTagProvider extends BlockTagsProvider {

	protected DelightfulBlockTagProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
		super(pGenerator, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {

		// Farmer's Delight
		this.tag(DelightfulBlockTags.CABINETS_WOODEN)
			.add(ModBlocks.ACACIA_CABINET.get())
			.add(ModBlocks.CRIMSON_CABINET.get())
			.add(ModBlocks.BIRCH_CABINET.get())
			.add(ModBlocks.DARK_OAK_CABINET.get())
			.add(ModBlocks.JUNGLE_CABINET.get())
			.add(ModBlocks.OAK_CABINET.get())
			.add(ModBlocks.SPRUCE_CABINET.get())
			.add(ModBlocks.WARPED_CABINET.get());
		this.tag(DelightfulBlockTags.CABINETS_STONE)
			.add(DelightfulBlocks.BASALT_CABINET.get())
			.add(DelightfulBlocks.QUARTZ_CABINET.get());
		this.tag(DelightfulBlockTags.CABINETS).addTag(DelightfulBlockTags.CABINETS_WOODEN).addTag(DelightfulBlockTags.CABINETS_STONE);

		// Forge

		// Minecraft
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(DelightfulBlockTags.CABINETS_STONE);
		this.tag(BlockTags.MINEABLE_WITH_AXE).add(DelightfulBlocks.MINI_MELON.get());
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public String getName() {
		return Delightful.MODID;
	}
}

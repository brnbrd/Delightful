package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.data.tags.DelightfulBiomeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class DelightfulBiomeTagProvider extends BiomeTagsProvider {

	protected DelightfulBiomeTagProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
		super(pGenerator, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(DelightfulBiomeTags.SALMONBERRY_SPAWNS)
			.addTag(BiomeTags.IS_FOREST);
		this.tag(DelightfulBiomeTags.MINI_MELON_SPAWNS)
			.addTag(Tags.Biomes.IS_PLAINS);
		this.tag(DelightfulBiomeTags.CANTALOUPE_SPAWNS)
			.addTag(BiomeTags.IS_BEACH);
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public String getName() {
		return Delightful.MODID;
	}
}

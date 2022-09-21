package net.brdle.delightful.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class DelightfulBlockTags {

	private static TagKey<Block> bind(String modid, String name) {
		return BlockTags.create(new ResourceLocation(modid, name));
	}
}

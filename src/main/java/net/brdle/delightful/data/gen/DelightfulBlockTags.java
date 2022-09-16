package net.brdle.delightful.data.gen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.FarmersDelight;

public class DelightfulBlockTags {

	// Delightful

	// FarmersDelight
	public static final TagKey<Block> CABINETS = bind(FarmersDelight.MODID, "cabinets");
	public static final TagKey<Block> CABINETS_WOODEN = bind(FarmersDelight.MODID, "cabinets/wooden");
	public static final TagKey<Block> CABINETS_STONE = bind(FarmersDelight.MODID, "cabinets/stone");

	// Forge


	// Minecraft

	private static TagKey<Block> bind(String modid, String name) {
		return BlockTags.create(new ResourceLocation(modid, name));
	}
}

package net.brnbrd.delightful.data.tags;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class DelightfulBlockTags {

	// Delightful
	public static final TagKey<Block> COMPAT_PIES = bind(Delightful.MODID, "compat_pies");
	public static final TagKey<Block> DROPS_STRAW = bind(Delightful.MODID, "drops_straw");
	public static final TagKey<Block> DROPS_ACORN = bind(Delightful.MODID, "drops_acorn");
	public static final TagKey<Block> ADD_ACORN = bind(Delightful.MODID, "add_acorn");
	public static final TagKey<Block> DROPS_GREEN_TEA_LEAF = bind(Delightful.MODID, "drops_green_tea_leaf");
	public static final TagKey<Block> CANTALOUPE_SPAWNS = bind(Delightful.MODID, "cantaloupe_spawns");

	// Serene Seasons
	public static final TagKey<Block> WINTER_CROPS = bind("sereneseasons", "winter_crops");
	public static final TagKey<Block> SUMMER_CROPS = bind("sereneseasons", "summer_crops");
	public static final TagKey<Block> AUTUMN_CROPS = bind("sereneseasons", "autumn_crops");
	public static final TagKey<Block> SPRING_CROPS = bind("sereneseasons", "spring_crops");

	private static TagKey<Block> bind(String modid, String name) {
		return BlockTags.create(Util.rl(modid, name));
	}
}

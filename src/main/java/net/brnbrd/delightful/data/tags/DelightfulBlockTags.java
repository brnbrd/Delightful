package net.brnbrd.delightful.data.tags;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Modid;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class DelightfulBlockTags {
	// Delightful
	public static final TagKey<Block> DROPS_STRAW = bind(Delightful.MODID, "drops_straw");
	public static final TagKey<Block> DROPS_ACORN = bind(Delightful.MODID, "drops_acorn");
	public static final TagKey<Block> ADD_ACORN = bind(Delightful.MODID, "add_acorn");
	public static final TagKey<Block> DROPS_GREEN_TEA_LEAF = bind(Delightful.MODID, "drops_green_tea_leaf");
	public static final TagKey<Block> CANTALOUPE_SPAWNS = bind(Delightful.MODID, "cantaloupe_spawns");

	// Forge
	public static final TagKey<Block> STORAGE_BLOCKS_SALMONBERRIES = forge("storage_blocks/salmonberries");
	public static final TagKey<Block> STORAGE_BLOCKS_ACORN = forge("storage_blocks/acorn");
	public static final TagKey<Block> STORAGE_BLOCKS_BLUEBERRIES = forge("storage_blocks/blueberries");
	public static final TagKey<Block> STORAGE_BLOCKS_MENDOSTEEN = forge("storage_blocks/mendosteen");
	public static final TagKey<Block> STORAGE_BLOCKS_BASTION_FRUIT = forge("storage_blocks/bastion_fruit");
	public static final TagKey<Block> STORAGE_BLOCKS_FROSTAYA = forge("storage_blocks/frostaya");
	public static final TagKey<Block> STORAGE_BLOCKS_BOMBEGRANATE = forge("storage_blocks/bombegranate");
	public static final TagKey<Block> STORAGE_BLOCKS_GREEN_APPLE = forge("storage_blocks/green_apple");
	public static final TagKey<Block> STORAGE_BLOCKS_YUCCA_FRUIT = forge("storage_blocks/yucca_fruit");
	public static final TagKey<Block> STORAGE_BLOCKS_BAOBAB_FRUIT = forge("storage_blocks/baobab_fruit");

	private static TagKey<Block> bind(@NotNull String modid, @NotNull String name) {
		return BlockTags.create(Util.rl(modid, name));
	}

	private static TagKey<Block> forge(@NotNull String name) {
		return bind(Modid.LOADER.get(), name);
	}
}
package net.brnbrd.delightful.data;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class DelightfulBiomeTags {

	public static final TagKey<Biome> SALMONBERRY_SPAWNS = create("salmonberry_spawns");
	public static final TagKey<Biome> MINI_MELON_SPAWNS = create("mini_melon_spawns");
	public static final TagKey<Biome> CANTALOUPE_SPAWNS = create("cantaloupe_spawns");


	private static TagKey<Biome> create(String name) {
		return TagKey.create(Registry.BIOME_REGISTRY, Util.rl(Delightful.MODID, name));
	}
}
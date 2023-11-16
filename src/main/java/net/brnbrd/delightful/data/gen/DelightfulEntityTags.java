package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class DelightfulEntityTags {

	public static final TagKey<EntityType<?>> FATTY_ANIMALS = create("fatty_animals");
	public static final TagKey<EntityType<?>> DROPS_ACORN = create("drops_acorn");
	public static final TagKey<EntityType<?>> DROPS_RAW_GOAT = create("drops_raw_goat");

	// Aether
	public static final TagKey<EntityType<?>> NO_AMBROSIUM_DROPS  = Util.et(Mods.AE, "no_ambrosium_drops");
	public static final TagKey<EntityType<?>> UNLAUNCHABLE  = Util.et(Mods.AE, "unlaunchable");

	private static TagKey<EntityType<?>> create(String pName) {
		return Util.et(Delightful.MODID, pName);
	}
}

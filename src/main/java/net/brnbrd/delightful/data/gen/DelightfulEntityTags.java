package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class DelightfulEntityTags {

	public static final TagKey<EntityType<?>> FATTY_ANIMALS = create("fatty_animals");
	public static final TagKey<EntityType<?>> DROPS_ACORN = create("drops_acorn");
	public static final TagKey<EntityType<?>> DROPS_RAW_GOAT = create("drops_raw_goat");

	private static TagKey<EntityType<?>> create(String pName) {
		return TagKey.create(Registries.ENTITY_TYPE, Util.rl(Delightful.MODID, pName));
	}
}

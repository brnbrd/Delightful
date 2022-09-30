package net.brdle.delightful.data.gen;

import net.brdle.delightful.Delightful;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class DelightfulEntityTags {

	public static final TagKey<EntityType<?>> FATTY_ANIMALS = create("fatty_animals");
	public static final TagKey<EntityType<?>> DROPS_ACORN = create("drops_acorn");
	public static final TagKey<EntityType<?>> DROPS_RAW_GOAT = create("drops_raw_goat");

	private static TagKey<EntityType<?>> create(String pName) {
		return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(Delightful.MODID, pName));
	}
}

package net.brdle.delightful.data;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.tag.DelightfulEntityTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DelightfulEntityTagProvider extends EntityTypeTagsProvider {
	public DelightfulEntityTagProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(DelightfulEntityTags.FATTY_ANIMALS)
			.add(EntityType.CAT)
			.add(EntityType.WOLF)
			.add(EntityType.COD)
			.add(EntityType.SALMON)
			.add(EntityType.COW)
			.add(EntityType.DOLPHIN)
			.add(EntityType.DONKEY)
			.add(EntityType.FOX)
			.add(EntityType.GLOW_SQUID)
			.add(EntityType.SQUID)
			.add(EntityType.GOAT)
			.add(EntityType.HOGLIN)
			.add(EntityType.HORSE)
			.add(EntityType.LLAMA)
			.add(EntityType.MOOSHROOM)
			.add(EntityType.MULE)
			.add(EntityType.OCELOT)
			.add(EntityType.PANDA)
			.add(EntityType.PIG)
			.add(EntityType.PIGLIN)
			.add(EntityType.PIGLIN_BRUTE)
			.add(EntityType.POLAR_BEAR)
			.add(EntityType.PUFFERFISH)
			.add(EntityType.RABBIT)
			.add(EntityType.SHEEP)
			.add(EntityType.TROPICAL_FISH)
			.add(EntityType.TURTLE)
			.addOptional(mod("ecologics", "camel"))
			.addOptional(mod("ecologics", "penguin"))
			.addOptional(mod("ecologics", "squirrel"))
			.addOptional(mod("goodall", "dumbo_octopus"))
			.addOptional(mod("goodall", "manatee"))
			.addOptional(mod("goodall", "red_deer"))
			.addOptional(mod("goodall", "seal"))
			.addOptional(mod("goodall", "white_tailed_deer"))
			.addOptional(mod("neapolitan", "chimpanzee"))
			.addOptional(mod("naturalist", "bear"))
			.addOptional(mod("naturalist", "boar"))
			.addOptional(mod("naturalist", "deer"))
			.addOptional(mod("naturalist", "elephant"))
			.addOptional(mod("naturalist", "giraffe"))
			.addOptional(mod("naturalist", "hippo"))
			.addOptional(mod("naturalist", "lion"))
			.addOptional(mod("naturalist", "rhino"))
			.addOptional(mod("naturalist", "zebra"))
			.addOptional(mod("duckling", "duck"))
			.addOptional(mod("duckling", "quackling"))
			.addOptional(mod("buzzier_bees", "moobloom"))
			.addOptional(mod("buzzier_bees", "grizzly_bear"))
			.addOptional(mod("fishofthieves", "ancientscale"))
			.addOptional(mod("fishofthieves", "battlegill"))
			.addOptional(mod("fishofthieves", "devilfish"))
			.addOptional(mod("fishofthieves", "islehopper"))
			.addOptional(mod("fishofthieves", "plentifin"))
			.addOptional(mod("fishofthieves", "pondie"))
			.addOptional(mod("fishofthieves", "splashtail"))
			.addOptional(mod("fishofthieves", "stormfish"))
			.addOptional(mod("fishofthieves", "wildsplash"))
			.addOptional(mod("fishofthieves", "wrecker"))
			.addOptional(mod("ars_nouveau", "starbuncle"))
			.addOptional(mod("aqcaracal", "caracal"));
		this.tag(DelightfulEntityTags.DROPS_ACORN).addOptional(mod("ecologics", "squirrel"));
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public String getName() {
		return Delightful.MODID;
	}

	private ResourceLocation mod(String modid, String name) {
		return new ResourceLocation(modid, name);
	}
}

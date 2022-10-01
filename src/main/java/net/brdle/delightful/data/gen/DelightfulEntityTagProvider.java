package net.brdle.delightful.data.gen;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
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
			.addOptional(Util.rl("ecologics", "camel"))
			.addOptional(Util.rl("ecologics", "penguin"))
			.addOptional(Util.rl("ecologics", "squirrel"))
			.addOptional(Util.rl("goodall", "dumbo_octopus"))
			.addOptional(Util.rl("goodall", "manatee"))
			.addOptional(Util.rl("goodall", "red_deer"))
			.addOptional(Util.rl("goodall", "seal"))
			.addOptional(Util.rl("goodall", "white_tailed_deer"))
			.addOptional(Util.rl("neapolitan", "chimpanzee"))
			.addOptional(Util.rl("naturalist", "bear"))
			.addOptional(Util.rl("naturalist", "boar"))
			.addOptional(Util.rl("naturalist", "deer"))
			.addOptional(Util.rl("naturalist", "elephant"))
			.addOptional(Util.rl("naturalist", "giraffe"))
			.addOptional(Util.rl("naturalist", "hippo"))
			.addOptional(Util.rl("naturalist", "lion"))
			.addOptional(Util.rl("naturalist", "rhino"))
			.addOptional(Util.rl("naturalist", "zebra"))
			.addOptional(Util.rl("duckling", "duck"))
			.addOptional(Util.rl("duckling", "quackling"))
			.addOptional(Util.rl("buzzier_bees", "moobloom"))
			.addOptional(Util.rl("buzzier_bees", "grizzly_bear"))
			.addOptional(Util.rl("fishofthieves", "ancientscale"))
			.addOptional(Util.rl("fishofthieves", "battlegill"))
			.addOptional(Util.rl("fishofthieves", "devilfish"))
			.addOptional(Util.rl("fishofthieves", "islehopper"))
			.addOptional(Util.rl("fishofthieves", "plentifin"))
			.addOptional(Util.rl("fishofthieves", "pondie"))
			.addOptional(Util.rl("fishofthieves", "splashtail"))
			.addOptional(Util.rl("fishofthieves", "stormfish"))
			.addOptional(Util.rl("fishofthieves", "wildsplash"))
			.addOptional(Util.rl("fishofthieves", "wrecker"))
			.addOptional(Util.rl("ars_nouveau", "starbuncle"))
			.addOptional(Util.rl("aqcaracal", "caracal"));
		this.tag(DelightfulEntityTags.DROPS_ACORN).addOptional(Util.rl("ecologics", "squirrel"));
		this.tag(DelightfulEntityTags.DROPS_RAW_GOAT).add(EntityType.GOAT);
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public String getName() {
		return Delightful.MODID;
	}
}

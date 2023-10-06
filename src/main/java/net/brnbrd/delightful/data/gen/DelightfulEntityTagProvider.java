package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

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
			.addOptional(Util.rl(Mods.ECO, "camel"))
			.addOptional(Util.rl(Mods.ECO, "penguin"))
			.addOptional(Util.rl(Mods.ECO, "squirrel"))
			.addOptional(Util.rl(Mods.ECO, "coconut_crab"))
			.addOptional(Util.rl(Mods.AN, "starbuncle"))
			.addOptional(Util.rl("aqcaracal", "caracal"))
			.addOptional(Util.rl("babyfat", "ranchu"))
			.addOptional(Util.rl("bettas", "betta_fish"))
			.addOptional(Util.rl("buzzier_bees", "moobloom"))
			.addOptional(Util.rl("buzzier_bees", "grizzly_bear"))
			.addOptional(Util.rl("crittersandcompanions", "dumbo_octopus"))
			.addOptional(Util.rl("crittersandcompanions", "ferret"))
			.addOptional(Util.rl("crittersandcompanions", "koi_fish"))
			.addOptional(Util.rl("crittersandcompanions", "otter"))
			.addOptional(Util.rl("crittersandcompanions", "red_panda"))
			.addOptional(Util.rl("duckling", "duck"))
			.addOptional(Util.rl("duckling", "quackling"))
			.addOptional(Util.rl("environmental", "deer"))
			.addOptional(Util.rl("environmental", "duck"))
			.addOptional(Util.rl("environmental", "fennec_fox"))
			.addOptional(Util.rl("environmental", "koi"))
			.addOptional(Util.rl("environmental", "slabfish"))
			.addOptional(Util.rl("environmental", "tapir"))
			.addOptional(Util.rl("environmental", "yak"))
			.addOptional(Util.rl("friendsandfoes", "moobloom"))
			.addOptional(Util.rl("frozenup", "chilloo"))
			.addOptional(Util.rl("frozenup", "penguin"))
			.addOptional(Util.rl("frozenup", "reindeer"))
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
			.addOptional(Util.rl("goodall", "deer"))
			.addOptional(Util.rl("goodall", "dumbo_octopus"))
			.addOptional(Util.rl("goodall", "fennec_fox"))
			.addOptional(Util.rl("goodall", "grizzly_bear"))
			.addOptional(Util.rl("goodall", "manatee"))
			.addOptional(Util.rl("goodall", "red_deer"))
			.addOptional(Util.rl("goodall", "seal"))
			.addOptional(Util.rl("goodall", "white_tailed_deer"))
			.addOptional(Util.rl("goodall", "rhino"))
			.addOptional(Util.rl("goodall", "river_turtle"))
			.addOptional(Util.rl("goodall", "tortoise"))
			.addOptional(Util.rl("hedgehog", "hedgehog"))
			.addOptional(Util.rl("naturalist", "bear"))
			.addOptional(Util.rl("naturalist", "boar"))
			.addOptional(Util.rl("naturalist", "deer"))
			.addOptional(Util.rl("naturalist", "elephant"))
			.addOptional(Util.rl("naturalist", "giraffe"))
			.addOptional(Util.rl("naturalist", "hippo"))
			.addOptional(Util.rl("naturalist", "lion"))
			.addOptional(Util.rl("naturalist", "rhino"))
			.addOptional(Util.rl("naturalist", "zebra"))
			.addOptional(Util.rl("neapolitan", "chimpanzee"))
			.addOptional(Util.rl("quark", "crab"))
			.addOptional(Util.rl("quark", "foxhound"))
			.addOptional(Util.rl("quark", "shiba"))
			.addOptional(Util.rl("snowpig", "snow_pig"))
			.addOptional(Util.rl("sprout", "elephant"))
			.addOptional(Util.rl("wandering_trapper", "trapper_dog"));
		this.tag(DelightfulEntityTags.DROPS_ACORN).addOptional(Util.rl(Mods.ECO, "squirrel"));
		this.tag(DelightfulEntityTags.DROPS_RAW_GOAT).add(EntityType.GOAT);
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public @NotNull String getName() {
		return Delightful.MODID;
	}
}

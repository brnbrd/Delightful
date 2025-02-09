package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulEntityTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.tag.ModTags;
import java.util.concurrent.CompletableFuture;

public class DelightfulEntityTagProvider extends EntityTypeTagsProvider {
	public DelightfulEntityTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper existingFileHelper) {
		super(output, provider, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		// Farmer's Delight
		this.tag(ModTags.HORSE_FEED_TEMPTED)
				.addOptional(Util.rl(Mods.NA, "zebra"));
		this.tag(ModTags.HORSE_FEED_USERS)
				.addOptional(Util.rl(Mods.NA, "zebra"));

		// Delightful
		this.tag(DelightfulEntityTags.FATTY_ANIMALS)
				.add(EntityType.COD)
				.add(EntityType.SALMON)
				.add(EntityType.TROPICAL_FISH)
				.add(EntityType.PUFFERFISH)
				.add(EntityType.TURTLE)
				.add(EntityType.GUARDIAN)
				.add(EntityType.ELDER_GUARDIAN)
				.add(EntityType.SQUID)
				.add(EntityType.GLOW_SQUID)
				.add(EntityType.DOLPHIN)
				.add(EntityType.OCELOT)
				.add(EntityType.CAT)
				.add(EntityType.HORSE)
				.add(EntityType.DONKEY)
				.add(EntityType.MULE)
				.add(EntityType.LLAMA)
				.add(EntityType.TRADER_LLAMA)
				.add(EntityType.PIG)
				.add(EntityType.PIGLIN)
				.add(EntityType.PIGLIN_BRUTE)
				.add(EntityType.HOGLIN)
				.add(EntityType.COW)
				.add(EntityType.MOOSHROOM)
				.add(EntityType.SHEEP)
				.add(EntityType.GOAT)
				.add(EntityType.PANDA)
				.add(EntityType.POLAR_BEAR)
				.add(EntityType.WOLF)
				.add(EntityType.FOX)
				.add(EntityType.CAMEL)
				.add(EntityType.RABBIT)
				.add(EntityType.FROG)
				.add(EntityType.SNIFFER)
				.addOptional(Util.rl(Mods.Q, "crab"))
				.addOptional(Util.rl(Mods.Q, "foxhound"))
				.addOptional(Util.rl(Mods.Q, "shiba"))
				.addOptional(Util.rl(Mods.N, "chimpanzee"))
				.addOptional(Util.rl(Mods.BB, "moobloom"))
				.addOptional(Util.rl(Mods.BB, "grizzly_bear"))
				.addOptional(Util.rl(Mods.ENV, "deer"))
				.addOptional(Util.rl(Mods.ENV, "duck"))
				.addOptional(Util.rl(Mods.ENV, "fennec_fox"))
				.addOptional(Util.rl(Mods.ENV, "koi"))
				.addOptional(Util.rl(Mods.ENV, "slabfish"))
				.addOptional(Util.rl(Mods.ENV, "tapir"))
				.addOptional(Util.rl(Mods.ENV, "yak"))
				.addOptional(Util.rl("autumnity", "turkey"))
				.addOptional(Util.rl(Mods.UA, "thrasher"))
				.addOptional(Util.rl(Mods.UA, "great_thrasher"))
				.addOptional(Util.rl(Mods.UA, "goose"))
				.addOptional(Util.rl(Mods.UA, "lionfish"))
				.addOptional(Util.rl(Mods.UA, "perch"))
				.addOptional(Util.rl(Mods.UA, "pike"))
				.addOptional(Util.rl(Mods.CR, "chieftain_crab"))
				.addOptional(Util.rl(Mods.CR, "platinum_bass"))
				.addOptional(Util.rl(Mods.NA, "alligator"))
				.addOptional(Util.rl(Mods.NA, "bass"))
				.addOptional(Util.rl(Mods.NA, "bear"))
				.addOptional(Util.rl(Mods.NA, "boar"))
				.addOptional(Util.rl(Mods.NA, "catfish"))
				.addOptional(Util.rl(Mods.NA, "deer"))
				.addOptional(Util.rl(Mods.NA, "duck"))
				.addOptional(Util.rl(Mods.NA, "elephant"))
				.addOptional(Util.rl(Mods.NA, "giraffe"))
				.addOptional(Util.rl(Mods.NA, "hippo"))
				.addOptional(Util.rl(Mods.NA, "lion"))
				.addOptional(Util.rl(Mods.NA, "rhino"))
				.addOptional(Util.rl(Mods.NA, "tortoise"))
				.addOptional(Util.rl(Mods.NA, "zebra"))
				.addOptional(Util.rl(Mods.ECO, "penguin"))
				.addOptional(Util.rl(Mods.ECO, "squirrel"))
				.addOptional(Util.rl(Mods.ECO, "coconut_crab"))
				.addOptional(Util.rl(Mods.AN, "starbuncle"))
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
				.addOptional(Util.rl("crittersandcompanions", "dumbo_octopus"))
				.addOptional(Util.rl("crittersandcompanions", "ferret"))
				.addOptional(Util.rl("crittersandcompanions", "koi_fish"))
				.addOptional(Util.rl("crittersandcompanions", "otter"))
				.addOptional(Util.rl("crittersandcompanions", "red_panda"))
				.addOptional(Util.rl("friendsandfoes", "moobloom"))
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
				.addOptional(Util.rl(Mods.FU, "chilloo"))
				.addOptional(Util.rl(Mods.FU, "penguin"))
				.addOptional(Util.rl(Mods.FU, "reindeer"))
				.addOptional(Util.rl(Mods.SM, "lanternfish"))
				.addOptional(Util.rl(Mods.SM, "piranha"))
				.addOptional(Util.rl(Mods.SM, "tortoise"))
				.addOptional(Util.rl("biomemakeover", "glowfish"))
				.addOptional(Util.rl("biomemakeover", "helmit_crab"))
				.addOptional(Util.rl("biomemakeover", "toad"))
				.addOptional(Util.rl("duckling", "duck"))
				.addOptional(Util.rl("duckling", "quackling"))
				.addOptional(Util.rl(Mods.HAB, "pooka"))
				.addOptional(Util.rl("aqcaracal", "caracal"))
				.addOptional(Util.rl("babyfat", "ranchu"))
				.addOptional(Util.rl("bettas", "betta_fish"))
				.addOptional(Util.rl("farmlife", "domestic_tribull"))
				.addOptional(Util.rl("goated", "geep"))
				.addOptional(Util.rl("hedgehog", "hedgehog"))
				.addOptional(Util.rl("rats", "rat"))
				.addOptional(Util.rl("snowpig", "snow_pig"))
				.addOptional(Util.rl("sprout", "elephant"))
				.addOptional(Util.rl("wandering_trapper", "trapper_dog"));
		this.tag(DelightfulEntityTags.DROPS_ACORN).addOptional(Util.rl(Mods.ECO, "squirrel"));
		this.tag(DelightfulEntityTags.DROPS_CRAB_LEGS).addOptional(Util.rl(Mods.ECO, "coconut_crab"));
		this.tag(DelightfulEntityTags.DROPS_RAW_GOAT).add(EntityType.GOAT);
		this.tag(DelightfulEntityTags.DROPS_HAM).addOptional(Util.rl(Mods.NA, "boar"));
	}
}
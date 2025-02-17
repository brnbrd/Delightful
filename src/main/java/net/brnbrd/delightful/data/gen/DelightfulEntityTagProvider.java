package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Modid;
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
				.addOptional(Modid.NA.rl("zebra"));
		this.tag(ModTags.HORSE_FEED_USERS)
				.addOptional(Modid.NA.rl("zebra"));

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
				.addOptional(Modid.Q.rl("crab"))
				.addOptional(Modid.Q.rl("foxhound"))
				.addOptional(Modid.Q.rl("shiba"))
				.addOptional(Modid.N.rl("chimpanzee"))
				.addOptional(Modid.BB.rl("moobloom"))
				.addOptional(Modid.BB.rl("grizzly_bear"))
				.addOptional(Modid.ENV.rl("deer"))
				.addOptional(Modid.ENV.rl("duck"))
				.addOptional(Modid.ENV.rl("fennec_fox"))
				.addOptional(Modid.ENV.rl("koi"))
				.addOptional(Modid.ENV.rl("slabfish"))
				.addOptional(Modid.ENV.rl("tapir"))
				.addOptional(Modid.ENV.rl("yak"))
				.addOptional(Util.rl("autumnity", "turkey"))
				.addOptional(Modid.UA.rl("thrasher"))
				.addOptional(Modid.UA.rl("great_thrasher"))
				.addOptional(Modid.UA.rl("goose"))
				.addOptional(Modid.UA.rl("lionfish"))
				.addOptional(Modid.UA.rl("perch"))
				.addOptional(Modid.UA.rl("pike"))
				.addOptional(Modid.CR.rl("chieftain_crab"))
				.addOptional(Modid.CR.rl("platinum_bass"))
				.addOptional(Modid.NA.rl("alligator"))
				.addOptional(Modid.NA.rl("bass"))
				.addOptional(Modid.NA.rl("bear"))
				.addOptional(Modid.NA.rl("boar"))
				.addOptional(Modid.NA.rl("catfish"))
				.addOptional(Modid.NA.rl("deer"))
				.addOptional(Modid.NA.rl("duck"))
				.addOptional(Modid.NA.rl("elephant"))
				.addOptional(Modid.NA.rl("giraffe"))
				.addOptional(Modid.NA.rl("hippo"))
				.addOptional(Modid.NA.rl("lion"))
				.addOptional(Modid.NA.rl("rhino"))
				.addOptional(Modid.NA.rl("tortoise"))
				.addOptional(Modid.NA.rl("zebra"))
				.addOptional(Modid.ECO.rl("penguin"))
				.addOptional(Modid.ECO.rl("squirrel"))
				.addOptional(Modid.ECO.rl("coconut_crab"))
				.addOptional(Modid.AN.rl("starbuncle"))
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
				.addOptional(Modid.FU.rl("chilloo"))
				.addOptional(Modid.FU.rl("penguin"))
				.addOptional(Modid.FU.rl("reindeer"))
				.addOptional(Modid.SM.rl("lanternfish"))
				.addOptional(Modid.SM.rl("piranha"))
				.addOptional(Modid.SM.rl("tortoise"))
				.addOptional(Modid.HAB.rl("pooka"))
				.addOptional(Modid.GO.rl("geep"))
				.addOptional(Util.rl("biomemakeover", "glowfish"))
				.addOptional(Util.rl("biomemakeover", "helmit_crab"))
				.addOptional(Util.rl("biomemakeover", "toad"))
				.addOptional(Util.rl("duckling", "duck"))
				.addOptional(Util.rl("duckling", "quackling"))
				.addOptional(Util.rl("aqcaracal", "caracal"))
				.addOptional(Util.rl("babyfat", "ranchu"))
				.addOptional(Util.rl("bettas", "betta_fish"))
				.addOptional(Util.rl("farmlife", "domestic_tribull"))
				.addOptional(Util.rl("hedgehog", "hedgehog"))
				.addOptional(Util.rl("rats", "rat"))
				.addOptional(Util.rl("snowpig", "snow_pig"))
				.addOptional(Util.rl("sprout", "elephant"))
				.addOptional(Util.rl("wandering_trapper", "trapper_dog"));
		this.tag(DelightfulEntityTags.DROPS_ACORN).addOptional(Modid.ECO.rl("squirrel"));
		this.tag(DelightfulEntityTags.DROPS_CRAB_LEGS).addOptional(Modid.ECO.rl("coconut_crab"));
		this.tag(DelightfulEntityTags.DROPS_RAW_GOAT).add(EntityType.GOAT);
		this.tag(DelightfulEntityTags.DROPS_HAM).addOptional(Modid.NA.rl("boar"));
	}
}
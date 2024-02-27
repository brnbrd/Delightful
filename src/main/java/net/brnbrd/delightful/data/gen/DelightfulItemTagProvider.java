package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.DelightfulKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.common.item.knife.compat.additionaladditions.GildedNetheriteKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.additionaladditions.RoseGoldKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.deeperdarker.WardenKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.lolenderite.EnderiteKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.undergarden.ForgottenKnifeItem;
import net.brnbrd.delightful.compat.ArsNouveauCompat;
import net.brnbrd.delightful.compat.BYGCompat;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.compat.UndergardenCompat;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class DelightfulItemTagProvider extends ItemTagsProvider {
	public DelightfulItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, provider, blockTagProvider, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {

		// Delightful
		tag(DelightfulItemTags.COMPAT_PIES)
			.add(Items.PUMPKIN_PIE)
			.addOptional(Util.rl(Mods.AN, ArsNouveauCompat.pie))
			.addOptional(Util.rl(Mods.UG, UndergardenCompat.pie))
			.addOptional(Util.rl(Mods.BYG, BYGCompat.blueberry_pie))
			.addOptional(Util.rl(Mods.BYG, BYGCompat.crimson_berry_pie))
			.addOptional(Util.rl(Mods.BYG, BYGCompat.green_apple_pie))
			.addOptional(Util.rl(Mods.BYG, BYGCompat.nightshade_berry_pie));
		tag(DelightfulItemTags.FIRE_KNIVES)
			.add(Knives.FIERY.get())
			.add(Knives.KIWANO.get())
			.add(Knives.BLAZING.get());

		// Farmer's Delight
		tag(ModTags.WOODEN_CABINETS)
			.add(ModItems.MANGROVE_CABINET.get())
			.addOptional(Util.rl("windswept", "holly_cabinet"))
			.addOptional(Util.rl("windswept", "chestnut_cabinet"))
			.addOptional(Util.rl("fruittrees", "citrus_cabinet"))
			.addOptional(Util.rl("fruittrees", "cherry_cabinet"));
		tag(ModTags.CABINETS)
			.addTag(DelightfulItemTags.CABINETS_STONE);
		tag(DelightfulItemTags.CABINETS_STONE)
			.add(DelightfulItems.BASALT_CABINET.get())
			.add(DelightfulItems.QUARTZ_CABINET.get());
		tag(ModTags.WILD_CROPS_ITEM)
			.add(DelightfulItems.WILD_SALMONBERRIES.get());
		tag(ForgeTags.CROPS_TOMATO)
			.addOptional(Util.rl("some_assembly_required", "tomato_slices"));
		tag(ForgeTags.VEGETABLES_TOMATO)
			.addOptional(Util.rl("some_assembly_required", "tomato_slices"));
		tag(ForgeTags.CROPS_ONION)
			.addOptional(Util.rl("some_assembly_required", "sliced_onion"));
		tag(ForgeTags.VEGETABLES_ONION)
			.addOptional(Util.rl("some_assembly_required", "sliced_onion"));
		tag(DelightfulItemTags.CROPS_CARROT)
			.add(Items.CARROT)
			.addOptional(Util.rl("some_assembly_required", "chopped_carrot"));
		tag(ForgeTags.VEGETABLES_CARROT)
			.addOptional(Util.rl("some_assembly_required", "chopped_carrot"));
		tag(DelightfulItemTags.CROPS_BEETROOT)
			.add(Items.BEETROOT)
			.addOptional(Util.rl("some_assembly_required", "chopped_beetroot"));
		tag(ForgeTags.VEGETABLES_BEETROOT)
			.addOptional(Util.rl("some_assembly_required", "chopped_beetroot"));

		// Collector's Reap
		tag(DelightfulItemTags.GUMMIES)
			.add(DelightfulItems.SALMONBERRY_GUMMY.get());

		// Forge
		tag(DelightfulItemTags.DOUGH_CORN)
			.addOptional(Util.rl("culturaldelights", "corn_dough"));
		tag(ForgeTags.DOUGH)
			.addTag(DelightfulItemTags.DOUGH_CORN);
		tag(DelightfulItemTags.TOOLS_MACHETES)
			.addOptionalTag(Util.rl("nethersdelight", "tools/machetes"));
		tag(DelightfulItemTags.TOOLS_SCAVENGING)
			.addTag(ForgeTags.TOOLS_KNIVES)
			.addOptionalTag(Util.rl("nethersdelight", "scavenging_tools"));
		tag(DelightfulItemTags.LAVENDER)
			.addOptional(Util.rl("biomesoplenty", "lavender"))
			.addOptional(Util.rl("quark", "lavender_blossom_leaves"));
		tag(DelightfulItemTags.FRUITS_APPLE).add(Items.APPLE);
		tag(DelightfulItemTags.FRUITS_MELON).add(Items.MELON_SLICE);
		tag(DelightfulItemTags.FRUITS_CANTALOUPE).add(DelightfulItems.CANTALOUPE_SLICE.get());
		tag(DelightfulItemTags.FRUITS_CHORUS).add(Items.CHORUS_FRUIT);
		tag(DelightfulItemTags.FRUITS_SWEET_BERRIES).add(Items.SWEET_BERRIES);
		tag(DelightfulItemTags.FRUITS_GLOW_BERRIES).add(Items.GLOW_BERRIES);
		tag(DelightfulItemTags.FRUITS_SALMONBERRIES).add(DelightfulItems.SALMONBERRIES.get());
		tag(DelightfulItemTags.FRUITS_KIWI).addOptional(Util.rl("hedgehog", "kiwi"));
		tag(DelightfulItemTags.FRUITS_PRICKLY_PEAR).addOptional(Util.rl(Mods.ECO, "prickly_pear"));
		tag(DelightfulItemTags.FRUITS_TORCHBERRIES).addOptional(Util.rl(Mods.TF, "torchberries"));
		tag(DelightfulItemTags.FRUITS_SOURCEBERRY).addOptional(Util.rl(Mods.AN, "sourceberry_bush"));
		tag(DelightfulItemTags.FRUITS_ELDERBERRY).addOptional(Util.rl(Mods.RC, "elderberry"));
		tag(DelightfulItemTags.FRUITS_BLACKCURRANT).addOptional(Util.rl(Mods.RC, "blackcurrant"));
		tag(DelightfulItemTags.FRUITS_REDCURRANT).addOptional(Util.rl(Mods.RC, "redcurrant"));
		tag(DelightfulItemTags.FRUITS_WHITECURRANT).addOptional(Util.rl(Mods.RC, "whitecurrant"));
		tag(DelightfulItemTags.FRUITS_BLUEBERRIES)
			.addOptional(Util.rl(Mods.BYG, "blueberries"))
			.addOptional(Util.rl(Mods.WB, "blueberries"))
			.addOptional(Util.rl(Mods.AE, "blue_berry"))
			.addOptional(Util.rl(Mods.AE, "enchanted_berry"));
		tag(DelightfulItemTags.FRUITS_RASPBERRIES)
			.addOptional(Util.rl(Mods.WB, "raspberry"));
		tag(DelightfulItemTags.FRUITS_BLACKBERRIES)
			.addOptional(Util.rl(Mods.WB, "blackberry"));
		tag(DelightfulItemTags.FRUITS_CRANBERRIES)
			.addOptional(Util.rl(Mods.WB, "cranberries"));
		tag(DelightfulItemTags.FRUITS_NIGHTSHADE_BERRIES).addOptional(Util.rl("byg", "nightshade_berries"));
		tag(DelightfulItemTags.FRUITS_CRIMSON_BERRIES).addOptional(Util.rl("byg", "crimson_berries"));
		tag(DelightfulItemTags.FRUITS_WILD_BERRIES)
			.addOptional(Util.rl("windswept", "wild_berries"));
		tag(DelightfulItemTags.FRUITS_STRAWBERRIES)
			.addOptional(Util.rl("neapolitan", "strawberries"))
			.addOptional(Util.rl("neapolitan", "white_strawberries"));
		tag(DelightfulItemTags.FRUITS_BANANA)
			.addOptional(Util.rl("neapolitan", "banana"));
		tag(DelightfulItemTags.FRUITS_CHERRY)
			.addOptional(Util.rl(Mods.FA, "cherry_peach"));
		tag(ForgeTags.BERRIES)
			.addTag(DelightfulItemTags.FRUITS_SWEET_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_GLOW_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.addTag(DelightfulItemTags.FRUITS_TORCHBERRIES)
			.addTag(DelightfulItemTags.FRUITS_SOURCEBERRY)
			.addTag(DelightfulItemTags.FRUITS_ELDERBERRY)
			.addTag(DelightfulItemTags.FRUITS_BLACKCURRANT)
			.addTag(DelightfulItemTags.FRUITS_REDCURRANT)
			.addTag(DelightfulItemTags.FRUITS_WHITECURRANT)
			.addTag(DelightfulItemTags.FRUITS_BLUEBERRIES)
			.addTag(DelightfulItemTags.FRUITS_RASPBERRIES)
			.addTag(DelightfulItemTags.FRUITS_BLACKBERRIES)
			.addTag(DelightfulItemTags.FRUITS_CRANBERRIES)
			.addTag(DelightfulItemTags.FRUITS_NIGHTSHADE_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_CRIMSON_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_CHERRY)
			.addTag(DelightfulItemTags.FRUITS_STRAWBERRIES)
			.addTag(DelightfulItemTags.FRUITS_WILD_BERRIES)
			.addOptionalTag(Util.rl("forge", "fruits/mulberry"))
			.addOptional(Util.rl("aether_redux", "chromaberry"))
			.addOptional(Util.rl("deep_aether", "goldenleaf_berries"))
			.addOptional(Util.rl("undergarden", "blisterberry"))
			.addOptional(Util.rl("enlightened_end", "zure_berry"))
			.addOptional(Util.rl("phantasm", "pream_berry"));
		tag(DelightfulItemTags.FRUITS)
			.addTag(ForgeTags.BERRIES)
			.addTag(DelightfulItemTags.FRUITS_CITRUS)
			.addTag(DelightfulItemTags.FRUITS_SWEET)
			.addTag(DelightfulItemTags.FRUITS_CHORUS)
			.addOptional(Util.rl(Mods.AN, "mendosteen_pod"))
			.addOptional(Util.rl(Mods.AN, "bastion_pod"))
			.addOptional(Util.rl(Mods.AN, "bombegranate_pod"))
			.addOptional(Util.rl(Mods.AN, "frostaya_pod"));
		tag(DelightfulItemTags.FRUITS_CITRUS)
			.addTag(DelightfulItemTags.FRUITS_CITRON)
			.addOptionalTag(DelightfulItemTags.FRUITS_MANDARIN.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_ORANGE.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_LEMON.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_LIME.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_GRAPEFRUIT.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_POMELO.location());
		tag(DelightfulItemTags.FRUITS_SWEET)
			.addTag(DelightfulItemTags.FRUITS_APPLE)
			.addTag(DelightfulItemTags.FRUITS_KIWI)
			.addTag(DelightfulItemTags.FRUITS_MELON)
			.addTag(DelightfulItemTags.FRUITS_CANTALOUPE)
			.addTag(DelightfulItemTags.FRUITS_PRICKLY_PEAR)
			.addTag(DelightfulItemTags.FRUITS_BANANA)
			.addTag(DelightfulItemTags.FRUITS_SWEET_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.addTag(DelightfulItemTags.FRUITS_STRAWBERRIES)
			.addTag(DelightfulItemTags.FRUITS_CHERRY)
			.addTag(DelightfulItemTags.FRUITS_BLUEBERRIES)
			.addTag(DelightfulItemTags.FRUITS_RASPBERRIES)
			.addTag(DelightfulItemTags.FRUITS_BLACKBERRIES)
			.addTag(DelightfulItemTags.FRUITS_CRANBERRIES)
			.addTag(DelightfulItemTags.FRUITS_WILD_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_RAMBUTAN)
			.addTag(DelightfulItemTags.FRUITS_PITAYA)
			.addTag(DelightfulItemTags.FRUITS_JABUTICABA)
			.addTag(DelightfulItemTags.FRUITS_KIWANO)
			.addOptionalTag(DelightfulItemTags.FRUITS_ORANGE.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_MANDARIN.location())
			.addOptionalTag(Util.rl("forge", "fruits/redlove"));
		tag(DelightfulItemTags.FRUITS_GREEN_APPLE)
			.addOptional(Util.rl(Mods.BYG, BYGCompat.green_apple));
		tag(DelightfulItemTags.COCONUT).addOptional(Util.rl(Mods.ECO, "coconut_slice"));
		tag(DelightfulItemTags.NUTS_WALNUT
		).addOptional(Util.rl(Mods.ECO, "walnut"));
		tag(DelightfulItemTags.NUTS_PEANUT)
			.addOptionalTag(Util.rl("forge", "peanut"))
			.addOptional(Util.rl("sprout", "peanut"));
		tag(DelightfulItemTags.NUTS_CHESTNUT)
			.addOptional(Util.rl("windswept", "chestnuts"));
		tag(DelightfulItemTags.NUTS_ACORN)
			.add(DelightfulItems.ACORN.get());
		tag(DelightfulItemTags.NUTS)
			.addTag(DelightfulItemTags.NUTS_ACORN)
			.addTag(DelightfulItemTags.NUTS_WALNUT)
			.addTag(DelightfulItemTags.NUTS_PEANUT)
			.addTag(DelightfulItemTags.NUTS_CHESTNUT);
		tag(DelightfulItemTags.CROPS_GINGER).addOptional(Util.rl("snowyspirit", "ginger"));
		tag(DelightfulItemTags.INGOTS_STEEL).addOptional(Util.rl("simplysteel", "steel_ingot"));
		tag(DelightfulItemTags.WATER).add(Items.WATER_BUCKET);
		tag(DelightfulItemTags.JELLY)
			.add(DelightfulItems.JELLY_BOTTLE.get())
			.add(DelightfulItems.GLOW_JELLY_BOTTLE.get());
		tag(DelightfulItemTags.JELLIES)
			.addTag(DelightfulItemTags.JELLY)
			.addOptionalTag(Util.rl("fruitsdelight", "jelly"));
		tag(DelightfulItemTags.JAM)
			.addTag(DelightfulItemTags.JELLIES);
		tag(DelightfulItemTags.JAMS)
			.addTag(DelightfulItemTags.JAM)
			.addOptionalTag(Util.rl(Mods.WB, "berry_jams"))
			.addOptionalTag(Util.rl("vintagedelight", "sweet_jam_bottles"));
		tag(DelightfulItemTags.NUT_BUTTER)
			.add(DelightfulItems.NUT_BUTTER_BOTTLE.get())
			.addOptional(Util.rl("vintagedelight", "nut_mash_bottle"));
		tag(DelightfulItemTags.SUGAR).add(Items.SUGAR);
		tag(ForgeTags.EGGS)
			.addOptional(Util.rl("deep_aether", "quail_egg"))
			.addOptional(Util.rl("etcetera", "eggple"))
			.addOptional(Util.rl("naturalist", "tortoise_egg"))
			.addOptional(Util.rl("naturalist", "alligator_egg"));
		tag(DelightfulItemTags.CRAB_MEAT)
			.addOptional(Util.rl(Mods.ECO, "crab_meat"))
			.addOptional(Util.rl("quark", "cooked_crab_leg"))
			.addOptional(Util.rl("crabbersdelight", "crab_legs"));
		tag(DelightfulItemTags.CHEESE)
			.addOptional(Util.rl("brewinandchewin", "flaxen_cheese_wedge"))
			.addOptional(Util.rl("farmlife", "tribull_cheese_wedge"))
			.addOptional(Util.rl("croptopia", "cheese"))
			.addOptional(Util.rl("thermal", "cheese_wedge"));
		tag(DelightfulItemTags.CHEESES)
			.addTag(DelightfulItemTags.CHEESE);
		tag(ForgeTags.MILK)
			.addOptional(Util.rl("dracovitadelight", "tribull_milk"))
			.addOptional(Util.rl(Mods.AE, "skyroot_milk_bucket"));
		tag(DelightfulItemTags.TEA_LEAVES)
			.addTag(DelightfulItemTags.TEA_LEAVES_GREEN)
			.addOptionalTag(Util.rl(Mods.FR, "tea_leaves"));
		tag(DelightfulItemTags.TEA_LEAVES_GREEN)
			.add(DelightfulItems.GREEN_TEA_LEAF.get())
			.addOptional(Util.rl(Mods.FR, "green_tea_leaves"));
		tag(DelightfulItemTags.RAW_FISHES_KOI)
			.addOptional(Util.rl("environmental", "koi"))
			.addOptional(Util.rl("crittersandcompanions", "koi_fish"));
		tag(DelightfulItemTags.RAW_FISHES_TUNA)
			.addOptional(Util.rl("spawn", "tuna_chunk"));
		tag(DelightfulItemTags.COOKED_FISHES_TUNA)
			.addOptional(Util.rl("spawn", "cooked_tuna_chunk"));
		tag(ForgeTags.RAW_FISHES)
			.addTag(DelightfulItemTags.RAW_FISHES_KOI)
			.addTag(DelightfulItemTags.RAW_FISHES_TUNA)
			.addOptional(Util.rl("biomemakeover", "glowfish"))
			.addOptional(Util.rl("deep_aether", "raw_aerglow_fish"))
			.addOptional(Util.rl("spawn", "angler_fish"));
		tag(ForgeTags.COOKED_FISHES)
			.addTag(DelightfulItemTags.COOKED_FISHES_TUNA)
			.addOptional(Util.rl("biomemakeover", "cooked_glowfish"))
			.addOptional(Util.rl("deep_aether", "cooked_aerglow_fish"));
		tag(DelightfulItemTags.TD_VENISON_RAW)
			.addOptional(Util.rl("naturalist", "venison"))
			.addOptional(Util.rl("goodall", "raw_venison"))
			.addOptional(Util.rl(Mods.TF, "raw_venison"));
		tag(DelightfulItemTags.RAW_VENISON_COMPAT)
			.addTag(DelightfulItemTags.TD_VENISON_RAW);
		tag(DelightfulItemTags.RAW_VENISON)
			.add(DelightfulItems.VENISON_CHOPS.get())
			.addTag(DelightfulItemTags.RAW_VENISON_COMPAT);
		tag(DelightfulItemTags.TD_VENISON_COOKED)
			.addOptional(Util.rl("naturalist", "cooked_venison"))
			.addOptional(Util.rl("goodall", "cooked_venison"))
			.addOptional(Util.rl(Mods.TF, "cooked_venison"));
		tag(DelightfulItemTags.COOKED_VENISON_COMPAT)
			.addTag(DelightfulItemTags.TD_VENISON_COOKED);
		tag(DelightfulItemTags.COOKED_VENISON)
			.add(DelightfulItems.COOKED_VENISON_CHOPS.get())
			.addTag(DelightfulItemTags.COOKED_VENISON_COMPAT);
		tag(DelightfulItemTags.RAW_GOAT)
			.add(DelightfulItems.RAW_GOAT.get());
		tag(DelightfulItemTags.COOKED_GOAT)
			.add(DelightfulItems.COOKED_GOAT.get());
		tag(DelightfulItemTags.RAW_FROG)
			.addOptional(Util.rl("frog_legs", "frog_legs"));
		tag(DelightfulItemTags.COOKED_FROG)
			.addOptional(Util.rl("frog_legs", "cooked_frog_legs"));
		tag(DelightfulItemTags.FOOD_MEAT)
			.addTag(DelightfulItemTags.RAW_VENISON)
			.addTag(DelightfulItemTags.COOKED_VENISON)
			.addTag(DelightfulItemTags.RAW_GOAT)
			.addTag(DelightfulItemTags.COOKED_GOAT)
			.addTag(DelightfulItemTags.RAW_FROG)
			.addTag(DelightfulItemTags.COOKED_FROG);
		tag(DelightfulItemTags.MEAT)
			.addTag(DelightfulItemTags.FOOD_MEAT);
		tag(DelightfulItemTags.CATTAIL)
			.addOptional(Util.rl("sprout", "cattail"))
			.addOptional(Util.rl(Mods.BOP, "cattail"))
			.addOptional(Util.rl("biomemakeover", "cattail"));
		tag(DelightfulItemTags.GEMS_ROSE_QUARTZ)
			.addOptional(Util.rl(Mods.BOP, "rose_quartz_shard"))
			.addOptional(Util.rl("create", "rose_quartz"));
		tag(DelightfulItemTags.GEMS_ZANITE).addOptional(Util.rl(Mods.AE, "zanite_gemstone"));
		tag(DelightfulItemTags.CHOCOLATE)
			.addOptional(Util.rl("neapolitan", "chocolate_bar"))
			.addOptional(Util.rl("create", "bar_of_chocolate"))
			.addOptionalTag(Util.rl("forge", "chocolatebar"));
		tag(DelightfulItemTags.BARS_CHOCOLATE)
			.addTag(DelightfulItemTags.CHOCOLATE);
		tag(ForgeTags.SALAD_INGREDIENTS)
			.add(DelightfulItems.CHOPPED_CLOVER.get())
			.addOptional(Util.rl("babyfat", "water_lettuce"));
		tag(DelightfulItemTags.SEEDS_SALMONBERRY)
			.add(DelightfulItems.SALMONBERRY_PIPS.get());
		tag(DelightfulItemTags.SEEDS_CANTALOUPE)
			.add(DelightfulItems.CANTALOUPE_SEEDS.get());
		tag(ForgeTags.SEEDS)
			.addTag(DelightfulItemTags.SEEDS_SALMONBERRY)
			.addTag(DelightfulItemTags.SEEDS_CANTALOUPE)
			.addOptional(Util.rl(Mods.FR, "tea_seeds"));
		tag(DelightfulItemTags.TORTILLA)
			.addOptional(Util.rl("culturaldelights", "tortilla"));
		tag(Tags.Items.SEEDS)
			.addOptional(Util.rl("vintagedelight", "oat_seeds"))
			.addOptional(Util.rl("vintagedelight", "ghost_pepper_seeds"))
			.addOptional(Util.rl("vintagedelight", "cucumber_seeds"))
			.addOptional(Util.rl("fruitsdelight", "lemon_seeds"))
			.addOptional(Util.rl("fruitsdelight", "hamimelon_seeds"));

		// Minecraft
		tag(ItemTags.FOX_FOOD)
			.addTag(DelightfulItemTags.FRUITS_SALMONBERRIES);
		tag(ItemTags.SMALL_FLOWERS)
			.add(DelightfulItems.WILD_SALMONBERRIES.get());
		tag(ItemTags.PIGLIN_LOVED).add(Knives.REFINED_GLOWSTONE.get());
		tag(ForgeTags.TOOLS_KNIVES)
			.addTag(ModTags.KNIVES)
			.addOptional(Util.rl("occultism", "butcher_knife"));
		var build = tag(ModTags.KNIVES);
		DelightfulItems.ITEMS.getEntries().stream()
			.map(RegistryObject::get)
			.filter(item -> item instanceof DelightfulKnifeItem)
			.forEach(build::add);

		// Ecologics
		this.addSelf(DelightfulItemTags.COOKED_PRICKLY_PEAR);

		// Forbidden and Arcanus
		this.addSelf(DelightfulItemTags.STELLARITE_PIECE);
		this.addSelf(DelightfulItemTags.DRACO_ARCANUS_STAFF);
		this.addSelf(DelightfulItemTags.DRAGON_SCALE);
		tag(DelightfulItemTags.INGOTS_DEORUM).addOptional(Util.rl(Mods.FA, "deorum_ingot"));

		// Byg
		tag(DelightfulItemTags.INGOTS_PENDORITE).addOptional(Util.rl(Mods.BYG, BYGCompat.pendorite_ingot));

		this.addSelf(DelightfulItemTags.REINFORCED_ECHO_SHARD);

		// Create
		this.addSelf(DelightfulItemTags.POLISHED_ROSE_QUARTZ);
		this.addSelf(DelightfulItemTags.ZINC_HANDLE);
		this.addSelf(DelightfulItemTags.HEAP_EXPERIENCE);

		// Seeds
		this.addSelf(DelightfulItemTags.SHARP_LEAF);

		// Nether's Exoticism
		this.addSelf(DelightfulItemTags.KIWANO_PEEL);
		tag(DelightfulItemTags.FRUITS_CITRON).addOptional(Util.rl("nethers_exoticism", "bouddha_s_hand"));
		tag(DelightfulItemTags.FRUITS_JABUTICABA).addOptional(Util.rl("nethers_exoticism", "jaboticaba"));
		tag(DelightfulItemTags.FRUITS_PITAYA).addOptional(Util.rl("nethers_exoticism", "pitaya"));
		tag(DelightfulItemTags.FRUITS_RAMBUTAN).addOptional(Util.rl("nethers_exoticism", "ramboutan"));
		tag(DelightfulItemTags.FRUITS_KIWANO).addOptional(Util.rl("nethers_exoticism", "kiwano"));

		// RL
		this.addSelf(DelightfulItemTags.SWEETENED_CHUNK);

		// SAS
		this.addSelf(DelightfulItemTags.BURGER_BUN);
		this.addSelf(DelightfulItemTags.BREAD_SLICE);

		// Create
		tag(DelightfulItemTags.UPRIGHT_ON_BELT)
			.addTag(DelightfulItemTags.JAMS)
			.add(DelightfulItems.NUT_BUTTER_BOTTLE.get())
			.add(DelightfulItems.PRICKLY_PEAR_JUICE.get())
			.add(DelightfulItems.ENDER_NECTAR.get())
			.add(DelightfulItems.AZALEA_TEA.get())
			.add(DelightfulItems.LAVENDER_TEA.get())
			.add(DelightfulItems.MATCHA_LATTE.get())
			.add(DelightfulItems.BERRY_MATCHA_LATTE.get())
			.add(DelightfulItems.ANIMAL_OIL_BOTTLE.get())
			.add(DelightfulItems.SALMONBERRY_PIE.get())
			.addOptional(Util.rl(Mods.FR, "green_tea"))
			.addOptional(Util.rl(Mods.FR, "yellow_tea"))
			.addOptional(Util.rl(Mods.FR, "black_tea"))
			.addOptional(Util.rl(Mods.FR, "rose_hip_tea"))
			.addOptional(Util.rl(Mods.FR, "dandelion_tea"))
			.addOptional(Util.rl(Mods.FR, "purulent_tea"))
			.addOptional(Util.rl(Mods.FR, "gamblers_tea"));

		// Serene Seasons
		tag(DelightfulItemTags.SUMMER_CROPS)
			.addTag(DelightfulItemTags.SEEDS_SALMONBERRY)
			.addTag(DelightfulItemTags.SEEDS_CANTALOUPE);

		// Phantasm
		this.addSelf(DelightfulItemTags.VOID_CRYSTAL_BLOCK);
		tag(DelightfulItemTags.CRYSTAL_SPIKE_TIPS)
			.addOptional(Util.rl(Mods.EP, "crystal_spike_tip"))
			.addOptional(Util.rl(Mods.EP, "void_crystal_spike_tip"));
		tag(DelightfulItemTags.INGOTS_STELLIUM)
			.addOptional(Util.rl(Mods.EP, "stellium_ingot"));

		tag(DelightfulItemTags.SOUL_STEEL_INGOT)
			.addOptional(Util.rl("spirit", "soul_steel_ingot"));
		tag(DelightfulItemTags.SOUL_STEEL_MAINHAND).add(Knives.SOUL_STEEL.get());

		// Botania
		this.addSelf(DelightfulItemTags.LIVINGWOOD_TWIG);
		this.addSelf(DelightfulItemTags.DREAMWOOD_TWIG);
		tag(DelightfulItemTags.MANA_ITEMS)
			.add(Knives.MANASTEEL.get())
			.add(Knives.ELEMENTIUM.get())
			.add(Knives.TERRA.get());
		tag(DelightfulItems.ingot("alfsteel"))
			.addOptional(Util.rl("mythicbotany", "alfsteel_ingot"));

		// Additional Additions
		this.addSelf(DelightfulItemTags.ROSE_GOLD_ALLOY);
		this.addSelf(DelightfulItemTags.GOLD_RING);
		this.addSelf(GildedNetheriteKnifeItem.upgrade);
		this.addSelf(RoseGoldKnifeItem.upgrade);

		// Enderite
		this.addSelf(EnderiteKnifeItem.upgrade);

		// Nourished Nether
		tag(DelightfulItemTags.NECRONIUM_INGOT)
			.addOptional(Util.rl("nourished_nether", "necronium_ingot"));
		tag(DelightfulItemTags.NECRONIUM_TOOLS).add(Knives.NECRONIUM.get());

		// Undergarden
		tag(DelightfulItemTags.CLOGGRUM_ITEMS).add(Knives.CLOGGRUM.get());
		tag(DelightfulItemTags.FROSTSTEEL_ITEMS).add(Knives.FROSTSTEEL.get());
		tag(DelightfulItemTags.UTHERIUM_ITEMS).add(Knives.UTHERIUM.get());
		this.addSelf(ForgottenKnifeItem.upgrade);

		// Deeper and Darker
		this.addSelf(WardenKnifeItem.upgrade);

		// Aether
		this.addSelf(DelightfulItemTags.ENCHANTED_GRAVITITE);
		this.addSelf(DelightfulItemTags.HOLYSTONE);

		// Aether Redux
		this.tag(DelightfulItemTags.INGOTS_VERIDIUM)
			.addOptional(Util.rl("aether_redux", "veridium_ingot"));

		// Deep Aether
		this.tag(DelightfulItemTags.GEMS_SKYJADE)
			.addOptionalTag(Util.rl("deep_aether", "skyjade_repairing"));
		this.tag(DelightfulItemTags.INGOTS_STRATUS)
			.addOptionalTag(Util.rl("deep_aether", "stratus_repairing"));
		this.addSelf(DelightfulItemTags.STRATUS_UPGRADE);

		// AE2
		this.addSelf(DelightfulItemTags.FLUIX_BLOCK);
		this.addSelf(DelightfulItemTags.FLUIX_UPGRADE_SMITHING_TEMPLATE);
		this.tag(DelightfulItemTags.QUARTZ_KNIFE)
			.add(Knives.NETHER_QUARTZ.get())
			.add(Knives.CERTUS_QUARTZ.get());

		// Naturalist
		this.tag(Util.it("naturalist", "bird_food_items")).addTag(Tags.Items.SEEDS);
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public @NotNull String getName() {
		return Delightful.MODID;
	}

	private void addSelf(TagKey<Item> item) {
		tag(item).addOptional(item.location());
	}
}

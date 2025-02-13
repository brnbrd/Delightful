package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.common.item.food.*;
import net.brnbrd.delightful.compat.*;
import net.brnbrd.delightful.compat.ars_nouveau.*;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.registry.ModItems;
import java.util.function.Supplier;

public class DelightfulItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Delightful.MODID);

	public static final RegistryObject<Item> QUARTZ_CABINET = registerItem("quartz_cabinet",
			() -> new BlockItem(DelightfulBlocks.QUARTZ_CABINET.get(), ModItems.basicItem()));
	public static final RegistryObject<Item> BASALT_CABINET = registerItem("basalt_cabinet",
			() -> new BlockItem(DelightfulBlocks.BASALT_CABINET.get(), ModItems.basicItem()));

	public static final RegistryObject<Item> SALMONBERRY_SACK = registerItem("salmonberry_sack", () ->
			new BlockItem(DelightfulBlocks.SALMONBERRY_SACK.get(), ModItems.basicItem()));
	public static final RegistryObject<Item> ACORN_SACK = registerItem("acorn_sack", () ->
			new BlockItem(DelightfulBlocks.ACORN_SACK.get(), ModItems.basicItem()));
	public static final RegistryObject<Item> MENDOSTEEN_CRATE = registerItem("mendosteen_crate", () ->
			new ArsCrateBlockItem(DelightfulBlocks.MENDOSTEEN_CRATE.get(), (new Item.Properties())));
	public static final RegistryObject<Item> BASTION_FRUIT_CRATE = registerItem("bastion_fruit_crate", () ->
			new ArsCrateBlockItem(DelightfulBlocks.BASTION_FRUIT_CRATE.get(), (new Item.Properties())));
	public static final RegistryObject<Item> FROSTAYA_CRATE = registerItem("frostaya_crate", () ->
			new ArsCrateBlockItem(DelightfulBlocks.FROSTAYA_CRATE.get(), (new Item.Properties())));
	public static final RegistryObject<Item> BOMBEGRANATE_CRATE = registerItem("bombegranate_crate", () ->
			new ArsCrateBlockItem(DelightfulBlocks.BOMBEGRANATE_CRATE.get(), (new Item.Properties())));
	public static final RegistryObject<Item> BLUEBERRY_SACK = registerItem("blueberry_sack", () ->
			new CompatBlockItem(DelightfulBlocks.BLUEBERRY_SACK.get(), (new Item.Properties()), Mods.BWG));
	public static final RegistryObject<Item> GREEN_APPLE_CRATE = registerItem("green_apple_crate", () ->
			new CompatBlockItem(DelightfulBlocks.GREEN_APPLE_CRATE.get(), (new Item.Properties()), Mods.BWG));
	public static final RegistryObject<Item> YUCCA_FRUIT_CRATE = registerItem("yucca_fruit_crate", () ->
			new CompatBlockItem(DelightfulBlocks.YUCCA_FRUIT_CRATE.get(), (new Item.Properties()), Mods.BWG));
	public static final RegistryObject<Item> BAOBAB_FRUIT_CRATE = registerItem("baobab_fruit_crate", () ->
			new CompatBlockItem(DelightfulBlocks.BAOBAB_FRUIT_CRATE.get(), (new Item.Properties()), Mods.BWG));

	public static final RegistryObject<Item> MATCHA_ICE_CREAM_BLOCK = registerItem("matcha_ice_cream_block", () ->
			new CompatBlockItem(DelightfulBlocks.MATCHA_ICE_CREAM_BLOCK.get(), (new Item.Properties()), Mods.N));
	public static final RegistryObject<Item> SALMONBERRY_ICE_CREAM_BLOCK = registerItem("salmonberry_ice_cream_block", () ->
			new CompatBlockItem(DelightfulBlocks.SALMONBERRY_ICE_CREAM_BLOCK.get(), (new Item.Properties()), Mods.N));
	public static final RegistryObject<Item> SOURCE_BERRY_ICE_CREAM_BLOCK = registerItem("source_berry_ice_cream_block", () ->
			new SourceBerryIceCreamBlockItem(DelightfulBlocks.SOURCE_BERRY_ICE_CREAM_BLOCK.get(),  (new Item.Properties())));

	public static final RegistryObject<Item> MATCHA_ICE_CREAM = registerItem("matcha_ice_cream", () -> new IceCreamItem((new Item.Properties()).food(Nutrition.MATCHA_ICE_CREAM)));
	public static final RegistryObject<Item> SALMONBERRY_ICE_CREAM = registerItem("salmonberry_ice_cream", () -> new IceCreamItem((new Item.Properties()).food(Nutrition.SALMONBERRY_ICE_CREAM)));
	public static final RegistryObject<Item> SOURCE_BERRY_ICE_CREAM = registerItem("source_berry_ice_cream", () -> new SourceBerryIceCreamItem((new Item.Properties()).food(ArsNouveauCompat.SOURCE_BERRY_ICE_CREAM)));
	public static final RegistryObject<Item> MATCHA_MILKSHAKE = registerItem("matcha_milkshake",
			() -> new ShakeItem(new Item.Properties().food(Nutrition.MATCHA_MILKSHAKE)));
	public static final RegistryObject<Item> SALMONBERRY_MILKSHAKE = registerItem("salmonberry_milkshake",
			() -> new ShakeItem(new Item.Properties().food(Nutrition.SALMONBERRY_MILKSHAKE)));
	public static final RegistryObject<Item> SOURCE_BERRY_MILKSHAKE = registerItem("source_berry_milkshake",
			() -> new SourceBerryShakeItem(new Item.Properties().food(ArsNouveauCompat.SOURCE_BERRY_MILKSHAKE)));
	public static final RegistryObject<Item> MATCHA = registerItem("matcha", () -> new MatchaItem(new Item.Properties().food(Nutrition.MATCHA)));
	public static final RegistryObject<Item> GREEN_TEA_LEAF = registerItem("green_tea_leaf", () -> new GreenTeaLeavesItem(new Item.Properties().food(Nutrition.GREEN_TEA_LEAF)));
	public static final RegistryObject<Item> SALMONBERRIES = registerFood("salmonberries", Nutrition.SALMONBERRIES);
	public static final RegistryObject<Item> SALMONBERRY_PIPS = registerItem("salmonberry_pips",
			() -> new ItemNameBlockItem(DelightfulBlocks.SALMONBERRY_BUSH.get(), ModItems.basicItem()));
	public static final RegistryObject<Item> WILD_SALMONBERRIES = registerItem("wild_salmonberries",
			() -> new BlockItem(DelightfulBlocks.WILD_SALMONBERRIES.get(), ModItems.basicItem()));
	public static final RegistryObject<Item> SALMONBERRY_PIE = registerItem("salmonberry_pie",
			() -> new BlockItem(DelightfulBlocks.SALMONBERRY_PIE.get(), ModItems.basicItem()));
	public static final RegistryObject<Item> SALMONBERRY_PIE_SLICE = registerItem("salmonberry_pie_slice",
			() -> new DConsumableItem((new Item.Properties()).food(Nutrition.SALMONBERRY_PIE_SLICE), true, false));
	public static final RegistryObject<Item> PUMPKIN_PIE_SLICE = registerItem("pumpkin_pie_slice",
			() -> new PumpkinPieSliceItem((new Item.Properties()).food(FoodValues.PIE_SLICE)));
	public static final RegistryObject<Item> GLOOMGOURD_PIE_SLICE = registerCompatPieSlice("gloomgourd_pie",
			UndergardenCompat.GLOOMGOURD_PIE_SLICE.get(), Mods.UG);
	public static final RegistryObject<Item> BLUEBERRY_PIE_SLICE = registerCompatPieSlice(BWGCompat.blueberry_pie,
			FoodValues.PIE_SLICE, Mods.BWG);
	public static final RegistryObject<Item> GREEN_APPLE_PIE_SLICE = registerCompatPieSlice(BWGCompat.green_apple_pie,
			BWGCompat.GREEN_APPLE_PIE_SLICE.get(), Mods.BWG);
	public static final RegistryObject<Item> MULBERRY_PIE_SLICE = registerCompatPieSlice(AquaticCompat.mulberry_pie,
			AquaticCompat.MULBERRY_PIE_SLICE, Mods.UA);
	public static final RegistryObject<Item> CHORUS_PIE_SLICE = registerCompatPieSlice(UnusualEndCompat.chorus_pie,
			UnusualEndCompat.CHORUS_PIE_SLICE, Mods.UE);
	public static final RegistryObject<Item> CHORUS_MUFFIN = registerItem("chorus_muffin",
			() -> new ChorusMuffinItem((new Item.Properties()).food(UnusualEndCompat.CHORUS_MUFFIN)));
	public static final RegistryObject<Item> SOURCE_BERRY_PIE_SLICE = registerItem("source_berry_pie_slice",
			() -> new SourceFoodItem((new Item.Properties()).food(ArsNouveauCompat.SOURCE_BERRY_PIE_SLICE)));
	public static final RegistryObject<Item> SOURCE_BERRY_COOKIE = registerItem("source_berry_cookie",
			() -> new SourceFoodItem((new Item.Properties()).food(ArsNouveauCompat.SOURCE_BERRY_COOKIE)));
	public static final RegistryObject<Item> BAKLAVA = registerItem("baklava",
			() -> new BlockItem(DelightfulBlocks.BAKLAVA.get(), ModItems.basicItem()));
	public static final RegistryObject<Item> BAKLAVA_SLICE = registerItem("baklava_slice",
			() -> new DConsumableItem((new Item.Properties()).food(Nutrition.BAKLAVA_SLICE), true, false));
	public static final RegistryObject<Item> ANIMAL_FAT = registerFood("animal_fat", Nutrition.ANIMAL_FAT);
	public static final RegistryObject<Item> ANIMAL_OIL_BOTTLE = registerItem("animal_oil_bottle",
			() -> new FurnaceFuelItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE), 3200));
	public static final RegistryObject<Item> ACORN = registerFood("acorn", Nutrition.ACORN);
	public static final RegistryObject<Item> ROASTED_ACORN = registerFood("roasted_acorn", Nutrition.ROASTED_ACORN);
	public static final RegistryObject<Item> NUT_DOUGH = registerFood("nut_dough", Nutrition.NUT_DOUGH);
	public static final RegistryObject<Item> CHOPPED_CLOVER = registerItem("chopped_clover", () -> new ChoppedCloverItem((new Item.Properties()).food(Nutrition.CHOPPED_CLOVER)));
	public static final RegistryObject<Item> CACTUS_FLESH = registerFood("cactus_flesh", Nutrition.CACTUS_FLESH);
	public static final RegistryObject<Item> CACTUS_STEAK = registerFood("cactus_steak", Nutrition.CACTUS_STEAK);
	public static final RegistryObject<Item> CACTUS_CHILI = registerItem("cactus_chili", () -> new DConsumableItem((new Item.Properties()).food(Nutrition.CACTUS_CHILI).stacksTo(16).craftRemainder(Items.BOWL), true, false));
	public static final RegistryObject<Item> CACTUS_SOUP = registerItem("cactus_soup", () -> new DConsumableItem((new Item.Properties()).food(Nutrition.CACTUS_SOUP).stacksTo(16).craftRemainder(Items.BOWL), true, false));
	public static final RegistryObject<Item> CACTUS_SOUP_CUP = registerItem("cactus_soup_cup", () -> new CupItem((new Item.Properties()).food(Nutrition.CACTUS_SOUP_CUP), true, false));
	public static final RegistryObject<Item> VENISON_STEW = registerItem("venison_stew", () -> new VenisonStewItem((new Item.Properties()).food(Nutrition.VENISON_STEW).stacksTo(16).craftRemainder(Items.BOWL), true, false));
	public static final RegistryObject<Item> VENISON_STEW_CUP = registerItem("venison_stew_cup", () -> new VenisonStewCupItem((new Item.Properties()).food(Nutrition.VENISON_STEW_CUP), true, false));
	public static final RegistryObject<Item> SINIGANG = registerItem("sinigang", () -> new ConsumableItem((new Item.Properties()).food(Nutrition.SINIGANG).craftRemainder(Items.BOWL).stacksTo(16), true, false));
	public static final RegistryObject<Item> COCONUT_CURRY = registerItem("coconut_curry",
			() -> new CoconutCurryItem((new Item.Properties()).food(Nutrition.COCONUT_CURRY).stacksTo(16).craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> FIELD_SALAD = registerItem("field_salad", () -> new DConsumableItem((new Item.Properties()).food(Nutrition.FIELD_SALAD).stacksTo(16).craftRemainder(Items.BOWL), true, false));
	public static final RegistryObject<Item> ENDER_NECTAR = registerItem("ender_nectar",
			() -> new EnderNectarItem((new Item.Properties()).food(Nutrition.ENDER_NECTAR).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
	public static final RegistryObject<Item> MATCHA_LATTE = registerItem("matcha_latte",
			() -> new MatchaLatteItem((new Item.Properties()).food(Nutrition.MATCHA_LATTE), true));
	public static final RegistryObject<Item> BERRY_MATCHA_LATTE = registerItem("berry_matcha_latte",
			() -> new MatchaLatteItem((new Item.Properties()).food(Nutrition.BERRY_MATCHA_LATTE), true));
	public static final RegistryObject<Item> AZALEA_TEA = registerItem("azalea_tea",
		() -> new TeaItem((new Item.Properties()).food(Nutrition.AZALEA_TEA), DelightfulItemTags.FLOWERS_AZALEA, false));
	public static final RegistryObject<Item> LAVENDER_TEA = registerItem("lavender_tea",
		() -> new TeaItem((new Item.Properties()).food(Nutrition.LAVENDER_TEA), DelightfulItemTags.LAVENDER, false));
	public static final RegistryObject<Item> PRICKLY_PEAR_JUICE = registerItem("prickly_pear_juice",
		() -> new PricklyPearJuiceItem((new Item.Properties()).food(Nutrition.PRICKLY_PEAR_JUICE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
	public static final RegistryObject<Item> JAM_JAR = registerItem("jam_jar", () -> new JamJarItem((new Item.Properties()).food(Nutrition.JAM_JAR).craftRemainder(Items.GLASS_BOTTLE), false, false));
	public static final RegistryObject<Item> GLOW_JAM_JAR = registerItem("glow_jam_jar", () -> new JamJarItem((new Item.Properties()).food(Nutrition.GLOW_JAM_JAR).craftRemainder(Items.GLASS_BOTTLE), true, false));
	public static final RegistryObject<Item> NUT_BUTTER_BOTTLE = registerItem("nut_butter_bottle", () -> new NutButterBottleItem(((new Item.Properties()).food(Nutrition.NUT_BUTTER_BOTTLE).craftRemainder(Items.GLASS_BOTTLE))));
	public static final RegistryObject<Item> NUT_BUTTER_AND_JAM_SANDWICH = registerItem("nut_butter_and_jam_sandwich", () -> new NutButterJamSandwichItem((new Item.Properties()).food(Nutrition.NUT_BUTTER_AND_JAM_SANDWICH)));
	public static final RegistryObject<Item> CHEESEBURGER = registerItem("cheeseburger", () -> new CheeseburgerItem((new Item.Properties()).food(Nutrition.CHEESEBURGER)));
	public static final RegistryObject<Item> DELUXE_CHEESEBURGER = registerConsumable("deluxe_cheeseburger", Nutrition.DELUXE_CHEESEBURGER, null, true, false);
	public static final RegistryObject<Item> CHUNKWICH = registerCompatFood("chunkwich", Nutrition.CHUNKWICH, true, Mods.RL);
	public static final RegistryObject<Item> ROCK_CANDY = registerItem("rock_candy",
			() -> new RockCandyItem((new Item.Properties()).food(Nutrition.ROCK_CANDY).craftRemainder(Items.STICK)));
	public static final RegistryObject<Item> MARSHMALLOW_STICK = registerConsumable("marshmallow_stick", Nutrition.MARSHMALLOW_STICK, Items.STICK, false, false);
	public static final RegistryObject<Item> COOKED_MARSHMALLOW_STICK = registerConsumable("cooked_marshmallow_stick", Nutrition.COOKED_MARSHMALLOW_STICK, Items.STICK, true, false);
	public static final RegistryObject<Item> SMORE = registerConsumable("smore", Nutrition.SMORE, null, true, false);
	public static final RegistryObject<Item> CRAB_RANGOON = registerItem("crab_rangoon",
			() -> new CrabRangoonItem(new Item.Properties().food(Nutrition.CRAB_RANGOON)));
	public static final RegistryObject<Item> HONEY_GLAZED_WALNUT = registerItem("honey_glazed_walnut",
			() -> new HoneyGlazedWalnutItem(new Item.Properties().food(Nutrition.HONEY_GLAZED_WALNUT)));
	public static final RegistryObject<Item> VENISON_CHOPS = registerItem("venison_chops",
			() -> new VenisonChopsItem(new Item.Properties().food(Nutrition.VENISON_CHOPS)));
	public static final RegistryObject<Item> COOKED_VENISON_CHOPS = registerItem("cooked_venison_chops",
			() -> new VenisonChopsItem(new Item.Properties().food(Nutrition.COOKED_VENISON_CHOPS)));
	public static final RegistryObject<Item> RAW_GOAT = registerItem("raw_goat",
			() -> new GoatMeatItem(new Item.Properties().food(Nutrition.RAW_GOAT)));
	public static final RegistryObject<Item> COOKED_GOAT = registerItem("cooked_goat",
			() -> new GoatMeatItem(new Item.Properties().food(Nutrition.COOKED_GOAT)));
	public static final RegistryObject<Item> MINI_MELON = registerItem("mini_melon", () ->
			new BlockItem(DelightfulBlocks.MINI_MELON.get(), ModItems.basicItem()));
	public static final RegistryObject<Item> CANTALOUPE = registerItem("cantaloupe", () ->
			new BlockItem(DelightfulBlocks.CANTALOUPE.get(), ModItems.basicItem()));
	public static final RegistryObject<Item> CANTALOUPE_SEEDS = registerItem("cantaloupe_seeds",
			() -> new ItemNameBlockItem(DelightfulBlocks.CANTALOUPE_PLANT.get(), ModItems.basicItem()));
	public static final RegistryObject<Item> CANTALOUPE_SLICE = registerFood("cantaloupe_slice", Nutrition.CANTALOUPE_SLICE);
	public static final RegistryObject<Item> CANTALOUPE_BREAD = registerConsumable("cantaloupe_bread", Nutrition.CANTALOUPE_BREAD, null, true, false);
	public static final RegistryObject<Item> WRAPPED_CANTALOUPE = registerConsumable("wrapped_cantaloupe", Nutrition.WRAPPED_CANTALOUPE, null, true, false);
	public static final RegistryObject<Item> CANTALOUPE_POPSICLE = registerConsumable("cantaloupe_popsicle", Nutrition.CANTALOUPE_POPSICLE, Items.STICK, true, false);
	public static final RegistryObject<Item> STUFFED_CANTALOUPE_BLOCK = registerItem("stuffed_cantaloupe_block",
			() -> new BlockItem(DelightfulBlocks.STUFFED_CANTALOUPE_BLOCK.get(), ModItems.basicItem().stacksTo(1)));
	public static final RegistryObject<Item> STUFFED_CANTALOUPE = registerConsumable("stuffed_cantaloupe", Nutrition.STUFFED_CANTALOUPE, Items.BOWL, true, false);

	// Gummies
	public static final RegistryObject<Item> SALMONBERRY_GUMMY = registerItem("salmonberry_gummy",
			() -> new GummyItem((new Item.Properties()).food(Nutrition.SALMONBERRY_GUMMY)));
	public static final RegistryObject<Item> MATCHA_GUMMY = registerItem("matcha_gummy",
			() -> new GummyItem((new Item.Properties()).food(Nutrition.MATCHA_GUMMY)));
	public static final RegistryObject<Item> CANTALOUPE_GUMMY = registerItem("cantaloupe_gummy",
			() -> new GummyItem((new Item.Properties()).food(Nutrition.CANTALOUPE_GUMMY)));
	public static final RegistryObject<Item> SOURCE_BERRY_GUMMY = registerItem("source_berry_gummy",
			() -> new SourceBerryGummyItem((new Item.Properties()).food(ArsNouveauCompat.SOURCE_BERRY_GUMMY)));

	// Registers food, optional craftRemainder
	public static RegistryObject<Item> registerFood(String name, FoodProperties food, Item... remainder) {
		if (remainder.length > 0) {
			if (remainder[0].equals(Items.BOWL)) {
				return registerItem(name,
						() -> new BowlFoodItem((new Item.Properties()).food(food).craftRemainder(Items.BOWL)));
			} else {
				return registerConsumable(name, food, remainder[0], false, false);
			}
		}
		return registerItem(name, () -> new DConsumableItem((new Item.Properties()).food(food)));
	}

	public static RegistryObject<Item> registerConsumable(String name, FoodProperties food, @Nullable Item remainder, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
		return registerItem(name,
			() -> new DConsumableItem((new Item.Properties()).food(food).craftRemainder(remainder), hasFoodEffectTooltip, hasCustomTooltip));
	}

	public static RegistryObject<Item> registerCompatFood(String name, FoodProperties food, boolean hasFoodEffectTooltip, String modid) {
		return registerItem(name,
			() -> new CompatConsumableItem((new Item.Properties().food(food)), hasFoodEffectTooltip, false, modid));
	}

	public static RegistryObject<Item> registerCompatPieSlice(String pieName, FoodProperties food, String modid) {
		return registerItem(pieName + "_slice",
			() -> new CompatPieSliceItem((new Item.Properties().food(food)), modid));
	}

	public static RegistryObject<Item> registerItem(String name, Supplier<Item> item) {
		return ITEMS.register(name, item);
	}

	public static void create(IEventBus bus) {
		ITEMS.register(bus);
	}
}
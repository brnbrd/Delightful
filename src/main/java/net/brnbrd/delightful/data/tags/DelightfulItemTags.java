package net.brnbrd.delightful.data.tags;

import net.brnbrd.delightful.compat.Modid;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class DelightfulItemTags {
	// Delightful
	public static final TagKey<Item> COMPAT_PIES = d("compat_pies");
	public static final TagKey<Item> FIRE_KNIVES = d("fire_knives");
	public static final TagKey<Item> RAW_VENISON_COMPAT = d("raw_venison_compat");
	public static final TagKey<Item> RAW_VENISON_CHOP_COMPAT = d("raw_venison_chop_compat");
	public static final TagKey<Item> COOKED_VENISON_COMPAT = d("cooked_venison_compat");
	public static final TagKey<Item> COOKED_VENISON_CHOP_COMPAT = d("cooked_venison_chop_compat");
	public static final TagKey<Item> ROTTEN = d("rotten");
	public static final TagKey<Item> MAKES_DYE_RED = d("makes_dye/red");
	public static final TagKey<Item> MAKES_DYE_YELLOW = d("makes_dye/yellow");
	public static final TagKey<Item> MAKES_DYE_WHITE = d("makes_dye/white");
	public static final TagKey<Item> MAKES_DYE_BLUE = d("makes_dye/blue");
	public static final TagKey<Item> MAKES_DYE_BLACK = d("makes_dye/black");
	public static final TagKey<Item> MAKES_DYE_ORANGE = d("makes_dye/orange");
	public static final TagKey<Item> MAKES_DYE_PURPLE = d("makes_dye/purple");
	public static final TagKey<Item> MAKES_DYE_MAGENTA = d("makes_dye/purple");
	public static final TagKey<Item> MAKES_DYE_PINK = d("makes_dye/pink");
	public static final TagKey<Item> MAKES_DYE_GREEN = d("makes_dye/green");
	public static final TagKey<Item> MAKES_DYE_LIME = d("makes_dye/lime");
	public static final TagKey<Item> MAKES_DYE_CYAN = d("makes_dye/cyan");
	public static final TagKey<Item> MAKES_DYE_LIGHT_GRAY = d("makes_dye/light_gray");
	public static final TagKey<Item> MAKES_DYE_LIGHT_BLUE = d("makes_dye/light_gray");

	// Minecraft
	public static final TagKey<Item> FLOWERS_AZALEA = Modid.MC.it("flowers/azalea");

	// Farmers Delight
	public static final TagKey<Item> CABINETS_STONE = Modid.FD.it("cabinets/stone");
	public static final TagKey<Item> STRAW_PLANTS = Modid.FD.it("straw_plants");

	// Fruits
	public static final TagKey<Item> FRUITS = forge("fruits");
	public static final TagKey<Item> FRUITS_APPLE = forge("fruits/apple");
	public static final TagKey<Item> FRUITS_KIWI = forge("fruits/kiwi");
	public static final TagKey<Item> FRUITS_MELON = forge("fruits/melon");
	public static final TagKey<Item> FRUITS_CANTALOUPE = forge("fruits/cantaloupe");
	public static final TagKey<Item> FRUITS_CHORUS = forge("fruits/chorus");
	public static final TagKey<Item> FRUITS_SWEET_BERRIES = forge("fruits/sweet_berries");
	public static final TagKey<Item> FRUITS_GLOW_BERRIES = forge("fruits/glow_berries");
	public static final TagKey<Item> FRUITS_SALMONBERRIES = forge("fruits/salmonberries");
	public static final TagKey<Item> FRUITS_STRAWBERRIES = forge("fruits/strawberries");
	public static final TagKey<Item> FRUITS_TORCHBERRIES = forge("fruits/torchberries");
	public static final TagKey<Item> FRUITS_SOURCEBERRY = forge("fruits/sourceberry");
	public static final TagKey<Item> FRUITS_ELDERBERRY = forge("fruits/elderberry");
	public static final TagKey<Item> FRUITS_GEARO_BERRY = forge("fruits/gearo_berry");
	public static final TagKey<Item> FRUITS_REDCURRANT = forge("fruits/redcurrant");
	public static final TagKey<Item> FRUITS_CURRANT = forge("fruits/currant");
	public static final TagKey<Item> FRUITS_WHITECURRANT = forge("fruits/whitecurrant");
	public static final TagKey<Item> FRUITS_BLUEBERRIES = forge("fruits/blueberries");
	public static final TagKey<Item> FRUITS_RASPBERRIES = forge("fruits/raspberries");
	public static final TagKey<Item> FRUITS_BLACKBERRIES = forge("fruits/blackberries");
	public static final TagKey<Item> FRUITS_CRANBERRIES = forge("fruits/cranberries");
	public static final TagKey<Item> FRUITS_WILD_BERRIES = forge("fruits/wild_berries");
	public static final TagKey<Item> FRUITS_GREEN_APPLE = forge("fruits/green_apple");
	public static final TagKey<Item> FRUITS_YUCCA = forge("fruits/yucca");
	public static final TagKey<Item> FRUITS_BAOBAB = forge("fruits/baobab");
	public static final TagKey<Item> FRUITS_BANANA = forge("fruits/banana");
	public static final TagKey<Item> FRUITS_ORANGE = forge("fruits/orange");
	public static final TagKey<Item> FRUITS_MANDARIN = forge("fruits/mandarin");
	public static final TagKey<Item> FRUITS_PASSION_FRUIT = forge("fruits/passion_fruit");
	public static final TagKey<Item> FRUITS_DRAGON_FRUIT = forge("fruits/dragon_fruit");
	public static final TagKey<Item> FRUITS_PLUM = forge("fruits/plum");
	public static final TagKey<Item> FRUITS_POMEGRANATE = forge("fruits/pomegranate");
	public static final TagKey<Item> FRUITS_LEMON = forge("fruits/lemon");
	public static final TagKey<Item> FRUITS_LIME = forge("fruits/lime");
	public static final TagKey<Item> FRUITS_GRAPEFRUIT = forge("fruits/grapefruit");
	public static final TagKey<Item> FRUITS_POMELO = forge("fruits/pomelo");
	public static final TagKey<Item> FRUITS_CHERRY = forge("fruits/cherry");
	public static final TagKey<Item> FRUITS_CITRON = forge("fruits/citron");
	public static final TagKey<Item> FRUITS_PITAYA = forge("fruits/pitaya");
	public static final TagKey<Item> FRUITS_RAMBUTAN = forge("fruits/rambutan");
	public static final TagKey<Item> FRUITS_JABUTICABA = forge("fruits/jabuticaba");
	public static final TagKey<Item> FRUITS_KIWANO = forge("fruits/kiwano");
	public static final TagKey<Item> FRUITS_SWEET = forge("fruits/sweet");
	public static final TagKey<Item> FRUITS_PRICKLY_PEAR = forge("fruits/prickly_pear");
	public static final TagKey<Item> FRUITS_CITRUS = forge("fruits/citrus");

	// Vegetables
	public static final TagKey<Item> VEGETABLES_SPICY = forge("vegetables/spicy");
	public static final TagKey<Item> VEGETABLES_CORN = forge("vegetables/corn");
	public static final TagKey<Item> VEGETABLES_GINGER = forge("vegetables/ginger");
	public static final TagKey<Item> VEGETABLES_CUCUMBER = forge("vegetables/cucumber");
	public static final TagKey<Item> CUCUMBER = forge("cucumber");

	// Forge
	public static final TagKey<Item> HOT_SPICE = forge("hot_spice");
	public static final TagKey<Item> PUMPKINS = forge("pumpkins");
	public static final TagKey<Item> PUMPKINS_CARVED = forge("pumpkins/carved");
	public static final TagKey<Item> CHOCOLATE = forge("chocolate");
	public static final TagKey<Item> CHEESE = forge("cheese");
	public static final TagKey<Item> RAW_CRAB = forge("raw_crab");
	public static final TagKey<Item> COOKED_CRAB = forge("cooked_crab");
	public static final TagKey<Item> CRAB_MEAT = forge("crab_meat");
	public static final TagKey<Item> CRAB_CLAW = forge("crab_claw");
	public static final TagKey<Item> CRAB_CLAW_COOKED = forge("crab_claw/cooked");
	public static final TagKey<Item> COCONUT = forge("coconut");
	public static final TagKey<Item> NUTS = forge("nuts");
	public static final TagKey<Item> NUTS_WALNUT = forge("nuts/walnut");
	public static final TagKey<Item> NUTS_PEANUT = forge("nuts/peanut");
	public static final TagKey<Item> NUTS_ACORN = forge("nuts/acorn");
	public static final TagKey<Item> COOKED_NUTS = forge("cooked_nuts");
	public static final TagKey<Item> SUGAR = forge("sugar");
	public static final TagKey<Item> WATER = forge("water");
	public static final TagKey<Item> TEA_LEAVES = forge("tea_leaves");
	public static final TagKey<Item> TEA_LEAVES_GREEN = forge("tea_leaves/green");
	public static final TagKey<Item> JAMS = forge("jams");
	public static final TagKey<Item> PEANUT_BUTTER = forge("peanut_butter");
	public static final TagKey<Item> NUT_BUTTER = forge("nut_butter");
	public static final TagKey<Item> RAW_RABBIT = forge("raw_rabbit");
	public static final TagKey<Item> COOKED_RABBIT = forge("cooked_rabbit");
	public static final TagKey<Item> RAW_FISHES_KOI = forge("raw_fishes/koi");
	public static final TagKey<Item> RAW_FISHES_TUNA = forge("raw_fishes/tuna");
	public static final TagKey<Item> COOKED_FISHES_TUNA = forge("cooked_fishes/tuna");
	public static final TagKey<Item> RAW_SQUID = forge("raw_squid");
	public static final TagKey<Item> RAW_FISHES_SQUID = forge("raw_fishes/squid");
	public static final TagKey<Item> RAW_FISHES_SQUID_TENTACLES = forge("raw_fishes/squid/tentacles");
	public static final TagKey<Item> COOKED_FISHES_SQUID = forge("cooked_fishes/squid");
	public static final TagKey<Item> RAW_VENISON = forge("raw_venison");
	public static final TagKey<Item> COOKED_VENISON = forge("cooked_venison");
	public static final TagKey<Item> RAW_GOAT = forge("raw_goat");
	public static final TagKey<Item> COOKED_GOAT = forge("cooked_goat");
	public static final TagKey<Item> RAW_DUCK = forge("raw_duck");
	public static final TagKey<Item> COOKED_DUCK = forge("cooked_duck");
	public static final TagKey<Item> FOODS_MEAT = forge("foods/meat");
	public static final TagKey<Item> FOODS_MEAT_RAW = forge("foods/meat/raw");
	public static final TagKey<Item> FOODS_MEAT_COOKED = forge("foods/meat/cooked");
	public static final TagKey<Item> PROTEIN_PATTY = forge("protein_patty");
	public static final TagKey<Item> COOKED_BEEF_OR_VEGAN = forge("cooked_beef_or_vegan");
	public static final TagKey<Item> CATTAIL = forge("cattail");
	public static final TagKey<Item> ICE_CUBES = forge("ice_cubes");
	public static final TagKey<Item> TORTILLA = forge("tortilla");
	public static final TagKey<Item> SYRUP = forge("syrup");
	public static final TagKey<Item> SYRUP_BOTTLE = forge("syrup/syrup_bottle");
	public static final TagKey<Item> LAVENDER = forge("lavender");
	public static final TagKey<Item> ROSEY = forge("rosey");
	public static final TagKey<Item> CLOVER = forge("clover");
	public static final TagKey<Item> CACTI = forge("cacti");
	public static final TagKey<Item> SMALL_CACTI = forge("small_cacti");
	public static final TagKey<Item> MATCHA = forge("matcha");
	public static final TagKey<Item> GEMS_ROSE_QUARTZ = forge("gems/rose_quartz");
	public static final TagKey<Item> GEMS_ZANITE = forge("gems/zanite");
	public static final TagKey<Item> INGOTS_STEEL = ingot("steel");
	public static final TagKey<Item> INGOTS_ZINC = ingot("zinc");
	public static final TagKey<Item> SEEDS_SALMONBERRY = forge("seeds/salmonberry");
	public static final TagKey<Item> SEEDS_CANTALOUPE = forge("seeds/cantaloupe");
	public static final TagKey<Item> BONES = forge("bones");
	public static final TagKey<Item> DOUGH_CORN = forge("dough/corn");
	public static final TagKey<Item> DOUGH_NUT = forge("dough/nut");
	public static final TagKey<Item> BREAD_CORN = forge("bread/corn");
	public static final TagKey<Item> BURGER_BUN = forge("burger_bun");
	public static final TagKey<Item> BREAD_OR_BUN = forge("bread_or_bun");
	public static final TagKey<Item> COOKIES = forge("cookies");
	public static final TagKey<Item> ROPES = forge("ropes");
	public static final TagKey<Item> FEATHERS = forge("feathers");
	public static final TagKey<Item> SNAIL_SHELLS = forge("snail_shells");

	// Forbidden and Arcanus
	public static final TagKey<Item> DRAGON_SCALE = Modid.FA.it("dragon_scale");
	public static final TagKey<Item> DRACO_ARCANUS_STAFF = Modid.FA.it("draco_arcanus_staff");

	// Allthemodium
	public static final TagKey<Item> PLATES_ALLTHEMODIUM = forge("plates/allthemodium");
	public static final TagKey<Item> RODS_ALLTHEMODIUM = forge("rods/allthemodium");

	// Deeper and Darker
	public static final TagKey<Item> REINFORCED_ECHO_SHARD = Modid.DD.it("reinforced_echo_shard");
	public static final TagKey<Item> RESONARIUM = Modid.DD.it("resonarium");
	public static final TagKey<Item> RESONARIUM_PLATE = Modid.DD.it("resonarium_plate");

	// Create
	public static final TagKey<Item> UPRIGHT_ON_BELT = Modid.C.it("upright_on_belt");
	public static final TagKey<Item> POLISHED_ROSE_QUARTZ = Modid.C.it("polished_rose_quartz");

	// MCreator mods
	public static final TagKey<Item> HEAP_EXPERIENCE = Modid.CSA.it("heap_of_experience");
	public static final TagKey<Item> ZINC_HANDLE = Modid.CSA.it("zinc_handle");
	public static final TagKey<Item> SHARP_LEAF = Modid.SE.it("sharp_leaf");
	public static final TagKey<Item> KIWANO_PEEL = Modid.NE.it("kiwano_peel");

	// Phantasm
	public static final TagKey<Item> VOID_CRYSTAL_BLOCK = Modid.EP.it("void_crystal_block");
	public static final TagKey<Item> CRYSTAL_SPIKE_TIPS = Modid.EP.it("crystal_spike_tips");
	public static final TagKey<Item> XP_BOOSTED = Modid.EP.it("gets_xp_speed_boost");

	// Unusual End
	public static final TagKey<Item> INGOTS_PEARLESCENT = ingot("pearlescent");

	// AE2
	public static final TagKey<Item> CERTUS_QUARTZ = gem("certus_quartz");
	public static final TagKey<Item> FLUIX_BLOCK = Modid.AE2.it("fluix_block");

	// Spirit
	public static final TagKey<Item> SOUL_STEEL_INGOT = ingot("soul_steel");
	public static final TagKey<Item> SOUL_STEEL_MAINHAND = Modid.SP.it("soul_steel_mainhand");

	// Botania
	public static final TagKey<Item> LIVINGWOOD_TWIG = Modid.BTA.it("livingwood_twig");
	public static final TagKey<Item> DREAMWOOD_TWIG = Modid.BTA.it("dreamwood_twig");
	public static final TagKey<Item> MANA_ITEMS = Modid.BTA.it("mana_using_items");

	// Additional Additions
	public static final TagKey<Item> ROSE_GOLD_ALLOY = Modid.AA.it("rose_gold_alloy");
	public static final TagKey<Item> GOLD_RING = Modid.AA.it("gold_ring");

	// Aether
	public static final TagKey<Item> HOLYSTONE = Modid.AE.it("holystone");
	public static final TagKey<Item> SKYROOT_STICK = Modid.AE.it("skyroot_stick");
	public static final TagKey<Item> SKYROOT_TOOL_CRAFTING = Modid.AE.it("skyroot_tool_crafting");
	public static final TagKey<Item> ENCHANTED_GRAVITITE = Modid.AE.it("enchanted_gravitite");

	// Aether Redux
	public static final TagKey<Item> INGOTS_GRAVITITE = ingot("gravitite");
	public static final TagKey<Item> INGOTS_VERIDIUM = ingot("veridium");

	// Deep Aether
	public static final TagKey<Item> GEMS_SKYJADE = gem("skyjade");
	public static final TagKey<Item> INGOTS_STRATUS = ingot("stratus");
	public final static TagKey<Item> STRATUS_UPGRADE = Modid.DA.it("stratus_smithing_template");

	public static TagKey<Item> forge(@NotNull String name) {
		return Modid.LOADER.it(name);
	}

	public static TagKey<Item> d(@NotNull String name) {
		return Modid.D.it(name);
	}

	public static TagKey<Item> ingot(@NotNull String name) {
		return forge("ingots/" + name);
	}

	public static Ingredient getIngot(@NotNull String name) {
		return Ingredient.of(ingot(name));
	}

	public static TagKey<Item> gem(@NotNull String name) {
		return forge("gems/" + name);
	}

	public static Ingredient getGem(@NotNull String name) {
		return Ingredient.of(gem(name));
	}
}
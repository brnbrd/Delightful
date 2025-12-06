package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.tag.ForgeTags;

public class DelightfulLanguageProvider extends LanguageProvider {
	public DelightfulLanguageProvider(PackOutput output) {
		super(output, Delightful.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		addKnives();
		addItems();
		addBlocks();
		addTags();
		addOther();
	}

	private void addKnives() {
		DelightfulItems.ITEMS.getEntries().stream()
			.filter(k -> k.get() instanceof DKnifeItem)
			.forEach(k -> addItem(k, ((DKnifeItem) k.get()).getTranslation()));
	}

	private void addItems() {
		addItem(DelightfulItems.CHEESEBURGER, "Cheeseburger");
		addItem(DelightfulItems.DELUXE_CHEESEBURGER, "Deluxe Cheeseburger");
		addItem(DelightfulItems.MARSHMALLOW_STICK, "Marshmallow on a Stick");
		addItem(DelightfulItems.COOKED_MARSHMALLOW_STICK, "Roasted Marshmallow on a Stick");
		addItem(DelightfulItems.SMORE, "S'more");
		addItem(DelightfulItems.CRAB_RANGOON, "Crab Rangoon");
		addItem(DelightfulItems.CHUNKWICH, "Chunkwich");
		addItem(DelightfulItems.JAM_JAR, "Jam Jar");
		addItem(DelightfulItems.GLOW_JAM_JAR, "Glow Jam Jar");
		addItem(DelightfulItems.NUT_BUTTER_BOTTLE, "Nut Butter");
		addItem(DelightfulItems.NUT_BUTTER_AND_JAM_SANDWICH, "Nut Butter and Jam Sandwich");
		addItem(DelightfulItems.HONEY_GLAZED_WALNUT, "Honey Glazed Walnut");
		addItem(DelightfulItems.BERRY_MATCHA_LATTE, "Iced Berry Matcha Latte");
		addItem(DelightfulItems.GREEN_TEA_LEAF, "Green Tea Leaves");
		addItem(DelightfulItems.SALMONBERRIES, "Salmonberries");
		addItem(DelightfulItems.SALMONBERRY_PIPS, "Salmonberry Pips");
		addItem(DelightfulItems.SALMONBERRY_PIE_SLICE, "Slice of Salmonberry Pie");
		addItem(DelightfulItems.PUMPKIN_PIE_SLICE, "Slice of Pumpkin Pie");
		addItem(DelightfulItems.SOURCE_BERRY_PIE_SLICE, "Slice of Source Berry Pie");
		addItem(DelightfulItems.SOURCE_BERRY_COOKIE, "Source Berry Cookie");
		addItem(DelightfulItems.GLOW_JAM_COOKIE, "Glow Jam Cookie");
		addItem(DelightfulItems.GLOOMGOURD_PIE_SLICE, "Slice of Gloomgourd Pie");
		addItem(DelightfulItems.GREEN_APPLE_PIE_SLICE, "Slice of Green Apple Pie");
		addItem(DelightfulItems.BLUEBERRY_PIE_SLICE, "Slice of Blueberry Pie");
		addItem(DelightfulItems.MULBERRY_PIE_SLICE, "Slice of Mulberry Pie");
		addItem(DelightfulItems.PASSION_FRUIT_TART_SLICE, "Slice of Passion Fruit Tart");
		addItem(DelightfulItems.MUTTON_PIE_SLICE, "Slice of Mutton Pie");
		addItem(DelightfulItems.BAKLAVA_SLICE, "Slice of Baklava");
		addItem(DelightfulItems.CHORUS_PIE_SLICE, "Slice of Chorus Pie");
		addItem(DelightfulItems.CHORUS_MUFFIN, "Chorus Muffin");
		addItem(DelightfulItems.CANTALOUPE_SLICE, "Cantaloupe Slice");
		addItem(DelightfulItems.CANTALOUPE_SEEDS, "Cantaloupe Seeds");
		addItem(DelightfulItems.CANTALOUPE_BREAD, "Cantaloupe Bread");
		addItem(DelightfulItems.WRAPPED_CANTALOUPE, "Wrapped Cantaloupe");
		addItem(DelightfulItems.CANTALOUPE_POPSICLE, "Cantaloupe Popsicle");
		addItem(DelightfulItems.STUFFED_CANTALOUPE_BLOCK, "Stuffed Cantaloupe");
		addItem(DelightfulItems.STUFFED_CANTALOUPE, "Bowl of Stuffed Cantaloupe");
		addItem(DelightfulItems.MATCHA, "Matcha");
		addItem(DelightfulItems.MATCHA_ICE_CREAM, "Matcha Ice Cream");
		addItem(DelightfulItems.MATCHA_MILKSHAKE, "Matcha Milkshake");
		addItem(DelightfulItems.SALMONBERRY_ICE_CREAM, "Salmonberry Ice Cream");
		addItem(DelightfulItems.SALMONBERRY_MILKSHAKE, "Salmonberry Milkshake");
		addItem(DelightfulItems.SOURCE_BERRY_ICE_CREAM, "Source Berry Ice Cream");
		addItem(DelightfulItems.SOURCE_BERRY_MILKSHAKE, "Source Berry Milkshake");
		addItem(DelightfulItems.ACORN, "Acorn");
		addItem(DelightfulItems.ROASTED_ACORN, "Roasted Acorn");
		addItem(DelightfulItems.NUT_DOUGH, "Nut Dough");
		addItem(DelightfulItems.ANIMAL_FAT, "Animal Fat");
		addItem(DelightfulItems.ANIMAL_OIL_BOTTLE, "Animal Oil Bottle");
		addItem(DelightfulItems.CHOPPED_CLOVER, "Chopped Clover");
		addItem(DelightfulItems.CACTUS_FLESH, "Cactus Chunk");
		addItem(DelightfulItems.CACTUS_STEAK, "Cactus Steak");
		addItem(DelightfulItems.CACTUS_CHILI, "Cactus Chili");
		addItem(DelightfulItems.CACTUS_SOUP, "Cactus Soup");
		addItem(DelightfulItems.CACTUS_SOUP_CUP, "Cactus Soup Cup");
		addItem(DelightfulItems.VENISON_STEW, "Venison Stew");
		addItem(DelightfulItems.VENISON_STEW_CUP, "Venison Stew Cup");
		addItem(DelightfulItems.FIELD_SALAD, "Field Salad");
		addItem(DelightfulItems.ROCK_CANDY, "Rose Rock Candy");
		addItem(DelightfulItems.VENISON_CHOPS, "Raw Venison Chops");
		addItem(DelightfulItems.COOKED_VENISON_CHOPS, "Cooked Venison Chops");
		addItem(DelightfulItems.RAW_GOAT, "Raw Chevon");
		addItem(DelightfulItems.COOKED_GOAT, "Cooked Chevon");
		addItem(DelightfulItems.COCONUT_CURRY, "Coconut Curry");
		addItem(DelightfulItems.SINIGANG, "Sinigang");
		addItem(DelightfulItems.SINIGANG_CUP, "Sinigang Cup");
		addItem(DelightfulItems.SALMONBERRY_GUMMY, "Salmonberry Gummy");
		addItem(DelightfulItems.MATCHA_GUMMY, "Matcha Gummy");
		addItem(DelightfulItems.CANTALOUPE_GUMMY, "Cantaloupe Gummy");
		addItem(DelightfulItems.SOURCE_BERRY_GUMMY, "Source Berry Gummy");
	}

	private void addBlocks() {
		addBlock(DelightfulBlocks.MINI_MELON, "Mini Melon");
		addBlock(DelightfulBlocks.SLICED_MINI_MELON, "Sliced Mini Melon");
		addBlock(DelightfulBlocks.CANTALOUPE, "Cantaloupe");
		addBlock(DelightfulBlocks.CANTALOUPE_PLANT, "Cantaloupe Plant");
		addBlock(DelightfulBlocks.SLICED_CANTALOUPE, "Sliced Cantaloupe");
		addBlock(DelightfulBlocks.SLICED_MELON, "Sliced Melon");
		addBlock(DelightfulBlocks.SLICED_PUMPKIN, "Sliced Pumpkin");
		addBlock(DelightfulBlocks.SLICED_GLOOMGOURD, "Sliced Gloomgourd");
		addBlock(DelightfulBlocks.BAKLAVA, "Baklava");
		addBlock(DelightfulBlocks.PUMPKIN_PIE, "Pumpkin Pie");
		addBlock(DelightfulBlocks.SOURCE_BERRY_PIE, "Source Berry Pie");
		addBlock(DelightfulBlocks.GLOOMGOURD_PIE, "Gloomgourd Pie");
		addBlock(DelightfulBlocks.GREEN_APPLE_PIE, "Green Apple Pie");
		addBlock(DelightfulBlocks.BLUEBERRY_PIE, "Blueberry Pie");
		addBlock(DelightfulBlocks.CHORUS_PIE, "Chorus Pie");
		addBlock(DelightfulBlocks.MULBERRY_PIE, "Mulberry Pie");
		addBlock(DelightfulBlocks.PASSION_FRUIT_TART, "Passion Fruit Tart");
		addBlock(DelightfulBlocks.MUTTON_PIE, "Mutton Pie");
		addBlock(DelightfulBlocks.SALMONBERRY_PIE, "Salmonberry Pie");
		addBlock(DelightfulBlocks.SALMONBERRY_BUSH, "Salmonberry Bush");
		addBlock(DelightfulBlocks.WILD_SALMONBERRIES, "Wild Salmonberries");
		addBlock(DelightfulBlocks.QUARTZ_CABINET, "Quartz Cabinet");
		addBlock(DelightfulBlocks.BASALT_CABINET, "Basalt Cabinet");
		addBlock(DelightfulBlocks.SALMONBERRY_ICE_CREAM_BLOCK, "Salmonberry Ice Cream Block");
		addBlock(DelightfulBlocks.SALMONBERRY_MILKSHAKE_CAULDRON, "Salmonberry Milkshake Cauldron");
		addBlock(DelightfulBlocks.MATCHA_ICE_CREAM_BLOCK, "Matcha Ice Cream Block");
		addBlock(DelightfulBlocks.MATCHA_MILKSHAKE_CAULDRON, "Matcha Milkshake Cauldron");
		addBlock(DelightfulBlocks.SOURCE_BERRY_ICE_CREAM_BLOCK, "Source Berry Ice Cream Block");
		addBlock(DelightfulBlocks.SOURCE_BERRY_MILKSHAKE_CAULDRON, "Source Berry Milkshake Cauldron");
		addBlock(DelightfulBlocks.ACORN_SACK, "Acorn Sack");
		addBlock(DelightfulBlocks.SALMONBERRY_SACK, "Salmonberry Sack");
		addBlock(DelightfulBlocks.BLUEBERRY_SACK, "Blueberry Sack");
		addBlock(DelightfulBlocks.MENDOSTEEN_CRATE, "Mendosteen Crate");
		addBlock(DelightfulBlocks.BASTION_FRUIT_CRATE, "Bastion Fruit Crate");
		addBlock(DelightfulBlocks.FROSTAYA_CRATE, "Frostaya Crate");
		addBlock(DelightfulBlocks.BOMBEGRANATE_CRATE, "Bombegranate Crate");
		addBlock(DelightfulBlocks.GREEN_APPLE_CRATE, "Green Apple Crate");
		addBlock(DelightfulBlocks.YUCCA_FRUIT_CRATE, "Yucca Fruit Crate");
		addBlock(DelightfulBlocks.BAOBAB_FRUIT_CRATE, "Baobab Fruit Crate");
		addBlock(DelightfulBlocks.GLOW_JAM_COOKIE_TILES, "Glow Jam Cookie Tiles");
		addBlock(DelightfulBlocks.GLOW_JAM_COOKIE_TILE_STAIRS, "Glow Jam Cookie Tile Stairs");
		addBlock(DelightfulBlocks.GLOW_JAM_COOKIE_TILE_SLAB, "Glow Jam Cookie Tile Slab");
		addBlock(DelightfulBlocks.GLOW_JAM_COOKIE_TILE_WALL, "Glow Jam Cookie Tile Wall");
		addBlock(DelightfulBlocks.SOURCE_BERRY_COOKIE_TILES, "Source Berry Cookie Tiles");
		addBlock(DelightfulBlocks.SOURCE_BERRY_COOKIE_TILE_STAIRS, "Source Berry Cookie Tile Stairs");
		addBlock(DelightfulBlocks.SOURCE_BERRY_COOKIE_TILE_SLAB, "Source Berry Cookie Tile Slab");
		addBlock(DelightfulBlocks.SOURCE_BERRY_COOKIE_TILE_WALL, "Source Berry Cookie Tile Wall");
	}

	private void addTags() {
		// Delightful
		addItemTag(DelightfulItemTags.ROASTED_MARSHMALLOWS, "Roasted Marshmallows");
		addItemTag(DelightfulItemTags.HOT_SPICES, "Hot Spices");
		addItemTag(DelightfulItemTags.PATTIES, "Patties");
		addItemTag(DelightfulItemTags.VEGETARIAN_PATTIES, "Vegetarian Patties");

		// Minecraft
		addItemTag(DelightfulItemTags.FLOWERS_AZALEA, "Azalea Flowers");
		addItemTag(DelightfulItemTags.FLOWERS_LAVENDER, "Lavender Flowers");

		// Forge
		addItemTag(DelightfulItemTags.FRUITS, "Fruits");
		addItemTag(DelightfulItemTags.FRUITS_CITRUS, "Citrus Fruits");
		addItemTag(DelightfulItemTags.FRUITS_PRICKLY_PEAR, "Prickly Pears");
		addItemTag(DelightfulItemTags.TEA_LEAVES, "Tea Leaves");
		addItemTag(DelightfulItemTags.TEA_LEAVES_GREEN, "Green Tea Leaves");
		addItemTag(DelightfulItemTags.CHEESE, "Cheeses");
		addItemTag(DelightfulItemTags.JAMS, "Jams");
		addItemTag(DelightfulItemTags.NUTS, "Nuts");
		addItemTag(DelightfulItemTags.COOKED_NUTS, "Cooked Nuts");
		addItemTag(DelightfulItemTags.NUT_BUTTER, "Nut Butters");
		addItemTag(DelightfulItemTags.ICE_CUBES, "Ice Cubes");
		addItemTag(ForgeTags.BREAD, "Breads");
		addItemTag(ForgeTags.DOUGH, "Doughs");
	}

	private void addOther() {
		// Fluids
		addItem(DelightfulItems.AGED_FISH_ROE, "Aged Fish Roe");
		add("fluid_type." + Delightful.MODID + ".aged_fish_roe_type", "Aged Fish Roe");
		addItem(DelightfulItems.AGED_PRAWN_ROE, "Aged Prawn Roe");
		add("fluid_type." + Delightful.MODID + ".aged_prawn_roe_type", "Aged Prawn Roe");
		addItem(DelightfulItems.MATCHA_LATTE, "Matcha Latte");
		add("fluid_type." + Delightful.MODID + ".matcha_latte_type", "Matcha Latte");
		addItem(DelightfulItems.ENDER_NECTAR, "Ender Nectar");
		add("fluid_type." + Delightful.MODID + ".ender_nectar_type", "Ender Nectar");
		addItem(DelightfulItems.AZALEA_TEA, "Azalea Tea");
		add("fluid_type." + Delightful.MODID + ".azalea_tea_type", "Azalea Tea");
		addItem(DelightfulItems.LAVENDER_TEA, "Lavender Tea");
		add("fluid_type." + Delightful.MODID + ".lavender_tea_type", "Lavender Tea");
		addItem(DelightfulItems.PRICKLY_PEAR_JUICE, "Prickly Pear Juice");
		addItem(DelightfulItems.LONG_PRICKLY_PEAR_JUICE, "Prickly Pear Juice");
		add("fluid_type." + Delightful.MODID + ".prickly_pear_juice_type", "Prickly Pear Juice");
		add("fluid_type." + Delightful.MODID + ".long_prickly_pear_juice_type", "Prickly Pear Juice");
		add("fluid_type." + Delightful.MODID + ".eggnog_type", "Eggnog");

		// Other
		add("delightful.overhauls", "Overhauls");

		// Tooltips
		addDelightfulTooltip("sneak_right", "Sneak R-Click for:");
		addDelightfulTooltip("furnace_fuel_burn_time", "s of burn time");
		addDelightfulTooltip("rose_rock_candy.when_feeding", "When fed to a tamed animal:");

		// Tooltips not specific to Delightful
		addTooltip("placeable", "Placeable");
		addTooltip("disabled", "Disabled");
		addTooltip("requires_empty_tag", "Requires empty tag:");
		addTooltip("requires_tag", "Requires tag:");
		addTooltip("requires_modid", "Requires modid:");
		add("farmersdelight.tooltip.ender_nectar", "Otherworldly");

		// JEI Descriptions
		addDescription("matcha", "Green Tea Powder");
		addDescription("green_tea_leaf", "Get lucky using a Knife on any Leaves block.");
		addDescription("animal_fat", "Can be scavenged from some dead animals.");
		addDescription("animal_oil_bottle", "Use on any furnace for a small amount of heat.");
		addDescription("acorn", "Acorns are tough nuts that drop from Oak Leaves or Squirrels.");
		addDescription("salmonberries", "Salmonberries can be commonly found as a wild plant in forests.");
		addDescription("mini_melon", "Mini Melons can be commonly found in plains. Can grow into a big Melon or be sliced with a Knife.");
		addDescription("cantaloupe", "Cantaloupes can be commonly found on beaches.");
		addDescription("cantaloupe_seeds", "Cantaloupe Seeds will sprout when planted in sand.");
		addDescription("sliceable", "Sliceable with a Knife.");

		// Jade
		add("tooltip.jade.age", "Age: %s");
		add("config.jade.plugin_delightful.crop_progress", "Crop Progress");
		add("config.jade.plugin_delightful.mushroom_colony", "Mushroom Colony");
		add("config.jade.plugin_delightful.pie_icons", "Pie Icons");
	}

	public void addItemTag(@NotNull TagKey<?> tag, @NotNull String translation) {
		add(
			"tag.item." + tag.location()
				.toLanguageKey()
				.replace("/", "."),
			translation
		);
	}

	public void addDescription(String key, String value) {
		add("desc." + Delightful.MODID + "." + key, value);
	}

	public void addTooltip(String key, String translation) {
		add("tooltip." + key, translation);
	}

	public void addDelightfulTooltip(String key, String translation) {
		addTooltip(Delightful.MODID + "." + key, translation);
	}
}
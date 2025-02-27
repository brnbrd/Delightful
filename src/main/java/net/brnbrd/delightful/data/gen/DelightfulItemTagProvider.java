package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.compat.abnormals.AquaticCompat;
import net.brnbrd.delightful.compat.BWGCompat;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.compat.UnusualEndCompat;
import net.brnbrd.delightful.compat.abnormals.AtmosphericCompat;
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
		// Minecraft
		this.tag(ItemTags.SMALL_FLOWERS)
			.addTag(DelightfulItemTags.FLOWERS_AZALEA);
		this.tag(DelightfulItemTags.FLOWERS_AZALEA)
			.addOptional(Modid.ECO.rl("azalea_flower"))
			.addOptional(Util.rl("twigs", "azalea_flowers"));
		this.tag(ItemTags.FOX_FOOD)
			.addTag(ForgeTags.BERRIES);
		this.tag(ItemTags.SMALL_FLOWERS)
			.add(DelightfulItems.WILD_SALMONBERRIES.get());
		this.tag(ItemTags.PIGLIN_LOVED).add(Knives.REFINED_GLOWSTONE.get());
		this.tag(ForgeTags.TOOLS_KNIVES)
			.addTag(ModTags.KNIVES)
			.addOptional(Util.rl("occultism", "butcher_knife"));
		var build = tag(ModTags.KNIVES);
		DelightfulItems.ITEMS.getEntries().stream()
			.map(RegistryObject::get)
			.filter(item -> item instanceof DKnifeItem)
			.forEach(build::add);

		// Delightful
		this.tag(DelightfulItemTags.COMPAT_PIES)
			.add(Items.PUMPKIN_PIE)
			.addOptional(Modid.AN.rl("source_berry_pie"))
			.addOptional(Modid.UG.rl("gloomgourd_pie"))
			.addOptional(Modid.BWG.rl(BWGCompat.blueberry_pie))
			.addOptional(Modid.BWG.rl(BWGCompat.green_apple_pie))
			.addOptional(Modid.UE.rl(UnusualEndCompat.chorus_pie))
			.addOptional(Modid.UA.rl(AquaticCompat.mulberry_pie))
			.addOptional(Modid.AT.rl(AtmosphericCompat.passion_fruit_tart));
		this.tag(DelightfulItemTags.FIRE_KNIVES)
			.add(Knives.FIERY.get())
			.add(Knives.KIWANO.get())
			.add(Knives.BLAZING.get());
		this.tag(DelightfulItemTags.STRAW_PLANTS)
			.add(Items.GRASS)
			.add(Items.TALL_GRASS)
			.add(ModItems.SANDY_SHRUB.get())
			.addOptional(Modid.UA.rl("beachgrass"))
			.addOptional(Modid.UA.rl("tall_beachgrass"));
		this.tag(DelightfulItemTags.ROTTEN)
			.add(Items.ROTTEN_FLESH)
			.addOptional(Modid.RL.rl("rotten_chunk"))
			.addOptional(Modid.UG.rl("rotten_blisterberry"));
		this.tag(DelightfulItemTags.MAKES_DYE_RED)
			.addOptionalTag(Modid.BWG.rl("dye/makes_red_dye"))
			.addOptional(Modid.HAB.rl("red_anthurium"))
			.addOptional(Modid.HAB.rl("dreadbud"))
			.addOptional(Modid.HAB.rl("rafflesia"))
			.addOptional(Modid.UG.rl("blood_mushroom"))
			.addOptional(Util.rl("snowyspirit", "ginger_flower"));
		this.tag(DelightfulItemTags.MAKES_DYE_YELLOW)
			.addOptionalTag(Modid.BWG.rl("dye/makes_yellow_dye"))
			.addOptional(Modid.HAB.rl("yellow_anthurium"));
		this.tag(DelightfulItemTags.MAKES_DYE_PURPLE)
			.addOptionalTag(Modid.BWG.rl("dye/makes_purple_dye"))
			.addOptional(Modid.HAB.rl("purple_anthurium"));
		this.tag(DelightfulItemTags.MAKES_DYE_MAGENTA)
			.addOptionalTag(Modid.BWG.rl("dye/makes_magenta_dye"))
			.addOptional(Modid.HAB.rl("purple_anthurium"));
		this.tag(DelightfulItemTags.MAKES_DYE_WHITE)
			.addOptionalTag(Modid.BWG.rl("dye/makes_white_dye"))
			.addOptional(Modid.HAB.rl("white_anthurium"))
			.addOptional(Modid.UG.rl("veil_mushroom"));
		this.tag(DelightfulItemTags.MAKES_DYE_BLUE)
			.addOptionalTag(Modid.BWG.rl("dye/makes_blue_dye"))
			.addOptional(Modid.UG.rl("indigo_mushroom"));
		this.tag(DelightfulItemTags.MAKES_DYE_LIGHT_BLUE)
			.addOptional(Modid.UG.rl("miserabell"));
		this.tag(DelightfulItemTags.MAKES_DYE_BLACK)
			.addOptionalTag(Modid.BWG.rl("dye/makes_black_dye"))
			.addOptional(Modid.UG.rl("ink_mushroom"));
		this.tag(DelightfulItemTags.MAKES_DYE_ORANGE)
			.addOptionalTag(Modid.BWG.rl("dye/makes_orange_dye"));
		this.tag(DelightfulItemTags.MAKES_DYE_PINK)
			.addOptionalTag(Modid.BWG.rl("dye/makes_pink_dye"));
		this.tag(DelightfulItemTags.MAKES_DYE_CYAN)
			.addOptionalTag(Modid.BWG.rl("dye/makes_cyan_dye"));
		this.tag(DelightfulItemTags.MAKES_DYE_GREEN)
			.addOptionalTag(Modid.BWG.rl("dye/makes_green_dye"));
		//this.tag(DelightfulItemTags.MAKES_DYE_LIME);
		this.tag(DelightfulItemTags.MAKES_DYE_LIGHT_GRAY)
			.addOptional(Modid.HAB.rl("edelweiss"));

		// Farmer's Delight
		this.tag(ModTags.WOODEN_CABINETS)
			.add(ModItems.MANGROVE_CABINET.get())
			.addOptional(Util.rl("windswept", "holly_cabinet"))
			.addOptional(Util.rl("windswept", "chestnut_cabinet"))
			.addOptional(Util.rl("fruittrees", "citrus_cabinet"))
			.addOptional(Util.rl("fruittrees", "cherry_cabinet"));
		this.tag(ModTags.CABINETS)
			.addTag(DelightfulItemTags.CABINETS_STONE);
		this.tag(DelightfulItemTags.CABINETS_STONE)
			.add(DelightfulItems.BASALT_CABINET.get())
			.add(DelightfulItems.QUARTZ_CABINET.get());
		this.tag(ModTags.WILD_CROPS_ITEM).add(DelightfulItems.WILD_SALMONBERRIES.get());

		// Tomato
		this.tag(ForgeTags.CROPS_TOMATO).addOptional(Modid.SAS.rl("tomato_slices"));
		this.tag(ForgeTags.VEGETABLES_TOMATO).addTag(ForgeTags.CROPS_TOMATO);

		// Onion
		this.tag(ForgeTags.CROPS_ONION)
			.addOptional(Modid.BWG.rl("oddion_bulb"))
			.addOptional(Modid.SAS.rl("sliced_onion"));
		this.tag(ForgeTags.VEGETABLES_ONION).addTag(ForgeTags.CROPS_ONION);

		// Potato
		this.tag(Tags.Items.CROPS_POTATO)
			.addOptional(Modid.MOD.rl("diced_potatoes"))
			.addOptional(Modid.CAD.rl("potato_slice"));
		this.tag(ForgeTags.VEGETABLES_POTATO).addTag(Tags.Items.CROPS_POTATO);

		// Carrot
		this.tag(Tags.Items.CROPS_CARROT).addOptional(Modid.SAS.rl("chopped_carrot"));
		this.tag(ForgeTags.VEGETABLES_CARROT).addTag(Tags.Items.CROPS_CARROT);

		// Beetroot
		this.tag(Tags.Items.CROPS_BEETROOT).addOptional(Modid.SAS.rl("chopped_beetroot"));
		this.tag(ForgeTags.VEGETABLES_BEETROOT).addTag(Tags.Items.CROPS_BEETROOT);

		this.tag(ModTags.CABBAGE_ROLL_INGREDIENTS)
			.addTag(DelightfulItemTags.RAW_VENISON)
			.addTag(DelightfulItemTags.RAW_GOAT)
			.addOptionalTag(Modid.LOADER.rl("raw_duck"))
			.addOptionalTag(Modid.LOADER.rl("raw_turkey"));

		// Vegetables
		this.tag(DelightfulItemTags.VEGETABLES_SPICY)
			.addOptionalTag(Modid.LOADER.rl("vegetables/ghost_pepper"))
			.addOptionalTag(Modid.LOADER.rl("chilipepper"))
			.addOptionalTag(Modid.LOADER.rl("chile_peppers"))
			.addOptionalTag(Modid.LOADER.rl("crops/chile_pepper"))
			.addOptionalTag(Modid.CR.rl("hot_nether_fruit"))
			.addOptional(Modid.MND.rl("bullet_pepper"));
		this.tag(DelightfulItemTags.VEGETABLES_CORN)
			.addOptionalTag(Modid.LOADER.rl("grain/corn"))
			.addOptional(Modid.CD.rl("corn_cob"))
			.addOptional(Util.rl("hauntedharvest", "corn"))
			.addOptional(Util.rl("corn_delight", "corn"));
		this.tag(DelightfulItemTags.VEGETABLES_GINGER).addOptional(Util.rl("snowyspirit", "ginger"));
		this.tag(DelightfulItemTags.VEGETABLES_CUCUMBER).addOptional(Modid.CD.rl("cucumber"));
		this.tag(DelightfulItemTags.CUCUMBER).addTag(DelightfulItemTags.VEGETABLES_CUCUMBER);

		// Fruits
		this.tag(DelightfulItemTags.FRUITS_APPLE).add(Items.APPLE);
		this.tag(DelightfulItemTags.FRUITS_MELON).add(Items.MELON_SLICE);
		this.tag(DelightfulItemTags.FRUITS_CHORUS).add(Items.CHORUS_FRUIT);
		this.tag(DelightfulItemTags.FRUITS_SWEET_BERRIES).add(Items.SWEET_BERRIES);
		this.tag(DelightfulItemTags.FRUITS_GLOW_BERRIES).add(Items.GLOW_BERRIES);
		this.tag(DelightfulItemTags.FRUITS_CANTALOUPE).add(DelightfulItems.CANTALOUPE_SLICE.get());
		this.tag(DelightfulItemTags.FRUITS_SALMONBERRIES).add(DelightfulItems.SALMONBERRIES.get());
		this.tag(DelightfulItemTags.FRUITS_KIWI).addOptional(Util.rl("hedgehog", "kiwi"));
		this.tag(DelightfulItemTags.FRUITS_GREEN_APPLE).addOptional(Modid.BWG.rl(BWGCompat.green_apple));
		this.tag(DelightfulItemTags.FRUITS_YUCCA)
			.addOptional(Modid.BWG.rl(BWGCompat.yucca))
			.addOptionalTag(Util.rl("atmospheric", "yucca_fruit"));
		this.tag(DelightfulItemTags.FRUITS_BAOBAB).addOptional(Modid.BWG.rl(BWGCompat.baobab));
		this.tag(DelightfulItemTags.FRUITS_PRICKLY_PEAR).addOptional(Modid.ECO.rl("prickly_pear"));
		this.tag(DelightfulItemTags.FRUITS_TORCHBERRIES).addOptional(Modid.TF.rl("torchberries"));
		this.tag(DelightfulItemTags.FRUITS_SOURCEBERRY).addOptional(Modid.AN.rl("sourceberry_bush"));
		this.tag(DelightfulItemTags.FRUITS_ELDERBERRY).addOptional(Modid.RC.rl("elderberry"));
		this.tag(DelightfulItemTags.FRUITS_BLACKCURRANT)
			.addOptional(Modid.RC.rl("blackcurrant"))
			.addOptionalTag(Util.rl("atmospheric", "currant"));
		this.tag(DelightfulItemTags.FRUITS_REDCURRANT).addOptional(Modid.RC.rl("redcurrant"));
		this.tag(DelightfulItemTags.FRUITS_WHITECURRANT).addOptional(Modid.RC.rl("whitecurrant"));
		this.tag(DelightfulItemTags.FRUITS_GEARO_BERRY).addOptional(Modid.VD.rl("gearo_berry"));
		this.tag(DelightfulItemTags.FRUITS_BLUEBERRIES)
			.addOptional(Modid.BWG.rl("blueberries"))
			.addOptional(Modid.WB.rl("blueberries"))
			.addOptional(Modid.NF.rl("blueberries"));
		this.tag(DelightfulItemTags.FRUITS_RASPBERRIES).addOptional(Modid.WB.rl("raspberry"));
		this.tag(DelightfulItemTags.FRUITS_BLACKBERRIES).addOptional(Modid.WB.rl("blackberry"));
		this.tag(DelightfulItemTags.FRUITS_CRANBERRIES).addOptional(Modid.WB.rl("cranberries"));
		this.tag(DelightfulItemTags.FRUITS_WILD_BERRIES).addOptional(Util.rl("windswept", "wild_berries"));
		this.tag(DelightfulItemTags.FRUITS_STRAWBERRIES)
				.addOptional(Modid.N.rl("strawberries"))
				.addOptional(Modid.N.rl("white_strawberries"));
		this.tag(DelightfulItemTags.FRUITS_BANANA).addOptional(Modid.N.rl("banana"));
		this.tag(DelightfulItemTags.FRUITS_CHERRY).addOptional(Modid.FA.rl("cherry_peach"));
		this.tag(DelightfulItemTags.FRUITS_PLUM).addOptional(Modid.ENV.rl("plum"));
		this.tag(DelightfulItemTags.FRUITS).replace(false)
			.addTag(ForgeTags.BERRIES)
			.addTag(DelightfulItemTags.FRUITS_SWEET)
			.addTag(DelightfulItemTags.FRUITS_CITRUS)
			.addTag(DelightfulItemTags.FRUITS_CHORUS)
			.addTag(DelightfulItemTags.FRUITS_GREEN_APPLE)
			.addTag(DelightfulItemTags.FRUITS_YUCCA)
			.addOptional(Modid.AN.rl("mendosteen_pod"))
			.addOptional(Modid.AN.rl("bombegranate_pod"))
			.addOptional(Modid.AN.rl("frostaya_pod"))
			.addOptional(Modid.AN.rl("bastion_pod"))
			.addOptional(Modid.UG.rl("droopvine_item"))
			.addOptional(Modid.EP.rl("oblifruit"));
		this.tag(ForgeTags.BERRIES).replace(false)
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
			.addTag(DelightfulItemTags.FRUITS_CHERRY)
			.addTag(DelightfulItemTags.FRUITS_STRAWBERRIES)
			.addTag(DelightfulItemTags.FRUITS_WILD_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_GEARO_BERRY)
			.addOptionalTag(Modid.LOADER.rl("fruits/mulberry"))
			.addOptionalTag(Modid.LOADER.rl("fruits/hawberry"))
			.addOptionalTag(Modid.LOADER.rl("fruits/bayberry"))
			.addOptional(Modid.FRIGHT.rl("soul_berry"))
			.addOptional(Modid.FRIGHT.rl("wither_berry"))
			.addOptional(Modid.UG.rl("blisterberry"))
			.addOptional(Modid.DD.rl("bloom_berries"))
			.addOptional(Modid.EN.rl("zure_berry"))
			.addOptional(Modid.EP.rl("pream_berry"))
			.addOptional(Modid.UE.rl("warped_berries"))
			.addOptional(Modid.AE.rl("blue_berry"))
			.addOptional(Modid.AER.rl("zanberry"))
			.addOptional(Modid.DA.rl("goldenleaf_berries"));
		this.tag(DelightfulItemTags.FRUITS_CITRUS).replace(false)
			.addTag(DelightfulItemTags.FRUITS_CITRON)
			.addOptionalTag(DelightfulItemTags.FRUITS_MANDARIN.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_ORANGE.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_LEMON.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_LIME.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_GRAPEFRUIT.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_POMELO.location());
		this.tag(DelightfulItemTags.FRUITS_SWEET).replace(false)
			.addTag(DelightfulItemTags.FRUITS_APPLE)
			.addTag(DelightfulItemTags.FRUITS_MELON)
			.addTag(DelightfulItemTags.FRUITS_SWEET_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.addTag(DelightfulItemTags.FRUITS_CANTALOUPE)
			.addTag(DelightfulItemTags.FRUITS_KIWI)
			.addTag(DelightfulItemTags.FRUITS_PRICKLY_PEAR)
			.addTag(DelightfulItemTags.FRUITS_BANANA)
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
			.addTag(DelightfulItemTags.FRUITS_BAOBAB)
			.addOptionalTag(DelightfulItemTags.FRUITS_ORANGE.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_MANDARIN.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_PASSION_FRUIT.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_DRAGON_FRUIT.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_PLUM.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_POMEGRANATE.location())
			.addOptionalTag(Modid.LOADER.rl("fruits/redlove"))
			.addOptional(Modid.FRD.rl("hamimelon_slice"));

		// Crab
		this.tag(DelightfulItemTags.RAW_CRAB) // Whole Crab
			.addOptional(Modid.CRAB.rl("crab"))
			.addOptionalTag(Util.rl("finsandtails", "spindly_gem_crabs"));
		this.tag(DelightfulItemTags.COOKED_CRAB) // Whole Crab
			.addOptional(Modid.CRAB.rl("cooked_crab"));
		this.tag(DelightfulItemTags.CRAB_MEAT)
			.addOptional(Modid.ECO.rl("crab_meat"));
		this.tag(DelightfulItemTags.CRAB_CLAW_COOKED)
			.addOptional(Modid.CR.rl("chieftain_claw"))
			.addOptional(Util.rl("finsandtails", "cooked_bull_crab_claw"));
		this.tag(DelightfulItemTags.CRAB_CLAW)
			.addTag(DelightfulItemTags.CRAB_CLAW_COOKED)
			.addOptional(Modid.CRAB.rl("crab_claw"))
			.addOptional(Modid.ECO.rl("crab_claw"))
			.addOptional(Util.rl("finsandtails", "red_bull_crab_claw"))
			.addOptional(Util.rl("finsandtails", "white_bull_crab_claw"));

		// Forge
		this.tag(DelightfulItemTags.LAVENDER)
			.addOptionalTag(Modid.LOADER.rl("flowers/lavender"))
			.addOptional(Modid.BOP.rl("lavender"))
			.addOptional(Modid.BOP.rl("tall_lavender"));
		this.tag(DelightfulItemTags.ROSEY)
			.addOptional(Modid.BWG.rl("rose"))
			.addOptional(Modid.FR.rl("rose_hips"))
			.addOptional(Util.rl("sunflowerdelight", "rosebud"));
		this.tag(DelightfulItemTags.CLOVER)
			.addOptional(Modid.BOP.rl("clover"))
			.addOptional(Modid.BWG.rl("clover_patch"))
			.addOptional(Modid.TF.rl("clover_patch"))
			.addOptional(Modid.BB.rl("four_leaf_clover"));
		this.tag(DelightfulItemTags.CACTI)
			.add(Items.CACTUS)
			.addOptional(Modid.BWG.rl("barrel_cactus"))
			.addOptional(Modid.BWG.rl("flowering_barrel_cactus"))
			.addOptional(Modid.BWG.rl("prickly_pear_cactus"))
			.addOptional(Modid.BWG.rl("golden_spined_cactus"))
			.addOptional(Util.rl("biomemakeover", "saguaro_cactus"));
		this.tag(DelightfulItemTags.SMALL_CACTI)
			.addOptionalTag(Modid.HAB.rl("ball_cacti"))
			.addOptional(Modid.BWG.rl("mini_cactus"))
			.addOptional(Modid.BOP.rl("tiny_cactus"))
			.addOptional(Util.rl("atmospheric", "barrel_cactus"))
			.addOptional(Util.rl("biomemakeover", "barrel_cactus"))
			.addOptional(Util.rl("naturesaura", "aura_cactus"));
		this.tag(DelightfulItemTags.MATCHA).add(DelightfulItems.MATCHA.get());
		this.tag(Tags.Items.EGGS)
			.add(Items.TURTLE_EGG)
			.addOptional(Modid.SM.rl("tortoise_egg"))
			.addOptional(Modid.NA.rl("tortoise_egg"))
			.addOptional(Modid.NA.rl("duck_egg"))
			.addOptional(Modid.Q.rl("egg_parrot_red_blue"))
			.addOptional(Modid.Q.rl("egg_parrot_blue"))
			.addOptional(Modid.Q.rl("egg_parrot_green"))
			.addOptional(Modid.Q.rl("egg_parrot_yellow_blue"))
			.addOptional(Modid.Q.rl("egg_parrot_gray"))
			.addOptional(Util.rl("autumnity", "turkey_egg"))
			.addOptional(Modid.DA.rl("quail_egg"))
			.addOptional(Util.rl("farmlife", "galliraptor_egg"))
			.addOptional(Util.rl("etcetera", "eggple"));
		this.tag(ForgeTags.COOKED_EGGS)
			.addOptional(Modid.AA.rl("fried_egg"))
			.addOptional(Modid.IN.rl("fried_egg"))
			.addOptional(Modid.NA.rl("cooked_egg"));
		this.tag(DelightfulItemTags.DOUGH_CORN).addOptional(Modid.CD.rl("corn_dough"));
		this.tag(DelightfulItemTags.DOUGH_NUT).add(DelightfulItems.NUT_DOUGH.get());
		this.tag(ForgeTags.DOUGH)
			.addTag(DelightfulItemTags.DOUGH_NUT)
			.addTag(DelightfulItemTags.DOUGH_CORN);
		this.tag(DelightfulItemTags.BREAD_CORN)
			.addOptional(Util.rl("corn_delight", "cornbread"))
			.addOptional(Util.rl("hauntedharvest", "cornbread"));
		this.tag(ForgeTags.BREAD).addTag(DelightfulItemTags.BREAD_CORN);
		this.tag(DelightfulItemTags.BURGER_BUN).addOptional(Modid.SAS.rl("burger_bun"));
		this.tag(DelightfulItemTags.BREAD_OR_BUN)
			.addTag(ForgeTags.BREAD)
			.addTag(DelightfulItemTags.BURGER_BUN);
		this.tag(DelightfulItemTags.HOT_SPICE)
			.add(Items.BLAZE_POWDER)
			.addTag(DelightfulItemTags.VEGETABLES_SPICY)
			.addOptionalTag(Modid.MND.rl("hot_spice"));
		this.tag(DelightfulItemTags.PUMPKINS_CARVED)
			.add(Items.CARVED_PUMPKIN)
			.addOptional(Util.rl("autumnity", "carved_large_pumpkin_slice"));
		this.tag(DelightfulItemTags.PUMPKINS)
			.addTag(DelightfulItemTags.PUMPKINS_CARVED)
			.add(Items.PUMPKIN)
			.addOptional(Util.rl("autumnity", "large_pumpkin_slice"));
		this.tag(DelightfulItemTags.COCONUT).addOptional(Modid.ECO.rl("coconut_slice"));
		this.tag(DelightfulItemTags.NUTS_WALNUT)
			.addOptional(Modid.ECO.rl("walnut"))
			.addOptional(Util.rl("caupona", "walnut"));
		this.tag(DelightfulItemTags.NUTS_PEANUT)
			.addOptionalTag(Modid.LOADER.rl("peanut"))
			.addOptional(Util.rl("sprout", "peanut"));
		this.tag(DelightfulItemTags.NUTS_ACORN)
			.add(DelightfulItems.ACORN.get());
		this.tag(DelightfulItemTags.NUTS)
			.addTag(DelightfulItemTags.NUTS_ACORN)
			.addTag(DelightfulItemTags.NUTS_WALNUT)
			.addTag(DelightfulItemTags.NUTS_PEANUT)
			.addOptional(Util.rl("windswept", "chestnuts"))
			.addOptional(Util.rl("alexscaves", "pine_nuts"));
		this.tag(DelightfulItemTags.COOKED_NUTS)
			.add(DelightfulItems.ROASTED_ACORN.get())
			.addOptional(Modid.VD.rl("roasted_peanut"));
		this.tag(DelightfulItemTags.INGOTS_STEEL).addOptional(Util.rl("simplysteel", "steel_ingot"));
		this.tag(DelightfulItemTags.WATER).add(Items.WATER_BUCKET);
		this.tag(DelightfulItemTags.JAMS)
			.add(DelightfulItems.JAM_JAR.get())
			.add(DelightfulItems.GLOW_JAM_JAR.get())
			.addOptionalTag(Modid.LOADER.rl("jam"))
			.addOptionalTag(Modid.LOADER.rl("jam_bottles"))
			.addOptionalTag(Modid.WB.rl("berry_jams"))
			.addOptionalTag(Modid.VD.rl("sweet_jam_bottles"))
			.addOptional(Modid.BC.rl("sweet_berry_jam"))
			.addOptional(Modid.BC.rl("glow_berry_marmalade"))
			.addOptional(Modid.BC.rl("apple_jelly"))
			.addOptional(Modid.UA.rl("mulberry_jam_bottle"));
		this.tag(DelightfulItemTags.PEANUT_BUTTER)
			.addOptional(Modid.TH.rl("peanut_butter"))
			.addOptional(Modid.CT.rl("peanut_butter"));
		this.tag(DelightfulItemTags.NUT_BUTTER)
			.addTag(DelightfulItemTags.PEANUT_BUTTER)
			.add(DelightfulItems.NUT_BUTTER_BOTTLE.get())
			.addOptional(Modid.VD.rl("nut_mash_bottle"));
		this.tag(DelightfulItemTags.SUGAR).add(Items.SUGAR);
		this.tag(DelightfulItemTags.CHEESE)
			.addOptionalTag(Modid.LOADER.rl("cheeses"))
			.addOptional(Modid.BC.rl("flaxen_cheese_wedge"))
			.addOptional(Modid.CT.rl("cheese"))
			.addOptional(Modid.TH.rl("cheese_wedge"))
			.addOptional(Modid.CAD.rl("cheese_wheel_slice"))
			.addOptional(Util.rl("farmlife", "tribull_cheese_wedge"))
			.addOptional(Util.rl("ad_astra", "cheese"));
		this.tag(ForgeTags.MILK)
			.addOptional(Modid.AE.rl("skyroot_milk_bucket"))
			.addOptional(Util.rl("farmlife", "tribull_milk"))
			.addOptional(Util.rl("dracovitadelight", "tribull_milk"))
			.addOptional(Util.rl("blue_skies", "ventium_milk_bucket"))
			.addOptional(Util.rl("forbidden_arcanus", "edelwood_milk_bucket"));

		this.tag(DelightfulItemTags.TEA_LEAVES_GREEN)
			.add(DelightfulItems.GREEN_TEA_LEAF.get())
			.addOptional(Modid.FR.rl("green_tea_leaves"))
			.addOptional(Modid.TH.rl("tea"))
			.addOptional(Modid.CT.rl("tea_leaves"));
		this.tag(DelightfulItemTags.TEA_LEAVES)
			.addTag(DelightfulItemTags.TEA_LEAVES_GREEN)
			.addOptionalTag(Modid.FR.rl("tea_leaves"));
		this.tag(DelightfulItemTags.RAW_RABBIT).add(Items.RABBIT);
		this.tag(DelightfulItemTags.COOKED_RABBIT).add(Items.COOKED_RABBIT);
		this.tag(DelightfulItemTags.RAW_FISHES_KOI)
			.addOptional(Modid.ENV.rl("koi"))
			.addOptional(Util.rl("crittersandcompanions", "koi_fish"));
		this.tag(DelightfulItemTags.RAW_FISHES_TUNA)
			.addOptional(Util.rl("spawn", "tuna_chunk"));
		this.tag(DelightfulItemTags.COOKED_FISHES_TUNA)
			.addOptional(Util.rl("spawn", "cooked_tuna_chunk"));
		this.tag(DelightfulItemTags.RAW_SQUID)
			.addOptional(Modid.MD.rl("squid"))
			.addOptional(Modid.MD.rl("glow_squid"))
			.addOptional(Modid.CD.rl("squid"))
			.addOptional(Modid.CD.rl("glow_squid"));
		this.tag(DelightfulItemTags.RAW_FISHES_SQUID_TENTACLES)
			.addOptional(Modid.MD.rl("tentacles"))
			.addOptional(Modid.CD.rl("raw_calamari"));
		this.tag(DelightfulItemTags.RAW_FISHES_SQUID)
			.addTag(DelightfulItemTags.RAW_FISHES_SQUID_TENTACLES)
			.addTag(DelightfulItemTags.RAW_SQUID);
		this.tag(DelightfulItemTags.COOKED_FISHES_SQUID)
			.addOptional(Modid.CD.rl("cooked_squid"))
			.addOptional(Modid.CD.rl("cooked_calamari"));
		this.tag(ForgeTags.RAW_FISHES)
			.addTag(DelightfulItemTags.RAW_FISHES_KOI)
			.addTag(DelightfulItemTags.RAW_FISHES_TUNA)
			.addTag(DelightfulItemTags.RAW_FISHES_SQUID)
			.addOptional(Modid.UG.rl("raw_gwibling"))
			.addOptional(Util.rl("biomemakeover", "glowfish"))
			.addOptional(Modid.DA.rl("raw_aerglow_fish"))
			.addOptional(Util.rl("spawn", "angler_fish"))
			.addOptional(Util.rl("bettas", "betta_fish"));
		this.tag(ForgeTags.COOKED_FISHES)
			.addTag(DelightfulItemTags.COOKED_FISHES_TUNA)
			.addTag(DelightfulItemTags.COOKED_FISHES_SQUID)
			.addOptional(Modid.UG.rl("cooked_gwibling"))
			.addOptional(Util.rl("biomemakeover", "cooked_glowfish"))
			.addOptional(Modid.DA.rl("cooked_aerglow_fish"));
		this.tag(DelightfulItemTags.RAW_VENISON_COMPAT)
			.addOptional(Modid.ENV.rl("venison"))
			.addOptional(Modid.NA.rl("venison"))
			.addOptional(Modid.TF.rl("raw_venison"))
			.addOptional(Util.rl("goodall", "raw_venison"))
			.addOptional(Util.rl("blue_skies", "venison"));
		this.tag(DelightfulItemTags.RAW_VENISON_CHOP_COMPAT)
			.addOptional(Modid.AD.rl("venison_shanks"))
			.addOptional(Modid.TFD.rl("raw_venison_rib"));
		this.tag(DelightfulItemTags.RAW_VENISON)
			.add(DelightfulItems.VENISON_CHOPS.get())
			.addTag(DelightfulItemTags.RAW_VENISON_CHOP_COMPAT)
			.addTag(DelightfulItemTags.RAW_VENISON_COMPAT);
		this.tag(DelightfulItemTags.COOKED_VENISON_COMPAT)
			.addOptional(Modid.ENV.rl("cooked_venison"))
			.addOptional(Modid.NA.rl("cooked_venison"))
			.addOptional(Modid.TF.rl("cooked_venison"))
			.addOptional(Util.rl("goodall", "cooked_venison"))
			.addOptional(Util.rl("blue_skies", "cooked_venison"));
		this.tag(DelightfulItemTags.COOKED_VENISON_CHOP_COMPAT)
			.addOptional(Modid.AD.rl("cooked_venison_shanks"))
			.addOptional(Modid.TFD.rl("cooked_venison_rib"));
		this.tag(DelightfulItemTags.COOKED_VENISON)
			.add(DelightfulItems.COOKED_VENISON_CHOPS.get())
			.addTag(DelightfulItemTags.COOKED_VENISON_CHOP_COMPAT)
			.addTag(DelightfulItemTags.COOKED_VENISON_COMPAT);
		this.tag(DelightfulItemTags.RAW_GOAT).add(DelightfulItems.RAW_GOAT.get());
		this.tag(DelightfulItemTags.COOKED_GOAT).add(DelightfulItems.COOKED_GOAT.get());
		this.tag(DelightfulItemTags.RAW_DUCK).addOptional(Modid.NA.rl("duck"));
		this.tag(DelightfulItemTags.COOKED_DUCK).addOptional(Modid.NA.rl("cooked_duck"));
		this.tag(DelightfulItemTags.FOODS_MEAT_RAW)
			.addOptionalTag(Modid.LOADER.rl("raw_meat"))
			.addTag(ForgeTags.RAW_FISHES)
			.addTag(ForgeTags.RAW_CHICKEN)
			.addTag(ForgeTags.RAW_BEEF)
			.addTag(ForgeTags.RAW_PORK)
			.addTag(ForgeTags.RAW_MUTTON)
			.addTag(DelightfulItemTags.RAW_RABBIT)
			.addTag(DelightfulItemTags.RAW_VENISON)
			.addTag(DelightfulItemTags.RAW_CRAB)
			.addTag(DelightfulItemTags.RAW_GOAT)
			.addOptionalTag(Modid.LOADER.rl("raw_duck"))
			.addOptionalTag(Modid.LOADER.rl("raw_turkey"))
			.addOptional(Modid.CAD.rl("raw_donkey_meat"))
			.addOptional(Modid.UG.rl("raw_dweller_meat"))
			.addOptional(Modid.UG.rl("raw_gloomper_leg"))
			.addOptional(Modid.EP.rl("behemoth_meat"))
			.addOptional(Util.rl("frog_legs", "frog_legs"));
		this.tag(DelightfulItemTags.FOODS_MEAT_COOKED)
			.addOptionalTag(Modid.LOADER.rl("cooked_meat"))
			.addTag(ForgeTags.COOKED_FISHES)
			.addTag(ForgeTags.COOKED_CHICKEN)
			.addTag(ForgeTags.COOKED_BEEF)
			.addTag(ForgeTags.COOKED_PORK)
			.addTag(ForgeTags.COOKED_MUTTON)
			.addTag(DelightfulItemTags.COOKED_RABBIT)
			.addTag(DelightfulItemTags.COOKED_VENISON)
			.addTag(DelightfulItemTags.COOKED_CRAB)
			.addTag(DelightfulItemTags.COOKED_GOAT)
			.addOptionalTag(Modid.LOADER.rl("cooked_duck"))
			.addOptionalTag(Modid.LOADER.rl("cooked_turkey"))
			.addOptional(Modid.CAD.rl("cooked_donkey_meat"))
			.addOptional(Modid.UG.rl("dweller_steak"))
			.addOptional(Modid.UG.rl("gloomper_leg"))
			.addOptional(Modid.EP.rl("behemoth_steak"))
			.addOptional(Util.rl("frog_legs", "cooked_frog_legs"));
		this.tag(DelightfulItemTags.FOODS_MEAT)
			.addTag(DelightfulItemTags.FOODS_MEAT_RAW)
			.addTag(DelightfulItemTags.FOODS_MEAT_COOKED)
			.addTag(DelightfulItemTags.CRAB_MEAT);
		this.tag(DelightfulItemTags.PROTEIN_PATTY)
			.add(ModItems.BEEF_PATTY.get())
			.addOptional(Modid.MD.rl("vegan_patty"));
		this.tag(DelightfulItemTags.COOKED_BEEF_OR_VEGAN)
			.addTag(ForgeTags.COOKED_BEEF)
			.addTag(DelightfulItemTags.PROTEIN_PATTY);
		this.tag(DelightfulItemTags.CATTAIL)
			.addOptional(Util.rl("sprout", "cattail"))
			.addOptional(Modid.BOP.rl("cattail"))
			.addOptional(Util.rl("biomemakeover", "cattail"));
		this.tag(DelightfulItemTags.GEMS_ROSE_QUARTZ)
			.addOptional(Modid.BOP.rl("rose_quartz_chunk"))
			.addOptional(Modid.C.rl("rose_quartz"));
		this.tag(DelightfulItemTags.GEMS_ZANITE).addOptional(Modid.AE.rl("zanite_gemstone"));
		this.tag(DelightfulItemTags.CHOCOLATE)
			.addOptionalTag(Modid.LOADER.rl("chocolates"))
			.addOptionalTag(Modid.LOADER.rl("bars/chocolate"))
			.addOptionalTag(Modid.LOADER.rl("chocolatebar"))
			.addOptional(Modid.N.rl("chocolate_bar"))
			.addOptional(Modid.C.rl("bar_of_chocolate"));
		this.tag(ForgeTags.SALAD_INGREDIENTS)
			.add(DelightfulItems.CHOPPED_CLOVER.get())
			.addOptional(Util.rl("babyfat", "water_lettuce"));
		this.tag(DelightfulItemTags.SEEDS_SALMONBERRY).add(DelightfulItems.SALMONBERRY_PIPS.get());
		this.tag(DelightfulItemTags.SEEDS_CANTALOUPE).add(DelightfulItems.CANTALOUPE_SEEDS.get());
		this.tag(ForgeTags.SEEDS)
			.addTag(DelightfulItemTags.SEEDS_SALMONBERRY)
			.addTag(DelightfulItemTags.SEEDS_CANTALOUPE)
			.addOptional(Modid.FR.rl("tea_seeds"));
		this.tag(DelightfulItemTags.TORTILLA).addOptional(Modid.CD.rl("tortilla"));
		this.tag(Tags.Items.SEEDS)
			.addOptional(Modid.VD.rl("oat_seeds"))
			.addOptional(Modid.VD.rl("ghost_pepper_seeds"))
			.addOptional(Modid.VD.rl("cucumber_seeds"))
			.addOptional(Modid.FRD.rl("lemon_seeds"))
			.addOptional(Modid.FRD.rl("hamimelon_seeds"));
		this.tag(DelightfulItemTags.BONES)
			.addOptional(Modid.DD.rl("sculk_bone"))
			.addOptional(Util.rl("alexscaves", "heavy_bone"))
			.addOptional(Modid.UA.rl("thrasher_tooth"))
			.addOptional(Modid.SM.rl("piranha_tooth"));
		this.tag(DelightfulItemTags.SYRUP)
			.addOptionalTag(Modid.SUP.rl("pancake_syrup"))
			.addOptional(Util.rl("autumnity", "syrup_bottle"));
		this.tag(DelightfulItemTags.COOKIES)
			.add(Items.COOKIE)
			.add(ModItems.HONEY_COOKIE.get())
			.add(ModItems.SWEET_BERRY_COOKIE.get())
			.add(DelightfulItems.SOURCE_BERRY_COOKIE.get())
			.addOptional(Modid.FR.rl("green_tea_cookie"))
			.addOptional(Modid.AD.rl("mulberry_cookie"))
			.addOptional(Modid.AD.rl("maple_cookie"))
			.addOptional(Modid.AD.rl("cherry_cookie"))
			.addOptional(Modid.MD.rl("bat_cookie"))
			.addOptional(Modid.VD.rl("oatmeal_cookie"))
			.addOptional(Modid.FRD.rl("persimmon_cookie"))
			.addOptional(Modid.FRD.rl("lemon_cookie"))
			.addOptional(Modid.FRD.rl("cranberry_cookie"))
			.addOptional(Modid.FRD.rl("bayberry_cookie"))
			.addOptional(Modid.FRIGHT.rl("cookie_flesh"))
			.addOptional(Modid.FRIGHT.rl("cookie_spidereye"))
			.addOptional(Modid.FRIGHT.rl("cookie_slimeapple"))
			.addOptional(Modid.FRIGHT.rl("cookie_slime"))
			.addOptional(Modid.FRIGHT.rl("cookie_cobweb"))
			.addOptional(Modid.FRIGHT.rl("cookie_ghast_tear"))
			.addOptional(Modid.FRIGHT.rl("cookie_soul_berry"))
			.addOptional(Modid.FRIGHT.rl("cookie_wither_berry"))
			.addOptional(Util.rl("ends_delight", "chorus_cookie"))
			.addOptional(Util.rl("exquisito", "chorus_cookie"))
			.addOptional(Util.rl("sunflowerdelight", "shortbread_cookie"))
			.addOptional(Util.rl("vampiresdelight", "orchid_cookie"))
			.addOptional(Util.rl("snowyspirit", "gingerbread_cookie"));
		this.tag(Modid.SUP.it("cookies"))
			.addTag(DelightfulItemTags.COOKIES)
			.addOptional(Modid.CD.rl("tortilla_chips"))
			.addOptional(Util.rl("corn_delight", "tortilla_chip"));
		this.tag(DelightfulItemTags.ROPES)
			.add(ModItems.ROPE.get())
			.addOptionalTag(Modid.SUP.rl("ropes"))
			.addOptional(Modid.AA.rl("rope"));
		this.tag(DelightfulItemTags.FEATHERS).addOptional(Modid.ECO.rl("penguin_feather"));
		this.tag(DelightfulItemTags.SNAIL_SHELLS)
			.addOptional(Modid.NA.rl("snail_shell"))
			.addOptional(Util.rl("autumnity", "snail_shell_piece"));

		// Collector's Reap
		this.tag(Modid.CR.it("gummies"))
			.add(DelightfulItems.SALMONBERRY_GUMMY.get())
			.add(DelightfulItems.MATCHA_GUMMY.get())
			.add(DelightfulItems.CANTALOUPE_GUMMY.get())
			.add(DelightfulItems.SOURCE_BERRY_GUMMY.get());

		// Nether's Delight
		this.tag(Modid.ND.it("meal_item"))
			.add(DelightfulItems.STUFFED_CANTALOUPE.get())
			.addOptional(Modid.MD.rl("bowl_of_stuffed_squid"));

		// My Nether's Delight
		this.tag(Modid.MND.it("curry_meats"))
			.addTag(DelightfulItemTags.RAW_VENISON)
			.addTag(DelightfulItemTags.RAW_GOAT);

		// Ecologics
		this.addSelf(Modid.ECO.it("cooked_prickly_pear"));

		// Forbidden and Arcanus
		this.addSelf(DelightfulItemTags.DRACO_ARCANUS_STAFF);
		this.addSelf(DelightfulItemTags.DRAGON_SCALE);

		// Deeper and Darker
		this.addSelf(DelightfulItemTags.REINFORCED_ECHO_SHARD);
		this.addSelf(DelightfulItemTags.RESONARIUM);
		this.addSelf(DelightfulItemTags.RESONARIUM_PLATE);

		// Create
		this.addSelf(DelightfulItemTags.POLISHED_ROSE_QUARTZ);
		this.addSelf(DelightfulItemTags.ZINC_HANDLE);
		this.addSelf(DelightfulItemTags.HEAP_EXPERIENCE);

		// Seeds
		this.addSelf(DelightfulItemTags.SHARP_LEAF);

		// Nether's Exoticism
		this.addSelf(DelightfulItemTags.KIWANO_PEEL);
		this.tag(DelightfulItemTags.FRUITS_CITRON).addOptional(Modid.NE.rl("bouddha_s_hand"));
		this.tag(DelightfulItemTags.FRUITS_JABUTICABA).addOptional(Modid.NE.rl("jaboticaba"));
		this.tag(DelightfulItemTags.FRUITS_PITAYA).addOptional(Modid.NE.rl("pitaya"));
		this.tag(DelightfulItemTags.FRUITS_RAMBUTAN).addOptional(Modid.NE.rl("ramboutan"));
		this.tag(DelightfulItemTags.FRUITS_KIWANO).addOptional(Modid.NE.rl("kiwano"));

		// Create
		this.tag(DelightfulItemTags.UPRIGHT_ON_BELT)
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
			.add(DelightfulItems.BAKLAVA.get())
			.addOptional(Modid.FR.rl("green_tea"))
			.addOptional(Modid.FR.rl("yellow_tea"))
			.addOptional(Modid.FR.rl("black_tea"))
			.addOptional(Modid.FR.rl("rose_hip_tea"))
			.addOptional(Modid.FR.rl("dandelion_tea"))
			.addOptional(Modid.FR.rl("purulent_tea"))
			.addOptional(Modid.FR.rl("gamblers_tea"));

		// Serene Seasons
		this.tag(Util.it("sereneseasons", "summer_crops"))
			.addTag(DelightfulItemTags.SEEDS_SALMONBERRY)
			.addTag(DelightfulItemTags.SEEDS_CANTALOUPE);

		// Phantasm
		this.addSelf(DelightfulItemTags.VOID_CRYSTAL_BLOCK);
		this.tag(DelightfulItemTags.CRYSTAL_SPIKE_TIPS)
			.addOptional(Modid.EP.rl("crystal_spike_tip"))
			.addOptional(Modid.EP.rl("void_crystal_spike_tip"));
		this.tag(DelightfulItemTags.XP_BOOSTED).add(Knives.CRYSTALLINE.get());

		// Spirit
		this.tag(DelightfulItemTags.SOUL_STEEL_INGOT).addOptional(Modid.SP.rl("soul_steel_ingot"));
		this.tag(DelightfulItemTags.SOUL_STEEL_MAINHAND).add(Knives.SOUL_STEEL.get());

		// Unusual End
		this.tag(DelightfulItemTags.INGOTS_PEARLESCENT).addOptional(Util.rl("unusualend", "pearlescent_ingot"));

		// Botania
		this.addSelf(DelightfulItemTags.LIVINGWOOD_TWIG);
		this.addSelf(DelightfulItemTags.DREAMWOOD_TWIG);
		this.tag(DelightfulItemTags.MANA_ITEMS)
			.add(Knives.MANASTEEL.get())
			.add(Knives.ELEMENTIUM.get())
			.add(Knives.TERRA.get());
		this.tag(DelightfulItemTags.ingot("alfsteel")).addOptional(Modid.MB.rl("alfsteel_ingot"));

		// Additional Additions
		this.addSelf(DelightfulItemTags.ROSE_GOLD_ALLOY);
		this.addSelf(DelightfulItemTags.GOLD_RING);
		this.addSelf(Modid.AA.it("gilded_netherite_upgrade"));
		this.addSelf(Modid.AA.it("rose_gold_upgrade"));

		// Enderite
		this.addSelf(Modid.LE.it("enderite_upgrade_smithing_template"));

		// Nourished Nether
		this.tag(DelightfulItemTags.ingot("necronium")).addOptional(Util.rl("nourished_nether", "necronium_ingot"));
		this.tag(Util.it("nourished_nether", "necronium_tools")).add(Knives.NECRONIUM.get());

		// Undergarden
		this.tag(Modid.UG.it("cloggrum_items")).add(Knives.CLOGGRUM.get());
		this.tag(Modid.UG.it("froststeel_items")).add(Knives.FROSTSTEEL.get());
		this.tag(Modid.UG.it("utherium_items")).add(Knives.UTHERIUM.get());
		this.addSelf(Modid.UG.it("forgotten_upgrade_smithing_template"));

		// Aether
		this.addSelf(DelightfulItemTags.ENCHANTED_GRAVITITE);
		this.addSelf(DelightfulItemTags.HOLYSTONE);

		// Aether Redux
		this.tag(Modid.AER.it("veridium_advancement_infusable")).add(Knives.VERIDIUM.get());
		this.tag(Modid.AER.it("infused_veridium_items")).add(Knives.INFUSED_VERIDIUM.get());
		this.tag(DelightfulItemTags.INGOTS_VERIDIUM).addOptional(Modid.AER.rl("veridium_ingot"));
		this.tag(DelightfulItemTags.INGOTS_GRAVITITE).addOptional(Modid.AER.rl("gravitite_ingot"));

		// Deep Aether
		this.tag(DelightfulItemTags.GEMS_SKYJADE).addOptionalTag(Modid.DA.rl("skyjade_repairing"));
		this.tag(DelightfulItemTags.INGOTS_STRATUS).addOptionalTag(Modid.DA.rl("stratus_repairing"));
		this.addSelf(DelightfulItemTags.STRATUS_UPGRADE);

		// Ancient Aether
		this.addSelf(Modid.AAE.it("valkyrum"));

		// Aether: Lost Content Addon
		this.tag(Util.it("lost_aether_content", "phoenix_tools")).add(Knives.PHOENIX.get());

		// AE2
		this.addSelf(DelightfulItemTags.FLUIX_BLOCK);
		this.addSelf(Modid.AE2.it("fluix_upgrade_smithing_template"));
		this.tag(Modid.D.it("quartz_knife"))
			.add(Knives.NETHER_QUARTZ.get())
			.add(Knives.CERTUS_QUARTZ.get());

		// Neapolitan
		this.tag(Modid.N.it("ice_cream"))
			.add(DelightfulItems.MATCHA_ICE_CREAM.get())
			.add(DelightfulItems.SALMONBERRY_ICE_CREAM.get())
			.add(DelightfulItems.SOURCE_BERRY_ICE_CREAM.get());

		// Ars Nouveau
		this.tag(Modid.AN.it("magic_food"))
			.add(DelightfulItems.SOURCE_BERRY_PIE_SLICE.get())
			.add(DelightfulItems.SOURCE_BERRY_COOKIE.get())
			.add(DelightfulItems.SOURCE_BERRY_GUMMY.get())
			.add(DelightfulItems.SOURCE_BERRY_ICE_CREAM.get())
			.add(DelightfulItems.SOURCE_BERRY_MILKSHAKE.get());

		// Sully's Mod
		this.tag(Modid.SM.it("tortoise_food"))
			.addTag(ForgeTags.BERRIES)
			.add(Items.BAMBOO)
			.add(DelightfulItems.CACTUS_FLESH.get())
			.add(ModItems.PUMPKIN_SLICE.get())
			.add(DelightfulItems.CANTALOUPE_SLICE.get())
			.addOptional(Modid.FRD.rl("hamimelon_slice"))
			.addOptional(Modid.UGD.rl("gloomgourd_slice"));
	}

	@Override
	public @NotNull String getName() {
		return Delightful.MODID;
	}

	private void addSelf(TagKey<Item> item) {
		this.tag(item).addOptional(item.location());
	}
}
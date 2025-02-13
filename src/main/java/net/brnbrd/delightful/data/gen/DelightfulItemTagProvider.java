package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.compat.AquaticCompat;
import net.brnbrd.delightful.compat.BWGCompat;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.compat.UnusualEndCompat;
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
			.addOptional(Util.rl(Mods.ECO, "azalea_flower"))
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
			.addOptional(Util.rl(Mods.AN, "source_berry_pie"))
			.addOptional(Util.rl(Mods.UG, "gloomgourd_pie"))
			.addOptional(Util.rl(Mods.BWG, BWGCompat.blueberry_pie))
			.addOptional(Util.rl(Mods.BWG, BWGCompat.green_apple_pie))
			.addOptional(Util.rl(Mods.UE, UnusualEndCompat.chorus_pie))
			.addOptional(Util.rl(Mods.UA, AquaticCompat.mulberry_pie));
		this.tag(DelightfulItemTags.FIRE_KNIVES)
			.add(Knives.FIERY.get())
			.add(Knives.KIWANO.get())
			.add(Knives.BLAZING.get());
		this.tag(DelightfulItemTags.STRAW_PLANTS)
			.add(Items.GRASS)
			.add(Items.TALL_GRASS)
			.add(ModItems.SANDY_SHRUB.get())
			.addOptional(Util.rl(Mods.UA, "beachgrass"))
			.addOptional(Util.rl(Mods.UA, "tall_beachgrass"));
		this.tag(DelightfulItemTags.ROTTEN)
			.add(Items.ROTTEN_FLESH)
			.addOptional(Util.rl(Mods.RL, "rotten_chunk"))
			.addOptional(Util.rl(Mods.UG, "rotten_blisterberry"));
		this.tag(DelightfulItemTags.MAKES_DYE_RED)
			.addOptionalTag(Util.rl(Mods.BWG, "dye/makes_red_dye"))
			.addOptional(Util.rl(Mods.HAB, "red_anthurium"))
			.addOptional(Util.rl(Mods.HAB, "dreadbud"))
			.addOptional(Util.rl(Mods.HAB, "rafflesia"))
			.addOptional(Util.rl(Mods.UG, "blood_mushroom"))
			.addOptional(Util.rl("snowyspirit", "ginger_flower"));
		this.tag(DelightfulItemTags.MAKES_DYE_YELLOW)
			.addOptionalTag(Util.rl(Mods.BWG, "dye/makes_yellow_dye"))
			.addOptional(Util.rl(Mods.HAB, "yellow_anthurium"));
		this.tag(DelightfulItemTags.MAKES_DYE_PURPLE)
			.addOptionalTag(Util.rl(Mods.BWG, "dye/makes_purple_dye"))
			.addOptional(Util.rl(Mods.HAB, "purple_anthurium"));
		this.tag(DelightfulItemTags.MAKES_DYE_MAGENTA)
			.addOptionalTag(Util.rl(Mods.BWG, "dye/makes_magenta_dye"))
			.addOptional(Util.rl(Mods.HAB, "purple_anthurium"));
		this.tag(DelightfulItemTags.MAKES_DYE_WHITE)
			.addOptionalTag(Util.rl(Mods.BWG, "dye/makes_white_dye"))
			.addOptional(Util.rl(Mods.HAB, "white_anthurium"))
			.addOptional(Util.rl(Mods.UG, "veil_mushroom"));
		this.tag(DelightfulItemTags.MAKES_DYE_BLUE)
			.addOptionalTag(Util.rl(Mods.BWG, "dye/makes_blue_dye"))
			.addOptional(Util.rl(Mods.UG, "indigo_mushroom"));
		this.tag(DelightfulItemTags.MAKES_DYE_LIGHT_BLUE)
			.addOptional(Util.rl(Mods.UG, "miserabell"));
		this.tag(DelightfulItemTags.MAKES_DYE_BLACK)
			.addOptionalTag(Util.rl(Mods.BWG, "dye/makes_black_dye"))
			.addOptional(Util.rl(Mods.UG, "ink_mushroom"));
		this.tag(DelightfulItemTags.MAKES_DYE_ORANGE)
			.addOptionalTag(Util.rl(Mods.BWG, "dye/makes_orange_dye"));
		this.tag(DelightfulItemTags.MAKES_DYE_PINK)
			.addOptionalTag(Util.rl(Mods.BWG, "dye/makes_pink_dye"));
		this.tag(DelightfulItemTags.MAKES_DYE_CYAN)
			.addOptionalTag(Util.rl(Mods.BWG, "dye/makes_cyan_dye"));
		this.tag(DelightfulItemTags.MAKES_DYE_GREEN)
			.addOptionalTag(Util.rl(Mods.BWG, "dye/makes_green_dye"));
		//this.tag(DelightfulItemTags.MAKES_DYE_LIME);
		this.tag(DelightfulItemTags.MAKES_DYE_LIGHT_GRAY)
			.addOptional(Util.rl(Mods.HAB, "edelweiss"));

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
		this.tag(ForgeTags.CROPS_TOMATO).addOptional(Util.rl(Mods.SAS, "tomato_slices"));
		this.tag(ForgeTags.VEGETABLES_TOMATO).addTag(ForgeTags.CROPS_TOMATO);

		// Onion
		this.tag(ForgeTags.CROPS_ONION)
			.addOptional(Util.rl(Mods.BWG, "oddion_bulb"))
			.addOptional(Util.rl(Mods.SAS, "sliced_onion"));
		this.tag(ForgeTags.VEGETABLES_ONION).addTag(ForgeTags.CROPS_ONION);

		// Potato
		this.tag(Tags.Items.CROPS_POTATO)
			.addOptional(Util.rl(Mods.MOD, "diced_potatoes"))
			.addOptional(Util.rl(Mods.CAD, "potato_slice"));
		this.tag(ForgeTags.VEGETABLES_POTATO).addTag(Tags.Items.CROPS_POTATO);

		// Carrot
		this.tag(Tags.Items.CROPS_CARROT).addOptional(Util.rl(Mods.SAS, "chopped_carrot"));
		this.tag(ForgeTags.VEGETABLES_CARROT).addTag(Tags.Items.CROPS_CARROT);

		// Beetroot
		this.tag(Tags.Items.CROPS_BEETROOT).addOptional(Util.rl(Mods.SAS, "chopped_beetroot"));
		this.tag(ForgeTags.VEGETABLES_BEETROOT).addTag(Tags.Items.CROPS_BEETROOT);

		this.tag(ModTags.CABBAGE_ROLL_INGREDIENTS)
			.addTag(DelightfulItemTags.RAW_VENISON)
			.addTag(DelightfulItemTags.RAW_GOAT)
			.addOptionalTag(Util.rl(Util.LOADER, "raw_duck"))
			.addOptionalTag(Util.rl(Util.LOADER, "raw_turkey"));

		// Vegetables
		this.tag(DelightfulItemTags.VEGETABLES_SPICY)
			.addOptionalTag(Util.rl(Util.LOADER, "vegetables/ghost_pepper"))
			.addOptionalTag(Util.rl(Util.LOADER, "chilipepper"))
			.addOptionalTag(Util.rl(Util.LOADER, "chile_peppers"))
			.addOptionalTag(Util.rl(Util.LOADER, "crops/chile_pepper"))
			.addOptionalTag(Util.rl(Mods.CR, "hot_nether_fruit"))
			.addOptional(Util.rl(Mods.MND, "bullet_pepper"));
		this.tag(DelightfulItemTags.VEGETABLES_CORN)
			.addOptionalTag(Util.rl(Util.LOADER, "grain/corn"))
			.addOptional(Util.rl(Mods.CD, "corn_cob"))
			.addOptional(Util.rl("hauntedharvest", "corn"))
			.addOptional(Util.rl("corn_delight", "corn"));
		this.tag(DelightfulItemTags.VEGETABLES_GINGER).addOptional(Util.rl("snowyspirit", "ginger"));
		this.tag(DelightfulItemTags.VEGETABLES_CUCUMBER).addOptional(Util.rl(Mods.CD, "cucumber"));
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
		this.tag(DelightfulItemTags.FRUITS_GREEN_APPLE).addOptional(Util.rl(Mods.BWG, BWGCompat.green_apple));
		this.tag(DelightfulItemTags.FRUITS_YUCCA)
			.addOptional(Util.rl(Mods.BWG, BWGCompat.yucca))
			.addOptionalTag(Util.rl("atmospheric", "yucca_fruit"));
		this.tag(DelightfulItemTags.FRUITS_BAOBAB).addOptional(Util.rl(Mods.BWG, BWGCompat.baobab));
		this.tag(DelightfulItemTags.FRUITS_PRICKLY_PEAR).addOptional(Util.rl(Mods.ECO, "prickly_pear"));
		this.tag(DelightfulItemTags.FRUITS_TORCHBERRIES).addOptional(Util.rl(Mods.TF, "torchberries"));
		this.tag(DelightfulItemTags.FRUITS_SOURCEBERRY).addOptional(Util.rl(Mods.AN, "sourceberry_bush"));
		this.tag(DelightfulItemTags.FRUITS_ELDERBERRY).addOptional(Util.rl(Mods.RC, "elderberry"));
		this.tag(DelightfulItemTags.FRUITS_BLACKCURRANT)
			.addOptional(Util.rl(Mods.RC, "blackcurrant"))
			.addOptionalTag(Util.rl("atmospheric", "currant"));
		this.tag(DelightfulItemTags.FRUITS_REDCURRANT).addOptional(Util.rl(Mods.RC, "redcurrant"));
		this.tag(DelightfulItemTags.FRUITS_WHITECURRANT).addOptional(Util.rl(Mods.RC, "whitecurrant"));
		this.tag(DelightfulItemTags.FRUITS_GEARO_BERRY).addOptional(Util.rl(Mods.VD, "gearo_berry"));
		this.tag(DelightfulItemTags.FRUITS_BLUEBERRIES)
			.addOptional(Util.rl(Mods.BWG, "blueberries"))
			.addOptional(Util.rl(Mods.WB, "blueberries"))
			.addOptional(Util.rl("nutritious_feast", "blueberries"));
		this.tag(DelightfulItemTags.FRUITS_RASPBERRIES).addOptional(Util.rl(Mods.WB, "raspberry"));
		this.tag(DelightfulItemTags.FRUITS_BLACKBERRIES).addOptional(Util.rl(Mods.WB, "blackberry"));
		this.tag(DelightfulItemTags.FRUITS_CRANBERRIES).addOptional(Util.rl(Mods.WB, "cranberries"));
		this.tag(DelightfulItemTags.FRUITS_WILD_BERRIES).addOptional(Util.rl("windswept", "wild_berries"));
		this.tag(DelightfulItemTags.FRUITS_STRAWBERRIES)
				.addOptional(Util.rl(Mods.N, "strawberries"))
				.addOptional(Util.rl(Mods.N, "white_strawberries"));
		this.tag(DelightfulItemTags.FRUITS_BANANA).addOptional(Util.rl(Mods.N, "banana"));
		this.tag(DelightfulItemTags.FRUITS_CHERRY).addOptional(Util.rl(Mods.FA, "cherry_peach"));
		this.tag(DelightfulItemTags.FRUITS_PLUM).addOptional(Util.rl(Mods.ENV, "plum"));
		this.tag(DelightfulItemTags.FRUITS).replace(false)
			.addTag(ForgeTags.BERRIES)
			.addTag(DelightfulItemTags.FRUITS_SWEET)
			.addTag(DelightfulItemTags.FRUITS_CITRUS)
			.addTag(DelightfulItemTags.FRUITS_CHORUS)
			.addTag(DelightfulItemTags.FRUITS_GREEN_APPLE)
			.addTag(DelightfulItemTags.FRUITS_YUCCA)
			.addOptional(Util.rl(Mods.AN, "mendosteen_pod"))
			.addOptional(Util.rl(Mods.AN, "bombegranate_pod"))
			.addOptional(Util.rl(Mods.AN, "frostaya_pod"))
			.addOptional(Util.rl(Mods.AN, "bastion_pod"))
			.addOptional(Util.rl(Mods.UG, "droopvine_item"))
			.addOptional(Util.rl(Mods.EP, "oblifruit"));
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
			.addOptionalTag(Util.rl(Util.LOADER, "fruits/mulberry"))
			.addOptionalTag(Util.rl(Util.LOADER, "fruits/hawberry"))
			.addOptionalTag(Util.rl(Util.LOADER, "fruits/bayberry"))
			.addOptional(Util.rl(Mods.FRIGHT, "soul_berry"))
			.addOptional(Util.rl(Mods.FRIGHT, "wither_berry"))
			.addOptional(Util.rl(Mods.UG, "blisterberry"))
			.addOptional(Util.rl(Mods.DD, "bloom_berries"))
			.addOptional(Util.rl(Mods.EN, "zure_berry"))
			.addOptional(Util.rl(Mods.EP, "pream_berry"))
			.addOptional(Util.rl(Mods.UE, "warped_berries"))
			.addOptional(Util.rl(Mods.AE, "blue_berry"))
			.addOptional(Util.rl(Mods.AER, "zanberry"))
			.addOptional(Util.rl("deep_aether", "goldenleaf_berries"));
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
			.addOptionalTag(Util.rl(Util.LOADER, "fruits/redlove"))
			.addOptional(Util.rl(Mods.FRD, "hamimelon_slice"));

		// Crab
		this.tag(DelightfulItemTags.RAW_CRAB) // Whole Crab
			.addOptional(Util.rl(Mods.CRAB, "crab"))
			.addOptionalTag(Util.rl("finsandtails", "spindly_gem_crabs"));
		this.tag(DelightfulItemTags.COOKED_CRAB) // Whole Crab
			.addOptional(Util.rl(Mods.CRAB, "cooked_crab"));
		this.tag(DelightfulItemTags.CRAB_MEAT)
			.addOptional(Util.rl(Mods.ECO, "crab_meat"));
		this.tag(DelightfulItemTags.CRAB_CLAW_COOKED)
			.addOptional(Util.rl(Mods.CR, "chieftain_claw"))
			.addOptional(Util.rl("finsandtails", "cooked_bull_crab_claw"));
		this.tag(DelightfulItemTags.CRAB_CLAW)
			.addTag(DelightfulItemTags.CRAB_CLAW_COOKED)
			.addOptional(Util.rl(Mods.CRAB, "crab_claw"))
			.addOptional(Util.rl(Mods.ECO, "crab_claw"))
			.addOptional(Util.rl("finsandtails", "red_bull_crab_claw"))
			.addOptional(Util.rl("finsandtails", "white_bull_crab_claw"));

		// Forge
		this.tag(DelightfulItemTags.LAVENDER)
			.addOptionalTag(Util.rl(Util.LOADER, "flowers/lavender"))
			.addOptional(Util.rl(Mods.BOP, "lavender"))
			.addOptional(Util.rl(Mods.BOP, "tall_lavender"));
		this.tag(DelightfulItemTags.ROSEY)
			.addOptional(Util.rl(Mods.BWG, "rose"))
			.addOptional(Util.rl(Mods.FR, "rose_hips"))
			.addOptional(Util.rl("sunflowerdelight", "rosebud"));
		this.tag(DelightfulItemTags.CLOVER)
			.addOptional(Util.rl(Mods.BOP, "clover"))
			.addOptional(Util.rl(Mods.BWG, "clover_patch"))
			.addOptional(Util.rl(Mods.TF, "clover_patch"))
			.addOptional(Util.rl(Mods.BB, "four_leaf_clover"));
		this.tag(DelightfulItemTags.CACTI)
			.add(Items.CACTUS)
			.addOptional(Util.rl(Mods.BWG, "barrel_cactus"))
			.addOptional(Util.rl(Mods.BWG, "flowering_barrel_cactus"))
			.addOptional(Util.rl(Mods.BWG, "prickly_pear_cactus"))
			.addOptional(Util.rl(Mods.BWG, "golden_spined_cactus"))
			.addOptional(Util.rl("biomemakeover", "saguaro_cactus"));
		this.tag(DelightfulItemTags.SMALL_CACTI)
			.addOptionalTag(Util.rl(Mods.HAB, "ball_cacti"))
			.addOptional(Util.rl(Mods.BWG, "mini_cactus"))
			.addOptional(Util.rl(Mods.BOP, "tiny_cactus"))
			.addOptional(Util.rl("atmospheric", "barrel_cactus"))
			.addOptional(Util.rl("biomemakeover", "barrel_cactus"))
			.addOptional(Util.rl("naturesaura", "aura_cactus"));
		this.tag(DelightfulItemTags.MATCHA).add(DelightfulItems.MATCHA.get());
		this.tag(Tags.Items.EGGS)
			.add(Items.TURTLE_EGG)
			.addOptional(Util.rl(Mods.SM, "tortoise_egg"))
			.addOptional(Util.rl(Mods.NA, "tortoise_egg"))
			.addOptional(Util.rl(Mods.NA, "duck_egg"))
			.addOptional(Util.rl(Mods.Q, "egg_parrot_red_blue"))
			.addOptional(Util.rl(Mods.Q, "egg_parrot_blue"))
			.addOptional(Util.rl(Mods.Q, "egg_parrot_green"))
			.addOptional(Util.rl(Mods.Q, "egg_parrot_yellow_blue"))
			.addOptional(Util.rl(Mods.Q, "egg_parrot_gray"))
			.addOptional(Util.rl("autumnity", "turkey_egg"))
			.addOptional(Util.rl("deep_aether", "quail_egg"))
			.addOptional(Util.rl("farmlife", "galliraptor_egg"))
			.addOptional(Util.rl("etcetera", "eggple"));
		this.tag(ForgeTags.COOKED_EGGS)
			.addOptional(Util.rl(Mods.AA, "fried_egg"))
			.addOptional(Util.rl(Mods.IN, "fried_egg"))
			.addOptional(Util.rl(Mods.NA, "cooked_egg"));
		this.tag(DelightfulItemTags.DOUGH_CORN).addOptional(Util.rl(Mods.CD, "corn_dough"));
		this.tag(DelightfulItemTags.DOUGH_NUT).add(DelightfulItems.NUT_DOUGH.get());
		this.tag(ForgeTags.DOUGH)
			.addTag(DelightfulItemTags.DOUGH_NUT)
			.addTag(DelightfulItemTags.DOUGH_CORN);
		this.tag(DelightfulItemTags.BREAD_CORN)
			.addOptional(Util.rl("corn_delight", "cornbread"))
			.addOptional(Util.rl("hauntedharvest", "cornbread"));
		this.tag(ForgeTags.BREAD).addTag(DelightfulItemTags.BREAD_CORN);
		this.tag(DelightfulItemTags.BURGER_BUN).addOptional(Util.rl(Mods.SAS, "burger_bun"));
		this.tag(DelightfulItemTags.BREAD_OR_BUN)
			.addTag(ForgeTags.BREAD)
			.addTag(DelightfulItemTags.BURGER_BUN);
		this.tag(DelightfulItemTags.HOT_SPICE)
			.add(Items.BLAZE_POWDER)
			.addTag(DelightfulItemTags.VEGETABLES_SPICY)
			.addOptionalTag(Util.rl(Mods.MND, "hot_spice"));
		this.tag(DelightfulItemTags.PUMPKINS_CARVED)
			.add(Items.CARVED_PUMPKIN)
			.addOptional(Util.rl("autumnity", "carved_large_pumpkin_slice"));
		this.tag(DelightfulItemTags.PUMPKINS)
			.addTag(DelightfulItemTags.PUMPKINS_CARVED)
			.add(Items.PUMPKIN)
			.addOptional(Util.rl("autumnity", "large_pumpkin_slice"));
		this.tag(DelightfulItemTags.COCONUT).addOptional(Util.rl(Mods.ECO, "coconut_slice"));
		this.tag(DelightfulItemTags.NUTS_WALNUT)
			.addOptional(Util.rl(Mods.ECO, "walnut"))
			.addOptional(Util.rl("caupona", "walnut"));
		this.tag(DelightfulItemTags.NUTS_PEANUT)
			.addOptionalTag(Util.rl(Util.LOADER, "peanut"))
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
			.addOptional(Util.rl(Mods.VD, "roasted_peanut"));
		this.tag(DelightfulItemTags.INGOTS_STEEL).addOptional(Util.rl("simplysteel", "steel_ingot"));
		this.tag(DelightfulItemTags.WATER).add(Items.WATER_BUCKET);
		this.tag(DelightfulItemTags.JAMS)
			.add(DelightfulItems.JAM_JAR.get())
			.add(DelightfulItems.GLOW_JAM_JAR.get())
			.addOptionalTag(Util.rl(Util.LOADER, "jam"))
			.addOptionalTag(Util.rl(Util.LOADER, "jam_bottles"))
			.addOptionalTag(Util.rl(Mods.WB, "berry_jams"))
			.addOptionalTag(Util.rl(Mods.VD, "sweet_jam_bottles"))
			.addOptional(Util.rl(Mods.BC, "sweet_berry_jam"))
			.addOptional(Util.rl(Mods.BC, "glow_berry_marmalade"))
			.addOptional(Util.rl(Mods.BC, "apple_jelly"))
			.addOptional(Util.rl(Mods.UA, "mulberry_jam_bottle"));
		this.tag(DelightfulItemTags.PEANUT_BUTTER)
			.addOptional(Util.rl(Mods.TH, "peanut_butter"))
			.addOptional(Util.rl(Mods.CT, "peanut_butter"));
		this.tag(DelightfulItemTags.NUT_BUTTER)
			.addTag(DelightfulItemTags.PEANUT_BUTTER)
			.add(DelightfulItems.NUT_BUTTER_BOTTLE.get())
			.addOptional(Util.rl(Mods.VD, "nut_mash_bottle"));
		this.tag(DelightfulItemTags.SUGAR).add(Items.SUGAR);
		this.tag(DelightfulItemTags.CHEESE)
			.addOptionalTag(Util.rl(Util.LOADER, "cheeses"))
			.addOptional(Util.rl(Mods.BC, "flaxen_cheese_wedge"))
			.addOptional(Util.rl(Mods.CT, "cheese"))
			.addOptional(Util.rl(Mods.TH, "cheese_wedge"))
			.addOptional(Util.rl(Mods.CAD, "cheese_wheel_slice"))
			.addOptional(Util.rl("farmlife", "tribull_cheese_wedge"))
			.addOptional(Util.rl("ad_astra", "cheese"));
		this.tag(ForgeTags.MILK)
			.addOptional(Util.rl(Mods.AE, "skyroot_milk_bucket"))
			.addOptional(Util.rl("farmlife", "tribull_milk"))
			.addOptional(Util.rl("dracovitadelight", "tribull_milk"))
			.addOptional(Util.rl("blue_skies", "ventium_milk_bucket"))
			.addOptional(Util.rl("forbidden_arcanus", "edelwood_milk_bucket"));

		this.tag(DelightfulItemTags.TEA_LEAVES_GREEN)
			.add(DelightfulItems.GREEN_TEA_LEAF.get())
			.addOptional(Util.rl(Mods.FR, "green_tea_leaves"))
			.addOptional(Util.rl(Mods.TH, "tea"))
			.addOptional(Util.rl(Mods.CT, "tea_leaves"));
		this.tag(DelightfulItemTags.TEA_LEAVES)
			.addTag(DelightfulItemTags.TEA_LEAVES_GREEN)
			.addOptionalTag(Util.rl(Mods.FR, "tea_leaves"));
		this.tag(DelightfulItemTags.RAW_RABBIT).add(Items.RABBIT);
		this.tag(DelightfulItemTags.COOKED_RABBIT).add(Items.COOKED_RABBIT);
		this.tag(DelightfulItemTags.RAW_FISHES_KOI)
			.addOptional(Util.rl(Mods.ENV, "koi"))
			.addOptional(Util.rl("crittersandcompanions", "koi_fish"));
		this.tag(DelightfulItemTags.RAW_FISHES_TUNA)
			.addOptional(Util.rl("spawn", "tuna_chunk"));
		this.tag(DelightfulItemTags.COOKED_FISHES_TUNA)
			.addOptional(Util.rl("spawn", "cooked_tuna_chunk"));
		this.tag(DelightfulItemTags.RAW_SQUID)
			.addOptional(Util.rl(Mods.MD, "squid"))
			.addOptional(Util.rl(Mods.MD, "glow_squid"))
			.addOptional(Util.rl(Mods.CD, "squid"))
			.addOptional(Util.rl(Mods.CD, "glow_squid"));
		this.tag(DelightfulItemTags.RAW_FISHES_SQUID_TENTACLES)
			.addOptional(Util.rl(Mods.MD, "tentacles"))
			.addOptional(Util.rl(Mods.CD, "raw_calamari"));
		this.tag(DelightfulItemTags.RAW_FISHES_SQUID)
			.addTag(DelightfulItemTags.RAW_FISHES_SQUID_TENTACLES)
			.addTag(DelightfulItemTags.RAW_SQUID);
		this.tag(DelightfulItemTags.COOKED_FISHES_SQUID)
			.addOptional(Util.rl(Mods.CD, "cooked_squid"))
			.addOptional(Util.rl(Mods.CD, "cooked_calamari"));
		this.tag(ForgeTags.RAW_FISHES)
			.addTag(DelightfulItemTags.RAW_FISHES_KOI)
			.addTag(DelightfulItemTags.RAW_FISHES_TUNA)
			.addTag(DelightfulItemTags.RAW_FISHES_SQUID)
			.addOptional(Util.rl(Mods.UG, "raw_gwibling"))
			.addOptional(Util.rl("biomemakeover", "glowfish"))
			.addOptional(Util.rl("deep_aether", "raw_aerglow_fish"))
			.addOptional(Util.rl("spawn", "angler_fish"))
			.addOptional(Util.rl("bettas", "betta_fish"));
		this.tag(ForgeTags.COOKED_FISHES)
			.addTag(DelightfulItemTags.COOKED_FISHES_TUNA)
			.addTag(DelightfulItemTags.COOKED_FISHES_SQUID)
			.addOptional(Util.rl(Mods.UG, "cooked_gwibling"))
			.addOptional(Util.rl("biomemakeover", "cooked_glowfish"))
			.addOptional(Util.rl("deep_aether", "cooked_aerglow_fish"));
		this.tag(DelightfulItemTags.RAW_VENISON_COMPAT)
			.addOptional(Util.rl(Mods.ENV, "venison"))
			.addOptional(Util.rl(Mods.NA, "venison"))
			.addOptional(Util.rl(Mods.TF, "raw_venison"))
			.addOptional(Util.rl("goodall", "raw_venison"))
			.addOptional(Util.rl("blue_skies", "venison"));
		this.tag(DelightfulItemTags.RAW_VENISON_CHOP_COMPAT)
			.addOptional(Util.rl(Mods.AD, "venison_shanks"))
			.addOptional(Util.rl(Mods.TFD, "raw_venison_rib"));
		this.tag(DelightfulItemTags.RAW_VENISON)
			.add(DelightfulItems.VENISON_CHOPS.get())
			.addTag(DelightfulItemTags.RAW_VENISON_CHOP_COMPAT)
			.addTag(DelightfulItemTags.RAW_VENISON_COMPAT);
		this.tag(DelightfulItemTags.COOKED_VENISON_COMPAT)
			.addOptional(Util.rl(Mods.ENV, "cooked_venison"))
			.addOptional(Util.rl(Mods.NA, "cooked_venison"))
			.addOptional(Util.rl(Mods.TF, "cooked_venison"))
			.addOptional(Util.rl("goodall", "cooked_venison"))
			.addOptional(Util.rl("blue_skies", "cooked_venison"));
		this.tag(DelightfulItemTags.COOKED_VENISON_CHOP_COMPAT)
			.addOptional(Util.rl(Mods.AD, "cooked_venison_shanks"))
			.addOptional(Util.rl(Mods.TFD, "cooked_venison_rib"));
		this.tag(DelightfulItemTags.COOKED_VENISON)
			.add(DelightfulItems.COOKED_VENISON_CHOPS.get())
			.addTag(DelightfulItemTags.COOKED_VENISON_CHOP_COMPAT)
			.addTag(DelightfulItemTags.COOKED_VENISON_COMPAT);
		this.tag(DelightfulItemTags.RAW_GOAT).add(DelightfulItems.RAW_GOAT.get());
		this.tag(DelightfulItemTags.COOKED_GOAT).add(DelightfulItems.COOKED_GOAT.get());
		this.tag(DelightfulItemTags.RAW_DUCK).addOptional(Util.rl(Mods.NA, "duck"));
		this.tag(DelightfulItemTags.COOKED_DUCK).addOptional(Util.rl(Mods.NA, "cooked_duck"));
		this.tag(DelightfulItemTags.FOODS_MEAT_RAW)
			.addOptionalTag(Util.rl(Util.LOADER, "raw_meat"))
			.addTag(ForgeTags.RAW_FISHES)
			.addTag(ForgeTags.RAW_CHICKEN)
			.addTag(ForgeTags.RAW_BEEF)
			.addTag(ForgeTags.RAW_PORK)
			.addTag(ForgeTags.RAW_MUTTON)
			.addTag(DelightfulItemTags.RAW_RABBIT)
			.addTag(DelightfulItemTags.RAW_VENISON)
			.addTag(DelightfulItemTags.RAW_CRAB)
			.addTag(DelightfulItemTags.RAW_GOAT)
			.addOptionalTag(Util.rl(Util.LOADER, "raw_duck"))
			.addOptionalTag(Util.rl(Util.LOADER, "raw_turkey"))
			.addOptional(Util.rl(Mods.CAD, "raw_donkey_meat"))
			.addOptional(Util.rl(Mods.UG, "raw_dweller_meat"))
			.addOptional(Util.rl(Mods.UG, "raw_gloomper_leg"))
			.addOptional(Util.rl(Mods.EP, "behemoth_meat"))
			.addOptional(Util.rl("frog_legs", "frog_legs"));
		this.tag(DelightfulItemTags.FOODS_MEAT_COOKED)
			.addOptionalTag(Util.rl(Util.LOADER, "cooked_meat"))
			.addTag(ForgeTags.COOKED_FISHES)
			.addTag(ForgeTags.COOKED_CHICKEN)
			.addTag(ForgeTags.COOKED_BEEF)
			.addTag(ForgeTags.COOKED_PORK)
			.addTag(ForgeTags.COOKED_MUTTON)
			.addTag(DelightfulItemTags.COOKED_RABBIT)
			.addTag(DelightfulItemTags.COOKED_VENISON)
			.addTag(DelightfulItemTags.COOKED_CRAB)
			.addTag(DelightfulItemTags.COOKED_GOAT)
			.addOptionalTag(Util.rl(Util.LOADER, "cooked_duck"))
			.addOptionalTag(Util.rl(Util.LOADER, "cooked_turkey"))
			.addOptional(Util.rl(Mods.CAD, "cooked_donkey_meat"))
			.addOptional(Util.rl(Mods.UG, "dweller_steak"))
			.addOptional(Util.rl(Mods.UG, "gloomper_leg"))
			.addOptional(Util.rl(Mods.EP, "behemoth_steak"))
			.addOptional(Util.rl("frog_legs", "cooked_frog_legs"));
		this.tag(DelightfulItemTags.FOODS_MEAT)
			.addTag(DelightfulItemTags.FOODS_MEAT_RAW)
			.addTag(DelightfulItemTags.FOODS_MEAT_COOKED)
			.addTag(DelightfulItemTags.CRAB_MEAT);
		this.tag(DelightfulItemTags.PROTEIN_PATTY)
			.add(ModItems.BEEF_PATTY.get())
			.addOptional(Util.rl(Mods.MD, "vegan_patty"));
		this.tag(DelightfulItemTags.COOKED_BEEF_OR_VEGAN)
			.addTag(ForgeTags.COOKED_BEEF)
			.addTag(DelightfulItemTags.PROTEIN_PATTY);
		this.tag(DelightfulItemTags.CATTAIL)
			.addOptional(Util.rl("sprout", "cattail"))
			.addOptional(Util.rl(Mods.BOP, "cattail"))
			.addOptional(Util.rl("biomemakeover", "cattail"));
		this.tag(DelightfulItemTags.GEMS_ROSE_QUARTZ)
			.addOptional(Util.rl(Mods.BOP, "rose_quartz_chunk"))
			.addOptional(Util.rl(Mods.C, "rose_quartz"));
		this.tag(DelightfulItemTags.GEMS_ZANITE).addOptional(Util.rl(Mods.AE, "zanite_gemstone"));
		this.tag(DelightfulItemTags.CHOCOLATE)
			.addOptionalTag(Util.rl(Util.LOADER, "chocolates"))
			.addOptionalTag(Util.rl(Util.LOADER, "bars/chocolate"))
			.addOptionalTag(Util.rl(Util.LOADER, "chocolatebar"))
			.addOptional(Util.rl(Mods.N, "chocolate_bar"))
			.addOptional(Util.rl(Mods.C, "bar_of_chocolate"));
		this.tag(ForgeTags.SALAD_INGREDIENTS)
			.add(DelightfulItems.CHOPPED_CLOVER.get())
			.addOptional(Util.rl("babyfat", "water_lettuce"));
		this.tag(DelightfulItemTags.SEEDS_SALMONBERRY).add(DelightfulItems.SALMONBERRY_PIPS.get());
		this.tag(DelightfulItemTags.SEEDS_CANTALOUPE).add(DelightfulItems.CANTALOUPE_SEEDS.get());
		this.tag(ForgeTags.SEEDS)
			.addTag(DelightfulItemTags.SEEDS_SALMONBERRY)
			.addTag(DelightfulItemTags.SEEDS_CANTALOUPE)
			.addOptional(Util.rl(Mods.FR, "tea_seeds"));
		this.tag(DelightfulItemTags.TORTILLA).addOptional(Util.rl(Mods.CD, "tortilla"));
		this.tag(Tags.Items.SEEDS)
			.addOptional(Util.rl(Mods.VD, "oat_seeds"))
			.addOptional(Util.rl(Mods.VD, "ghost_pepper_seeds"))
			.addOptional(Util.rl(Mods.VD, "cucumber_seeds"))
			.addOptional(Util.rl(Mods.FRD, "lemon_seeds"))
			.addOptional(Util.rl(Mods.FRD, "hamimelon_seeds"));
		this.tag(DelightfulItemTags.BONES)
			.addOptional(Util.rl(Mods.DD, "sculk_bone"))
			.addOptional(Util.rl("alexscaves", "heavy_bone"))
			.addOptional(Util.rl(Mods.UA, "thrasher_tooth"))
			.addOptional(Util.rl(Mods.SM, "piranha_tooth"));
		this.tag(DelightfulItemTags.SYRUP)
			.addOptionalTag(Util.rl(Mods.SUP, "pancake_syrup"))
			.addOptional(Util.rl("autumnity", "syrup_bottle"));
		this.tag(DelightfulItemTags.COOKIES)
			.add(Items.COOKIE)
			.add(ModItems.HONEY_COOKIE.get())
			.add(ModItems.SWEET_BERRY_COOKIE.get())
			.add(DelightfulItems.SOURCE_BERRY_COOKIE.get())
			.addOptional(Util.rl(Mods.FR, "green_tea_cookie"))
			.addOptional(Util.rl(Mods.AD, "mulberry_cookie"))
			.addOptional(Util.rl(Mods.AD, "maple_cookie"))
			.addOptional(Util.rl(Mods.AD, "cherry_cookie"))
			.addOptional(Util.rl(Mods.MD, "bat_cookie"))
			.addOptional(Util.rl(Mods.VD, "oatmeal_cookie"))
			.addOptional(Util.rl(Mods.FRD, "persimmon_cookie"))
			.addOptional(Util.rl(Mods.FRD, "lemon_cookie"))
			.addOptional(Util.rl(Mods.FRD, "cranberry_cookie"))
			.addOptional(Util.rl(Mods.FRD, "bayberry_cookie"))
			.addOptional(Util.rl(Mods.FRIGHT, "cookie_flesh"))
			.addOptional(Util.rl(Mods.FRIGHT, "cookie_spidereye"))
			.addOptional(Util.rl(Mods.FRIGHT, "cookie_slimeapple"))
			.addOptional(Util.rl(Mods.FRIGHT, "cookie_slime"))
			.addOptional(Util.rl(Mods.FRIGHT, "cookie_cobweb"))
			.addOptional(Util.rl(Mods.FRIGHT, "cookie_ghast_tear"))
			.addOptional(Util.rl(Mods.FRIGHT, "cookie_soul_berry"))
			.addOptional(Util.rl(Mods.FRIGHT, "cookie_wither_berry"))
			.addOptional(Util.rl("ends_delight", "chorus_cookie"))
			.addOptional(Util.rl("exquisito", "chorus_cookie"))
			.addOptional(Util.rl("sunflowerdelight", "shortbread_cookie"))
			.addOptional(Util.rl("vampiresdelight", "orchid_cookie"))
			.addOptional(Util.rl("snowyspirit", "gingerbread_cookie"));
		this.tag(Util.it(Mods.SUP, "cookies"))
			.addTag(DelightfulItemTags.COOKIES)
			.addOptional(Util.rl(Mods.CD, "tortilla_chips"))
			.addOptional(Util.rl("corn_delight", "tortilla_chip"));
		this.tag(DelightfulItemTags.ROPES)
			.add(ModItems.ROPE.get())
			.addOptionalTag(Util.rl(Mods.SUP, "ropes"))
			.addOptional(Util.rl(Mods.AA, "rope"));
		this.tag(DelightfulItemTags.FEATHERS).addOptional(Util.rl(Mods.ECO, "penguin_feather"));
		this.tag(DelightfulItemTags.SNAIL_SHELLS)
			.addOptional(Util.rl(Mods.NA, "snail_shell"))
			.addOptional(Util.rl("autumnity", "snail_shell_piece"));

		// Collector's Reap
		this.tag(Util.it(Mods.CR, "gummies"))
			.add(DelightfulItems.SALMONBERRY_GUMMY.get())
			.add(DelightfulItems.MATCHA_GUMMY.get())
			.add(DelightfulItems.CANTALOUPE_GUMMY.get())
			.add(DelightfulItems.SOURCE_BERRY_GUMMY.get());

		// Nether's Delight
		this.tag(Util.it(Mods.ND, "meal_item"))
			.add(DelightfulItems.STUFFED_CANTALOUPE.get())
			.addOptional(Util.rl(Mods.MD, "bowl_of_stuffed_squid"));

		// My Nether's Delight
		this.tag(Util.it(Mods.MND, "curry_meats"))
			.addTag(DelightfulItemTags.RAW_VENISON)
			.addTag(DelightfulItemTags.RAW_GOAT);

		// Ecologics
		this.addSelf(Util.it(Mods.ECO, "cooked_prickly_pear"));

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
		this.tag(DelightfulItemTags.FRUITS_CITRON).addOptional(Util.rl("nethers_exoticism", "bouddha_s_hand"));
		this.tag(DelightfulItemTags.FRUITS_JABUTICABA).addOptional(Util.rl("nethers_exoticism", "jaboticaba"));
		this.tag(DelightfulItemTags.FRUITS_PITAYA).addOptional(Util.rl("nethers_exoticism", "pitaya"));
		this.tag(DelightfulItemTags.FRUITS_RAMBUTAN).addOptional(Util.rl("nethers_exoticism", "ramboutan"));
		this.tag(DelightfulItemTags.FRUITS_KIWANO).addOptional(Util.rl("nethers_exoticism", "kiwano"));

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
			.addOptional(Util.rl(Mods.FR, "green_tea"))
			.addOptional(Util.rl(Mods.FR, "yellow_tea"))
			.addOptional(Util.rl(Mods.FR, "black_tea"))
			.addOptional(Util.rl(Mods.FR, "rose_hip_tea"))
			.addOptional(Util.rl(Mods.FR, "dandelion_tea"))
			.addOptional(Util.rl(Mods.FR, "purulent_tea"))
			.addOptional(Util.rl(Mods.FR, "gamblers_tea"));

		// Serene Seasons
		this.tag(Util.it("sereneseasons", "summer_crops"))
			.addTag(DelightfulItemTags.SEEDS_SALMONBERRY)
			.addTag(DelightfulItemTags.SEEDS_CANTALOUPE);

		// Phantasm
		this.addSelf(DelightfulItemTags.VOID_CRYSTAL_BLOCK);
		this.tag(DelightfulItemTags.CRYSTAL_SPIKE_TIPS)
			.addOptional(Util.rl(Mods.EP, "crystal_spike_tip"))
			.addOptional(Util.rl(Mods.EP, "void_crystal_spike_tip"));
		this.tag(DelightfulItemTags.XP_BOOSTED).add(Knives.CRYSTALLINE.get());

		// Spirit
		this.tag(DelightfulItemTags.SOUL_STEEL_INGOT).addOptional(Util.rl("spirit", "soul_steel_ingot"));
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
		this.tag(DelightfulItemTags.ingot("alfsteel"))
			.addOptional(Util.rl("mythicbotany", "alfsteel_ingot"));

		// Additional Additions
		this.addSelf(DelightfulItemTags.ROSE_GOLD_ALLOY);
		this.addSelf(DelightfulItemTags.GOLD_RING);
		this.addSelf(Util.it(Mods.AA, "gilded_netherite_upgrade"));
		this.addSelf(Util.it(Mods.AA, "rose_gold_upgrade"));

		// Enderite
		this.addSelf(Util.it(Mods.LE, "enderite_upgrade_smithing_template"));

		// Nourished Nether
		this.tag(DelightfulItemTags.ingot("necronium")).addOptional(Util.rl("nourished_nether", "necronium_ingot"));
		this.tag(Util.it("nourished_nether", "necronium_tools")).add(Knives.NECRONIUM.get());

		// Undergarden
		this.tag(Util.it(Mods.UG, "cloggrum_items")).add(Knives.CLOGGRUM.get());
		this.tag(Util.it(Mods.UG, "froststeel_items")).add(Knives.FROSTSTEEL.get());
		this.tag(Util.it(Mods.UG, "utherium_items")).add(Knives.UTHERIUM.get());
		this.addSelf(Util.it(Mods.UG, "forgotten_upgrade_smithing_template"));

		// Aether
		this.addSelf(DelightfulItemTags.ENCHANTED_GRAVITITE);
		this.addSelf(DelightfulItemTags.HOLYSTONE);

		// Aether Redux
		this.tag(Util.it(Mods.AER, "veridium_advancement_infusable")).add(Knives.VERIDIUM.get());
		this.tag(Util.it(Mods.AER, "infused_veridium_items")).add(Knives.INFUSED_VERIDIUM.get());
		this.tag(DelightfulItemTags.INGOTS_VERIDIUM).addOptional(Util.rl(Mods.AER, "veridium_ingot"));
		this.tag(DelightfulItemTags.INGOTS_GRAVITITE).addOptional(Util.rl(Mods.AER, "gravitite_ingot"));

		// Deep Aether
		this.tag(DelightfulItemTags.GEMS_SKYJADE).addOptionalTag(Util.rl("deep_aether", "skyjade_repairing"));
		this.tag(DelightfulItemTags.INGOTS_STRATUS).addOptionalTag(Util.rl("deep_aether", "stratus_repairing"));
		this.addSelf(DelightfulItemTags.STRATUS_UPGRADE);

		// Ancient Aether
		this.addSelf(Util.it("ancient_aether", "valkyrum"));

		// Aether: Lost Content Addon
		this.tag(Util.it("lost_aether_content", "phoenix_tools")).add(Knives.PHOENIX.get());

		// AE2
		this.addSelf(DelightfulItemTags.FLUIX_BLOCK);
		this.addSelf(Util.it(Mods.AE2, "fluix_upgrade_smithing_template"));
		this.tag(Util.it(Delightful.MODID, "quartz_knife"))
			.add(Knives.NETHER_QUARTZ.get())
			.add(Knives.CERTUS_QUARTZ.get());

		// Neapolitan
		this.tag(Util.it(Mods.N, "ice_cream"))
			.add(DelightfulItems.MATCHA_ICE_CREAM.get())
			.add(DelightfulItems.SALMONBERRY_ICE_CREAM.get())
			.add(DelightfulItems.SOURCE_BERRY_ICE_CREAM.get());

		// Ars Nouveau
		this.tag(Util.it(Mods.AN, "magic_food"))
			.add(DelightfulItems.SOURCE_BERRY_PIE_SLICE.get())
			.add(DelightfulItems.SOURCE_BERRY_COOKIE.get())
			.add(DelightfulItems.SOURCE_BERRY_GUMMY.get())
			.add(DelightfulItems.SOURCE_BERRY_ICE_CREAM.get())
			.add(DelightfulItems.SOURCE_BERRY_MILKSHAKE.get());

		// Sully's Mod
		this.tag(Util.it(Mods.SM, "tortoise_food"))
			.addTag(ForgeTags.BERRIES)
			.add(Items.BAMBOO)
			.add(DelightfulItems.CACTUS_FLESH.get())
			.add(ModItems.PUMPKIN_SLICE.get())
			.add(DelightfulItems.CANTALOUPE_SLICE.get())
			.addOptional(Util.rl(Mods.FRD, "hamimelon_slice"))
			.addOptional(Util.rl(Mods.UGD, "gloomgourd_slice"));
	}

	@Override
	public @NotNull String getName() {
		return Delightful.MODID;
	}

	private void addSelf(TagKey<Item> item) {
		this.tag(item).addOptional(item.location());
	}
}
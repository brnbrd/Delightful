package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.compat.BWGCompat;
import net.brnbrd.delightful.compat.UnusualEndCompat;
import net.brnbrd.delightful.compat.WindsweptCompat;
import net.brnbrd.delightful.compat.abnormals.AquaticCompat;
import net.brnbrd.delightful.compat.brewinandchewin.BrewinChewinCompat;
import net.brnbrd.delightful.compat.Modid;
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
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.CompatibilityTags;
import vectorwing.farmersdelight.common.tag.ModTags;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DelightfulItemTagProvider extends ItemTagsProvider {
	public DelightfulItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, provider, blockTagProvider, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		// Minecraft
		this.tag(ItemTags.FLOWERS).addTag(DelightfulItemTags.FLOWERS_LAVENDER);
		this.tag(ItemTags.SMALL_FLOWERS)
			.addTag(DelightfulItemTags.FLOWERS_AZALEA)
			.add(DelightfulItems.WILD_SALMONBERRIES.get());
		this.tag(ItemTags.FOX_FOOD).addTag(CommonTags.Items.BERRIES);
		this.tag(ItemTags.STAIRS)
			.add(DelightfulItems.GLOW_JAM_COOKIE_TILE_STAIRS.get())
			.add(DelightfulItems.SOURCE_BERRY_COOKIE_TILE_STAIRS.get());
		this.tag(ItemTags.SLABS)
			.add(DelightfulItems.GLOW_JAM_COOKIE_TILE_SLAB.get())
			.add(DelightfulItems.SOURCE_BERRY_COOKIE_TILE_SLAB.get());
		this.tag(ItemTags.WALLS)
			.add(DelightfulItems.GLOW_JAM_COOKIE_TILE_WALL.get())
			.add(DelightfulItems.SOURCE_BERRY_COOKIE_TILE_WALL.get());

		// Farmer's Delight
		this.tag(ModTags.Items.MEALS)
			.add(DelightfulItems.CACTUS_CHILI.get())
			.add(DelightfulItems.CACTUS_SOUP.get())
			.add(DelightfulItems.VENISON_STEW.get())
			.add(DelightfulItems.SINIGANG.get())
			.add(DelightfulItems.COCONUT_CURRY.get())
			.add(DelightfulItems.FIELD_SALAD.get())
			.add(DelightfulItems.STUFFED_CANTALOUPE.get());
		this.tag(ModTags.Items.FEASTS)
			.add(DelightfulItems.STUFFED_CANTALOUPE_BLOCK.get());
		this.tag(ModTags.Items.PIES).addTag(DelightfulItemTags.COMPAT_PIES);
		this.tag(DelightfulItemTags.STRAW_PLANTS)
			.add(Items.GRASS)
			.add(Items.TALL_GRASS)
			.add(ModItems.SANDY_SHRUB.get())
			.addOptional(Modid.UA.rl("beachgrass"))
			.addOptional(Modid.UA.rl("tall_beachgrass"));
		this.tag(DelightfulItemTags.BARKS).add(ModItems.TREE_BARK.get());
		this.tag(DelightfulItemTags.STONE_CABINETS)
			.add(DelightfulItems.BASALT_CABINET.get())
			.add(DelightfulItems.QUARTZ_CABINET.get());
		this.tag(ModTags.Items.CABINETS).addTag(DelightfulItemTags.STONE_CABINETS);
		this.tag(ModTags.Items.WILD_CROPS).add(DelightfulItems.WILD_SALMONBERRIES.get());

		// Knives
		var FDknives = this.tag(ModTags.Items.KNIVES);
		var toolsknives = this.tag(CommonTags.Items.TOOLS_KNIVES);
		DelightfulItems.ITEMS.getEntries().stream()
			.map(RegistryObject::get).filter(i -> i instanceof KnifeItem).forEach(knife -> {
				FDknives.add(knife);
				toolsknives.add(knife);
			});
		this.tag(ItemTags.PIGLIN_LOVED).add(Knives.REFINED_GLOWSTONE.get());

		// Delightful
		this.tag(DelightfulItemTags.ROASTED_MARSHMALLOWS)
			.add(DelightfulItems.COOKED_MARSHMALLOW_STICK.get())
			.addOptional(Modid.HH.rl("roasted_marshmallow_stick"));
		this.tag(DelightfulItemTags.VEGETARIAN_PATTIES)
			.addOptional(Modid.MD.rl("vegan_patty"))
			.addOptional(Modid.VEG.rl("cooked_vegetarian_patty"));
		this.tag(DelightfulItemTags.PATTIES)
			.addTag(DelightfulItemTags.VEGETARIAN_PATTIES)
			.add(ModItems.BEEF_PATTY.get());
		this.tag(DelightfulItemTags.ROSE)
			.add(Items.ROSE_BUSH)
			.addOptional(Modid.BWG.rl("rose"))
			.addOptional(Modid.BWG.rl("osiria_rose"))
			.addOptional(Modid.WS.rl("red_rose"))
			.addOptional(Modid.WS.rl("red_rose_bush"))
			.addOptional(Modid.FORA.rl("stout_beach_rose_bush"))
			.addOptional(Modid.FORA.rl("tall_beach_rose_bush"))
			.addOptional(Modid.FORA.rl("rose_hip"))
			.addOptional(Modid.FORA.rl("rose_petals"))
			.addOptional(Modid.CR.rl("bulbous_rose"))
			.addOptional(Modid.BOP.rl("rose"))
			.addOptional(Modid.FR.rl("rose_hips"))
			.addOptional(Modid.SEED.rl("rosehip"))
			.addOptional(Util.rl("sunflowerdelight", "rosebud"));
		this.tag(DelightfulItemTags.ROTTEN)
			.add(Items.ROTTEN_FLESH)
			.addOptional(Modid.RL.rl("rotten_chunk"))
			.addOptional(Modid.UG.rl("rotten_blisterberry"));
		this.tag(DelightfulItemTags.SPITE)
			.addOptional(Modid.ECO.rl("prickly_pear"))
			.addOptional(Modid.ECO.rl("cooked_prickly_pear"));
		this.tag(DelightfulItemTags.FIRE_KNIVES)
			.add(Knives.FIERY.get())
			.add(Knives.KIWANO.get())
			.add(Knives.BLAZING.get());
		this.tag(DelightfulItemTags.COMPAT_PIES)
			.addOptional(Modid.AN.rl("source_berry_pie"))
			.addOptional(Modid.UG.rl("gloomgourd_pie"))
			.addOptional(Modid.BWG.rl(BWGCompat.blueberry_pie))
			.addOptional(Modid.BWG.rl(BWGCompat.green_apple_pie))
			.addOptional(Modid.UE.rl(UnusualEndCompat.chorus_pie))
			.addOptional(Modid.UA.rl(AquaticCompat.mulberry_pie))
			.addOptional(Modid.AT.rl(AtmosphericCompat.PASSION_FRUIT_TART))
			.addOptional(Modid.WS.rl(WindsweptCompat.MUTTON_PIE));

		// FD/Vanilla Crops
		this.tag(CommonTags.Items.CROPS_TOMATO).addOptional(Modid.SAS.rl("tomato_slices"));
		this.tag(CommonTags.Items.CROPS_ONION)
			.addOptional(Modid.BWG.rl("oddion_bulb"))
			.addOptional(Modid.SAS.rl("sliced_onion"));
		this.tag(Tags.Items.CROPS_POTATO)
			.addOptional(Modid.MOD.rl("diced_potatoes"))
			.addOptional(Modid.CAD.rl("potato_slice"));
		this.tag(Tags.Items.CROPS_CARROT).addOptional(Modid.SAS.rl("chopped_carrot"));
		this.tag(Tags.Items.CROPS_BEETROOT).addOptional(Modid.SAS.rl("chopped_beetroot"));

		// Other Crops
		this.tag(DelightfulItemTags.CROPS_ACORN).addTag(DelightfulItemTags.NUTS_ACORN);
		this.tag(DelightfulItemTags.CROPS_CACTUS)
			.add(DelightfulItems.CACTUS_FLESH.get())
			.addOptionalTag(Modid.LOADER.rl("crops/cactus_paddle"));
		this.tag(DelightfulItemTags.CROPS_CORN)
			.addOptional(Modid.CD.rl("corn_cob"))
			.addOptional(Modid.HH.rl("corn"))
			.addOptional(Modid.CORN.rl("corn"));
		this.tag(DelightfulItemTags.CROPS_GINGER)
			.addOptionalTag(Modid.LOADER.rl("vegetables/ginger"))
			.addOptional(Modid.SS.rl("ginger"))
			.addOptional(Modid.WS.rl("ginger_root"));
		this.tag(CommonTags.Items.CROPS).replace(false)
			.addTag(DelightfulItemTags.CROPS_ACORN)
			.addTag(DelightfulItemTags.CROPS_CACTUS)
			.addTag(DelightfulItemTags.CROPS_CORN)
			.addTag(DelightfulItemTags.CROPS_GINGER);

		// Salad Ingredients
		this.tag(DelightfulItemTags.SALAD_INGREDIENTS_CLOVER).add(DelightfulItems.CHOPPED_CLOVER.get());
		this.tag(CommonTags.Items.SALAD_INGREDIENTS).addTag(DelightfulItemTags.SALAD_INGREDIENTS_CLOVER);

		// Vegetables
		this.tag(DelightfulItemTags.VEGETABLES_SPICY)
			.addOptionalTag(Modid.LOADER.rl("vegetables/ghost_pepper"))
			.addOptionalTag(Modid.LOADER.rl("chilipepper"))
			.addOptionalTag(Modid.LOADER.rl("chile_peppers"))
			.addOptionalTag(Modid.LOADER.rl("crops/chile_pepper"))
			.addOptionalTag(Modid.CR.rl("hot_nether_fruits"))
			.addOptional(Modid.MND.rl("bullet_pepper"));
		this.tag(DelightfulItemTags.VEGETABLES_CUCUMBER).addOptional(Modid.CD.rl("cucumber"));
		this.tag(CommonTags.Items.VEGETABLES).replace(false)
			.addTag(DelightfulItemTags.VEGETABLES_CUCUMBER)
			.addTag(DelightfulItemTags.VEGETABLES_SPICY);

		// Fruits
		this.tag(DelightfulItemTags.FRUITS_APPLE).add(Items.APPLE);
		this.tag(DelightfulItemTags.FRUITS_MELON).add(Items.MELON_SLICE);
		this.tag(DelightfulItemTags.FRUITS_CHORUS).add(Items.CHORUS_FRUIT);
		this.tag(DelightfulItemTags.FRUITS_SWEET_BERRIES).add(Items.SWEET_BERRIES);
		this.tag(DelightfulItemTags.FRUITS_GLOW_BERRIES).add(Items.GLOW_BERRIES);
		this.tag(DelightfulItemTags.FRUITS_CANTALOUPE).add(DelightfulItems.CANTALOUPE_SLICE.get());
		this.tag(DelightfulItemTags.FRUITS_SALMONBERRIES).add(DelightfulItems.SALMONBERRIES.get());
		this.tag(DelightfulItemTags.FRUITS_KIWI).addOptional(Modid.HED.rl("kiwi"));
		this.tag(DelightfulItemTags.FRUITS_GREEN_APPLE).addOptional(Modid.BWG.rl(BWGCompat.green_apple));
		this.tag(DelightfulItemTags.FRUITS_YUCCA)
			.addOptional(Modid.AT.rl(AtmosphericCompat.YUCCA))
			.addOptional(Modid.BWG.rl(BWGCompat.yucca));
		this.tag(DelightfulItemTags.FRUITS_BLUEBERRIES)
			.addOptional(Modid.HH.rl("blueberries"))
			.addOptional(Modid.BWG.rl("blueberries"))
			.addOptional(Modid.WB.rl("blueberries"))
			.addOptional(Modid.NF.rl("blueberries"));
		this.tag(DelightfulItemTags.FRUITS_CURRANT)
			.addOptionalTag(Modid.LOADER.rl("fruits/blackcurrant"))
			.addOptional(Modid.RC.rl("blackcurrant"));
		this.tag(DelightfulItemTags.FRUITS_REDCURRANT).addOptional(Modid.RC.rl("redcurrant"));
		this.tag(DelightfulItemTags.FRUITS_WHITECURRANT).addOptional(Modid.RC.rl("whitecurrant"));
		this.tag(DelightfulItemTags.FRUITS_BAOBAB).addOptional(Modid.BWG.rl(BWGCompat.baobab));
		this.tag(DelightfulItemTags.FRUITS_PRICKLY_PEAR).addOptional(Modid.ECO.rl("prickly_pear"));
		this.tag(DelightfulItemTags.FRUITS_TORCHBERRIES).addOptional(Modid.TF.rl("torchberries"));
		this.tag(DelightfulItemTags.FRUITS_SOURCEBERRY).addOptional(Modid.AN.rl("sourceberry_bush"));
		this.tag(DelightfulItemTags.FRUITS_ELDERBERRY).addOptional(Modid.RC.rl("elderberry"));
		this.tag(DelightfulItemTags.FRUITS_GEARO_BERRY).addOptional(Modid.VD.rl("gearo_berry"));
		this.tag(DelightfulItemTags.FRUITS_RASPBERRIES)
			.addOptional(Modid.HH.rl("raspberry"))
			.addOptional(Modid.WB.rl("raspberry"));
		this.tag(DelightfulItemTags.FRUITS_BLACKBERRIES).addOptional(Modid.WB.rl("blackberry"));
		this.tag(DelightfulItemTags.FRUITS_CRANBERRIES).addOptional(Modid.WB.rl("cranberries"));
		this.tag(DelightfulItemTags.FRUITS_WILD_BERRIES).addOptional(Modid.WS.rl("wild_berries"));
		this.tag(DelightfulItemTags.FRUITS_CHERRY)
			.addOptional(Modid.HH.rl("cherry"))
			.addOptional(Modid.FA.rl("cherry_peach"));
		this.tag(DelightfulItemTags.FRUITS_PLUM).addOptional(Modid.ENV.rl("plum"));
		this.tag(DelightfulItemTags.FRUITS_BLOOD_ORANGE).addOptional(Modid.AT.rl("blood_orange"));
		this.tag(DelightfulItemTags.FRUITS_DRAGON_FRUIT).addOptionalTag(Modid.LOADER.rl("fruits/pitaya"));
		this.tag(DelightfulItemTags.FRUITS).replace(false)
			.addTag(CommonTags.Items.BERRIES)
			.addTag(DelightfulItemTags.FRUITS_CITRUS)
			.addTag(DelightfulItemTags.FRUITS_CHORUS)
			.addTag(DelightfulItemTags.FRUITS_GREEN_APPLE)
			.addTag(DelightfulItemTags.FRUITS_YUCCA)
			.addOptionalTag(Modid.LOADER.rl("black_grapes"))
			.addOptionalTag(Modid.LOADER.rl("green_grapes"))
			.addOptional(Modid.AN.rl("mendosteen_pod"))
			.addOptional(Modid.AN.rl("bombegranate_pod"))
			.addOptional(Modid.AN.rl("frostaya_pod"))
			.addOptional(Modid.AN.rl("bastion_pod"))
			.addOptional(Modid.EE.rl("bolloom_fruit"))
			.addOptional(Modid.EP.rl("oblifruit"))
			.addOptional(Modid.UG.rl("droopvine_item"))
			.addOptional(Util.rl("ars_elemental", "flashpine_pod"));
		this.tag(CommonTags.Items.BERRIES).replace(false)
			.addTag(DelightfulItemTags.FRUITS_SWEET_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_GLOW_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.addTag(DelightfulItemTags.FRUITS_TORCHBERRIES)
			.addTag(DelightfulItemTags.FRUITS_SOURCEBERRY)
			.addTag(DelightfulItemTags.FRUITS_ELDERBERRY)
			.addTag(DelightfulItemTags.FRUITS_CURRANT)
			.addTag(DelightfulItemTags.FRUITS_REDCURRANT)
			.addTag(DelightfulItemTags.FRUITS_WHITECURRANT)
			.addTag(DelightfulItemTags.FRUITS_BLUEBERRIES)
			.addTag(DelightfulItemTags.FRUITS_RASPBERRIES)
			.addTag(DelightfulItemTags.FRUITS_BLACKBERRIES)
			.addTag(DelightfulItemTags.FRUITS_CRANBERRIES)
			.addTag(DelightfulItemTags.FRUITS_CHERRY)
			.addTag(DelightfulItemTags.FRUITS_WILD_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_GEARO_BERRY)
			.addOptionalTag(Modid.LOADER.rl("fruits/strawberry"))
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
			.addOptional(Modid.DA.rl("goldenleaf_berries"))
			.addOptional(Modid.EXQ.rl("midnight_berries"));
		this.tag(DelightfulItemTags.FRUITS_CITRUS).replace(false)
			.addTag(DelightfulItemTags.FRUITS_CITRON)
			.addTag(DelightfulItemTags.FRUITS_BLOOD_ORANGE)
			.addOptionalTag(DelightfulItemTags.FRUITS_ORANGE.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_MANDARIN.location())
			.addOptionalTag(DelightfulItemTags.FRUITS_LIME.location())
			.addOptionalTag(Modid.LOADER.rl("fruits/lemon"))
			.addOptionalTag(Modid.LOADER.rl("fruits/grapefruit"))
			.addOptionalTag(Modid.LOADER.rl("fruits/pomelo"));

		// Crab
		this.tag(DelightfulItemTags.RAW_CRAB) // Whole Crab
			.addOptional(Modid.CRAB.rl("crab"))
			.addOptionalTag(Util.rl("finsandtails", "spindly_gem_crabs"));
		this.tag(DelightfulItemTags.COOKED_CRAB) // Whole Crab
			.addOptional(Modid.CRAB.rl("cooked_crab"));
		// Cooked Crab Meat or Legs
		this.tag(DelightfulItemTags.RAW_CRAB_MEAT)
			.addOptional(Modid.Q.rl("crab_leg"));
		this.tag(DelightfulItemTags.COOKED_CRAB_MEAT)
			.addOptional(Modid.CRAB.rl("crab_legs"))
			.addOptional(Modid.ECO.rl("crab_meat"))
			.addOptional(Modid.Q.rl("cooked_crab_leg"));
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
		this.tag(DelightfulItemTags.STORAGE_BLOCKS_SALMONBERRIES).add(DelightfulItems.SALMONBERRY_SACK.get());
		this.tag(DelightfulItemTags.STORAGE_BLOCKS_ACORN).add(DelightfulItems.ACORN_SACK.get());
		this.tag(DelightfulItemTags.STORAGE_BLOCKS_BLUEBERRIES).add(DelightfulItems.BLUEBERRY_SACK.get());
		this.tag(DelightfulItemTags.STORAGE_BLOCKS_MENDOSTEEN).add(DelightfulItems.MENDOSTEEN_CRATE.get());
		this.tag(DelightfulItemTags.STORAGE_BLOCKS_BASTION_FRUIT).add(DelightfulItems.BASTION_FRUIT_CRATE.get());
		this.tag(DelightfulItemTags.STORAGE_BLOCKS_FROSTAYA).add(DelightfulItems.FROSTAYA_CRATE.get());
		this.tag(DelightfulItemTags.STORAGE_BLOCKS_BOMBEGRANATE).add(DelightfulItems.BOMBEGRANATE_CRATE.get());
		this.tag(DelightfulItemTags.STORAGE_BLOCKS_GREEN_APPLE).add(DelightfulItems.GREEN_APPLE_CRATE.get());
		this.tag(DelightfulItemTags.STORAGE_BLOCKS_YUCCA_FRUIT).add(DelightfulItems.YUCCA_FRUIT_CRATE.get());
		this.tag(DelightfulItemTags.STORAGE_BLOCKS_BAOBAB_FRUIT).add(DelightfulItems.BAOBAB_FRUIT_CRATE.get());
		this.tag(Tags.Items.STORAGE_BLOCKS)
			.addTag(DelightfulItemTags.STORAGE_BLOCKS_SALMONBERRIES)
			.addTag(DelightfulItemTags.STORAGE_BLOCKS_ACORN)
			.addTag(DelightfulItemTags.STORAGE_BLOCKS_BLUEBERRIES)
			.addTag(DelightfulItemTags.STORAGE_BLOCKS_MENDOSTEEN)
			.addTag(DelightfulItemTags.STORAGE_BLOCKS_BASTION_FRUIT)
			.addTag(DelightfulItemTags.STORAGE_BLOCKS_FROSTAYA)
			.addTag(DelightfulItemTags.STORAGE_BLOCKS_BOMBEGRANATE)
			.addTag(DelightfulItemTags.STORAGE_BLOCKS_GREEN_APPLE)
			.addTag(DelightfulItemTags.STORAGE_BLOCKS_YUCCA_FRUIT)
			.addTag(DelightfulItemTags.STORAGE_BLOCKS_BAOBAB_FRUIT);
		this.tag(Tags.Items.MUSHROOMS).addOptional(Modid.FORA.rl("blewit_mushroom"));
		this.tag(Tags.Items.TOOLS_FISHING_RODS).addOptional(Modid.RA.rl("flux_fishing_rod"));
		this.tag(DelightfulItemTags.TOOLS_WRENCH).addOptional(Modid.FOR.rl("wrench"));
		this.tag(ItemTags.TOOLS).addTag(DelightfulItemTags.TOOLS_WRENCH);
		this.tag(DelightfulItemTags.WRENCHES).addTag(DelightfulItemTags.TOOLS_WRENCH);
		this.tag(DelightfulItemTags.ROE)
			.add(DelightfulItems.STURGEON_ROE.get())
			.addOptional(Modid.LFL.rl("cod_roe"))
			.addOptional(Modid.LFL.rl("pufferfish_roe"))
			.addOptional(Modid.LFL.rl("salmon_roe"))
			.addOptional(Modid.LFL.rl("tropical_fish_roe"))
			.addOptional(Modid.LMFL.rl("bass_roe"))
			.addOptional(Modid.LMFL.rl("blizzard_fin_roe"))
			.addOptional(Modid.LMFL.rl("catfish_roe"))
			.addOptional(Modid.LMFL.rl("koi_roe"))
			.addOptional(Modid.LMFL.rl("lanternfish_roe"))
			.addOptional(Modid.LMFL.rl("lionfish_roe"))
			.addOptional(Modid.LMFL.rl("perch_roe"))
			.addOptional(Modid.LMFL.rl("pike_roe"))
			.addOptional(Modid.CR.rl("tiger_prawn_roe"));
		this.tag(DelightfulItemTags.WATER)
			.addTag(CommonTags.Items.BUCKETS_WATER)
			.addOptional(Modid.MD.rl("water_cup"));
		this.tag(DelightfulItemTags.FLOWERS_AZALEA)
			.addOptional(Modid.ECO.rl("azalea_flower"))
			.addOptional(Modid.TWIG.rl("azalea_flowers"));
		this.tag(DelightfulItemTags.FLOWERS_LAVENDER)
			.addOptional(Modid.WS.rl("lavender"))
			.addOptional(Modid.BOP.rl("lavender"))
			.addOptional(Modid.BOP.rl("tall_lavender"));
		this.tag(DelightfulItemTags.CLOVER)
			.addOptional(Modid.BOP.rl("clover"))
			.addOptional(Modid.BWG.rl("clover_patch"))
			.addOptional(Modid.TF.rl("clover_patch"))
			.addOptional(Util.rl("regions_unexplored", "clover"));
		this.tag(DelightfulItemTags.CACTI)
			// Large
			.add(Items.CACTUS)
			.addOptional(Modid.BWG.rl("barrel_cactus"))
			.addOptional(Modid.BWG.rl("flowering_barrel_cactus"))
			.addOptional(Modid.BWG.rl("prickly_pear_cactus"))
			.addOptional(Modid.BWG.rl("golden_spined_cactus"))
			.addOptional(Modid.HAB.rl("ball_cactus_block"))
			.addOptional(Modid.BM.rl("saguaro_cactus"))
			// Small
			.addOptionalTag(Modid.HAB.rl("ball_cacti"))
			.addOptional(Modid.BWG.rl("mini_cactus"))
			.addOptional(Modid.BOP.rl("tiny_cactus"))
			.addOptional(Modid.AT.rl("barrel_cactus"))
			.addOptional(Modid.BM.rl("barrel_cactus"))
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
			.addOptional(Modid.AUT.rl("turkey_egg"))
			.addOptional(Modid.DA.rl("quail_egg"))
			.addOptional(Util.rl("farmlife", "galliraptor_egg"))
			.addOptional(Util.rl("etcetera", "eggple"));
		this.tag(CommonTags.Items.COOKED_EGGS)
			.addOptional(Modid.AA.rl("fried_egg"))
			.addOptional(Modid.IN.rl("fried_egg"))
			.addOptional(Modid.NA.rl("cooked_egg"));
		this.tag(DelightfulItemTags.DUSTS_SALT)
			.addOptional(Modid.HH.rl("salt"))
			.addOptional(Modid.VGND.rl("salt"));
		this.tag(DelightfulItemTags.SALT).addTag(DelightfulItemTags.DUSTS_SALT);
		this.tag(DelightfulItemTags.DUSTS_FLOUR_WHEAT)
			.addOptional(Modid.CRE.rl("wheat_flour"))
			.addOptional(Modid.PN.rl("wheat_flour"))
			.addOptional(Modid.EIO.rl("flour"));
		this.tag(DelightfulItemTags.DUSTS_FLOUR)
			.addTag(DelightfulItemTags.DUSTS_FLOUR_WHEAT)
			.addOptionalTag(DelightfulItemTags.DUSTS_FLOUR_NUT.location());
		this.tag(DelightfulItemTags.FLOUR).addTag(DelightfulItemTags.DUSTS_FLOUR);
		this.tag(DelightfulItemTags.DUSTS_ENDER_PEARL)
			.addOptional(Modid.TH.rl("ender_pearl_dust"))
			.addOptional(Modid.EIO.rl("powdered_ender_pearl"))
			.addOptional(Modid.AE2.rl("ender_dust"));
		this.tag(DelightfulItemTags.DUSTS_WOOD).addOptional(Modid.FOR.rl("wood_pulp"));
		this.tag(Tags.Items.DUSTS)
			.addTag(DelightfulItemTags.DUSTS_SALT)
			.addTag(DelightfulItemTags.DUSTS_FLOUR)
			.addTag(DelightfulItemTags.DUSTS_ENDER_PEARL)
			.addTag(DelightfulItemTags.DUSTS_WOOD)
			.addOptional(Modid.AND.rl("wilden_horn_powder"))
			.addOptional(Modid.AND.rl("wilden_spike_powder"))
			.addOptional(Modid.DD.rl("soul_dust"))
			.addOptional(Modid.RA.rl("flux_dust"))
			.addOptionalTag(Modid.LOADER.rl("dusts/prismalium"))
			.addOptionalTag(Modid.LOADER.rl("dusts/melodium"))
			.addOptionalTag(Modid.LOADER.rl("dusts/stellarium"));
		this.tag(DelightfulItemTags.DOUGH_CORN).addOptional(Modid.CD.rl("corn_dough"));
		this.tag(DelightfulItemTags.DOUGH_NUT).add(DelightfulItems.NUT_DOUGH.get());
		this.tag(CommonTags.Items.DOUGH)
			.addTag(DelightfulItemTags.DOUGH_NUT)
			.addTag(DelightfulItemTags.DOUGH_CORN);
		this.tag(DelightfulItemTags.CORNBREAD)
			.addOptional(Modid.CORN.rl("cornbread"))
			.addOptional(Modid.HH.rl("cornbread"));
		this.tag(DelightfulItemTags.BURGER_BUN).addOptional(Modid.SAS.rl("burger_bun"));
		this.tag(DelightfulItemTags.PANCAKES)
			.addOptional(Modid.SUP.rl("pancake"))
			.addOptional(Modid.AUT.rl("pancake"));
		this.tag(CommonTags.Items.BREAD)
			.addTag(DelightfulItemTags.CORNBREAD)
			.addTag(DelightfulItemTags.BURGER_BUN);
		this.tag(DelightfulItemTags.HOT_SPICES)
			.add(Items.BLAZE_POWDER)
			.addTag(DelightfulItemTags.VEGETABLES_SPICY)
			.addOptionalTag(Modid.MND.rl("hot_spice"));
		this.tag(DelightfulItemTags.AGED_ROES)
			.add(DelightfulItems.AGED_ROE.get())
			.add(DelightfulItems.CAVIAR.get());
		this.tag(DelightfulItemTags.PUMPKINS_PUMPKIN_BLOCKS)
			.add(Items.PUMPKIN)
			.add(Items.CARVED_PUMPKIN)
			.addOptional(Modid.AUT.rl("large_pumpkin_slice"))
			.addOptional(Modid.AUT.rl("carved_large_pumpkin_slice"));
		this.tag(DelightfulItemTags.PUMPKINS)
			.addTag(DelightfulItemTags.PUMPKINS_PUMPKIN_BLOCKS)
			.add(ModItems.PUMPKIN_SLICE.get());
		this.tag(DelightfulItemTags.COCONUT).addOptional(Modid.ECO.rl("coconut_slice"));
		this.tag(DelightfulItemTags.NUTS_ACORN)
			.add(DelightfulItems.ACORN.get())
			.addOptional(Modid.SEED.rl("acorn"))
			.addOptional(Modid.FORA.rl("black_acorn"));
		this.tag(DelightfulItemTags.NUTS_WALNUT)
			.addOptionalTag(Modid.LOADER.rl("walnut"))
			.addOptionalTag(Modid.LOADER.rl("fruits/walnut"))
			.addOptional(Modid.ECO.rl("walnut"))
			.addOptional(Util.rl("caupona", "walnut"));
		this.tag(DelightfulItemTags.NUTS_PEANUT)
			.addOptionalTag(Modid.LOADER.rl("peanut"))
			.addOptionalTag(Modid.LOADER.rl("fruits/peanut"))
			.addOptionalTag(Modid.LOADER.rl("crops/peanut"))
			.addOptional(Modid.HH.rl("peanut"))
			.addOptional(Util.rl("sprout", "peanut"));
		this.tag(DelightfulItemTags.NUTS)
			.addTag(DelightfulItemTags.NUTS_ACORN)
			.addTag(DelightfulItemTags.NUTS_WALNUT)
			.addTag(DelightfulItemTags.NUTS_PEANUT)
			.addOptional(Modid.WS.rl("chestnuts"))
			.addOptional(Modid.THI.rl("hazelnut"))
			.addOptional(Util.rl("alexscaves", "pine_nuts"));
		this.tag(DelightfulItemTags.COOKED_NUTS)
			.add(DelightfulItems.ROASTED_ACORN.get())
			.addOptional(Modid.WS.rl("roasted_chestnuts"))
			.addOptional(Modid.SOB.rl("roasted_peanuts"))
			.addOptional(Modid.HH.rl("roasted_peanuts"))
			.addOptional(Modid.VD.rl("roasted_peanut"));
		this.tag(Tags.Items.STRING).addOptional(Modid.AN.rl("magebloom_fiber"));
		this.tag(DelightfulItemTags.JAMS_GLOW)
			.add(DelightfulItems.GLOW_JAM_JAR.get())
			.addOptionalTag(Modid.LOADER.rl("jams/glow_berries"))
			.addOptionalTag(Modid.LOADER.rl("jams/glow_berry"))
			.addOptional(BrewinChewinCompat.glowMarmalade);
		this.tag(DelightfulItemTags.JAMS)
			.addTag(DelightfulItemTags.JAMS_GLOW)
			.add(DelightfulItems.JAM_JAR.get())
			.add(DelightfulItems.GLOW_JAM_JAR.get())
			.addOptionalTag(Modid.LOADER.rl("jam"))
			.addOptionalTag(Modid.LOADER.rl("jam_bottles"))
			.addOptionalTag(Modid.SOB.rl("ingredients/preserves"))
			.addOptionalTag(Modid.HH.rl("jelly"))
			.addOptionalTag(Modid.WB.rl("berry_jams"))
			.addOptionalTag(Modid.VD.rl("sweet_jam_bottles"))
			.addOptional(Modid.BC.rl("sweet_berry_jam"))
			.addOptional(Modid.BC.rl("apple_jelly"))
			.addOptional(Modid.AND.rl("source_berry_jam"))
			.addOptional(Modid.AND.rl("activated_mendosteen_jam"))
			.addOptional(Modid.AND.rl("activated_bastion_jam"))
			.addOptional(Modid.AND.rl("neutralized_frostaya_jam"))
			.addOptional(Modid.AND.rl("neutralized_bombegrante_jam")) // Intentionally misspelled
			.addOptional(Modid.NE.rl("jaboticaba_juice_bottle"))
			.addOptional(Modid.NE.rl("jaboticaba_juice_fire_resistance"))
			.addOptional(Modid.NE.rl("jaboticaba_juice_night_vision"))
			.addOptional(Modid.NE.rl("jaboticaba_juice_saturation"))
			.addOptional(Modid.NE.rl("jaboticaba_juice_wither_cure"))
			.addOptional(Modid.SEED.rl("cherry_jam_jar"))
			.addOptional(Modid.SEED.rl("rosehip_jam_jar"))
			.addOptional(Modid.UA.rl("mulberry_jam_bottle"))
			.addOptional(Modid.WS.rl("pinecone_jam_bottle"))
			.addOptional(Modid.TH.rl("jelly"));
		this.tag(DelightfulItemTags.PEANUT_BUTTER)
			.addOptional(Modid.HH.rl("peanut_butter"))
			.addOptional(Modid.TH.rl("peanut_butter"))
			.addOptional(Modid.CT.rl("peanut_butter"));
		this.tag(DelightfulItemTags.NUT_BUTTER)
			.addTag(DelightfulItemTags.PEANUT_BUTTER)
			.add(DelightfulItems.NUT_BUTTER_BOTTLE.get())
			.addOptional(Modid.THI.rl("hazelbutter"))
			.addOptional(Modid.VD.rl("nut_mash_bottle"));
		this.tag(CommonTags.Items.MILK_BUCKET)
			.addOptional(Modid.AE.rl("skyroot_milk_bucket"))
			.addOptional(Modid.BS.rl("ventium_milk_bucket"))
			.addOptional(Modid.FA.rl("edelwood_milk_bucket"));
		this.tag(DelightfulItemTags.CHEESE_MILD)
			.addOptional(Modid.BC.rl("flaxen_cheese_wedge"))
			.addOptional(Modid.HH.rl("cheddar_cheese_slice"))
			.addOptional(Modid.HH.rl("goat_cheese_slice"))
			.addOptional(Modid.CT.rl("cheese"))
			.addOptional(Modid.TH.rl("cheese_wedge"))
			.addOptional(Modid.CAD.rl("cheese_wheel_slice"))
			.addOptional(Util.rl("ad_astra", "cheese"));
		this.tag(DelightfulItemTags.CHEESE_SPICY).addOptional(Modid.BC.rl("scarlet_cheese_wedge"));
		this.tag(DelightfulItemTags.CHEESE_SWEET)
			.addOptional(Modid.SOB.rl("eumozz_cheese_wedge"))
			.addOptional(Util.rl("farmlife", "tribull_cheese_wedge"));
		this.tag(DelightfulItemTags.CHEESE)
			.addTag(DelightfulItemTags.CHEESE_MILD)
			.addTag(DelightfulItemTags.CHEESE_SPICY)
			.addTag(DelightfulItemTags.CHEESE_SWEET)
			.addOptionalTag(Modid.LOADER.rl("cheeses"))
			.addOptionalTag(Modid.BC.rl("cheese_wedges"));
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
		this.tag(DelightfulItemTags.RAW_FISHES_STURGEON).addOptional(Modid.TIDE.rl("sturgeon"));
		this.tag(DelightfulItemTags.RAW_SQUID)
			.addOptional(Modid.MD.rl("squid"))
			.addOptional(Modid.MD.rl("glow_squid"))
			.addOptional(Modid.CD.rl("squid"))
			.addOptional(Modid.CD.rl("glow_squid"));
		this.tag(DelightfulItemTags.RAW_FISHES_SQUID_TENTACLES)
			.addOptional(Modid.MD.rl("tentacles"))
			.addOptional(Modid.CD.rl("raw_calamari"))
			.addOptional(Modid.OD.rl("tentacles"));
		this.tag(DelightfulItemTags.RAW_FISHES_SQUID)
			.addTag(DelightfulItemTags.RAW_FISHES_SQUID_TENTACLES)
			.addTag(DelightfulItemTags.RAW_SQUID);
		this.tag(CommonTags.Items.RAW_FISHES)
			.addTag(DelightfulItemTags.RAW_FISHES_STURGEON)
			.addTag(DelightfulItemTags.RAW_FISHES_SQUID)
			.addOptional(Modid.SF.rl("goliath_grouper"))
			.addOptional(Modid.SF.rl("vampire_payara"))
			.addOptional(Modid.SF.rl("golden_snook"))
			.addOptional(Modid.SF.rl("sabretoothed_tigerfish"))
			.addOptional(Modid.SF.rl("chromatic_arapaima"))
			.addOptional(Modid.SF.rl("cyclops_mahimahi"))
			.addOptional(Modid.SF.rl("storm_tarpon"))
			.addOptional(Modid.SF.rl("blazing_oarfish"))
			.addOptional(Modid.SF.rl("crystalline_snakehead"))
			.addOptional(Modid.SF.rl("demon_gar"))
			.addOptional(Modid.UG.rl("raw_gwibling"))
			.addOptional(Modid.DA.rl("raw_aerglow_fish"))
			.addOptional(Modid.BM.rl("glowfish"))
			.addOptional(Modid.CRIT.rl("koi_fish"))
			.addOptional(Modid.SPWN.rl("tuna_chunk"))
			.addOptional(Modid.SPWN.rl("angler_fish"))
			.addOptional(Util.rl("bettas", "betta_fish"));
		this.tag(DelightfulItemTags.COOKED_FISHES_SQUID)
			.addOptional(Modid.CD.rl("cooked_squid"))
			.addOptional(Modid.CD.rl("cooked_calamari"));
		this.tag(CommonTags.Items.COOKED_FISHES)
			.addTag(DelightfulItemTags.COOKED_FISHES_SQUID)
			.addOptionalTag(Modid.TIDE.rl("cooked_fish"))
			.addOptional(Modid.TIDE.rl("cooked_fish_slice"))
			.addOptional(Modid.UG.rl("cooked_gwibling"))
			.addOptional(Modid.DA.rl("cooked_aerglow_fish"))
			.addOptional(Modid.BM.rl("cooked_glowfish"))
			.addOptional(Modid.SPWN.rl("cooked_tuna_chunk"));
		this.tag(DelightfulItemTags.RAW_VENISON_COMPAT)
			.addOptional(Modid.ENV.rl("venison"))
			.addOptional(Modid.NA.rl("venison"))
			.addOptional(Modid.TF.rl("raw_venison"))
			.addOptional(Modid.GOOD.rl("raw_venison"))
			.addOptional(Modid.BS.rl("venison"));
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
			.addOptional(Modid.GOOD.rl("cooked_venison"))
			.addOptional(Modid.BS.rl("cooked_venison"));
		this.tag(DelightfulItemTags.COOKED_VENISON_CHOP_COMPAT)
			.addOptional(Modid.AD.rl("cooked_venison_shanks"))
			.addOptional(Modid.TFD.rl("cooked_venison_rib"));
		this.tag(DelightfulItemTags.COOKED_VENISON)
			.add(DelightfulItems.COOKED_VENISON_CHOPS.get())
			.addTag(DelightfulItemTags.COOKED_VENISON_CHOP_COMPAT)
			.addTag(DelightfulItemTags.COOKED_VENISON_COMPAT);
		this.tag(DelightfulItemTags.RAW_GOAT)
			.add(DelightfulItems.RAW_GOAT.get())
			.addOptional(Modid.FUNC.rl("chevon"))
			.addOptional(Modid.EXC.rl("chevon_chop"));
		this.tag(DelightfulItemTags.COOKED_GOAT)
			.add(DelightfulItems.COOKED_GOAT.get())
			.addOptional(Modid.FUNC.rl("cooked_chevon"))
			.addOptional(Modid.EXC.rl("cooked_chevon_chop"));
		this.tag(DelightfulItemTags.RAW_DUCK).addOptional(Modid.NA.rl("duck"));
		this.tag(DelightfulItemTags.COOKED_DUCK).addOptional(Modid.NA.rl("cooked_duck"));
		this.tag(DelightfulItemTags.FOODS_MEAT_RAW)
			.addOptionalTag(Modid.LOADER.rl("raw_meat"))
			.addTag(CommonTags.Items.RAW_FISHES)
			.addTag(CommonTags.Items.RAW_CHICKEN)
			.addTag(CommonTags.Items.RAW_BEEF)
			.addTag(CommonTags.Items.RAW_PORK)
			.addTag(CommonTags.Items.RAW_MUTTON)
			.addTag(DelightfulItemTags.RAW_RABBIT)
			.addTag(DelightfulItemTags.RAW_VENISON)
			.addTag(DelightfulItemTags.RAW_CRAB)
			.addTag(DelightfulItemTags.RAW_GOAT)
			.addOptionalTag(Modid.LOADER.rl("raw_duck"))
			.addOptionalTag(Modid.LOADER.rl("raw_turkey"))
			.addOptional(Modid.NA.rl("bushmeat"))
			.addOptional(Modid.AND.rl("wilden_meat"))
			.addOptional(Modid.AND.rl("wilden_meat_slice"))
			.addOptional(Modid.AND.rl("chimera_meat"))
			.addOptional(Modid.AND.rl("chimera_meat_slice"))
			.addOptional(Modid.CAD.rl("raw_donkey_meat"))
			.addOptional(Modid.UG.rl("raw_dweller_meat"))
			.addOptional(Modid.UG.rl("raw_gloomper_leg"))
			.addOptional(Modid.EP.rl("behemoth_meat"))
			.addOptional(Modid.FUNC.rl("frog_leg"))
			.addOptional(Util.rl("frog_legs", "frog_legs"));
		this.tag(DelightfulItemTags.FOODS_MEAT_COOKED)
			.addOptionalTag(Modid.LOADER.rl("cooked_meat"))
			.addTag(CommonTags.Items.COOKED_FISHES)
			.addTag(CommonTags.Items.COOKED_CHICKEN)
			.addTag(CommonTags.Items.COOKED_BEEF)
			.addTag(CommonTags.Items.COOKED_PORK)
			.addTag(CommonTags.Items.COOKED_MUTTON)
			.addTag(DelightfulItemTags.COOKED_RABBIT)
			.addTag(DelightfulItemTags.COOKED_VENISON)
			.addTag(DelightfulItemTags.COOKED_CRAB)
			.addTag(DelightfulItemTags.COOKED_GOAT)
			.addOptionalTag(Modid.LOADER.rl("cooked_duck"))
			.addOptionalTag(Modid.LOADER.rl("cooked_turkey"))
			.addOptional(Modid.NA.rl("cooked_bushmeat"))
			.addOptional(Modid.AND.rl("grilled_wilden_meat"))
			.addOptional(Modid.AND.rl("grilled_wilden_meat_slice"))
			.addOptional(Modid.AND.rl("grilled_chimera_meat"))
			.addOptional(Modid.AND.rl("grilled_chimera_meat_slice"))
			.addOptional(Modid.CAD.rl("cooked_donkey_meat"))
			.addOptional(Modid.UG.rl("dweller_steak"))
			.addOptional(Modid.UG.rl("gloomper_leg"))
			.addOptional(Modid.EP.rl("behemoth_steak"))
			.addOptional(Modid.FUNC.rl("cooked_frog_leg"))
			.addOptional(Util.rl("frog_legs", "cooked_frog_legs"));
		this.tag(DelightfulItemTags.FOODS_MEAT)
			.addTag(DelightfulItemTags.FOODS_MEAT_RAW)
			.addTag(DelightfulItemTags.FOODS_MEAT_COOKED)
			.addTag(DelightfulItemTags.COOKED_CRAB_MEAT);
		this.tag(DelightfulItemTags.CATTAIL)
			.addOptional(Modid.BOP.rl("cattail"))
			.addOptional(Util.rl("sprout", "cattail"))
			.addOptional(Modid.BM.rl("cattail"));
		this.tag(DelightfulItemTags.GEMS_ROSE_QUARTZ)
			.addOptional(Modid.BOP.rl("rose_quartz_chunk"))
			.addOptional(Modid.CRE.rl("rose_quartz"));
		this.tag(DelightfulItemTags.GEMS_ZANITE).addOptional(Modid.AE.rl("zanite_gemstone"));
		this.tag(DelightfulItemTags.CHOCOLATE)
			.addOptionalTag(Modid.LOADER.rl("chocolates"))
			.addOptionalTag(Modid.LOADER.rl("bars/chocolate"))
			.addOptionalTag(Modid.LOADER.rl("chocolatebar"))
			.addOptionalTag(Modid.SUP.rl("chocolate_bars"))
			.addOptional(Modid.N.rl("chocolate_bar"))
			.addOptional(Modid.CRE.rl("bar_of_chocolate"))
			.addOptional(Modid.HH.rl("chocolate_bar"));
		this.tag(DelightfulItemTags.SEEDS_SALMONBERRY).add(DelightfulItems.SALMONBERRY_PIPS.get());
		this.tag(DelightfulItemTags.SEEDS_CANTALOUPE).add(DelightfulItems.CANTALOUPE_SEEDS.get());
		this.tag(CommonTags.Items.SEEDS)
			.addTag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
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
			.addOptional(Modid.UA.rl("thrasher_tooth"))
			.addOptional(Modid.TIDE.rl("fish_bone"))
			.addOptional(Modid.DD.rl("sculk_bone"))
			.addOptional(Modid.SM.rl("piranha_tooth"))
			.addOptional(Util.rl("alexscaves", "heavy_bone"));
		this.tag(DelightfulItemTags.SYRUP_BOTTLE)
			.addOptional(Modid.AUT.rl("syrup_bottle"))
			.addOptional(Modid.HH.rl("syrup_bottle"))
			.addOptional(Modid.TH.rl("syrup_bottle"))
			.addOptional(Modid.FORA.rl("birch_syrup_bottle"));
		this.tag(DelightfulItemTags.SYRUP)
			.addTag(DelightfulItemTags.SYRUP_BOTTLE)
			.addOptional(Modid.TH.rl("syrup_bucket"));
		this.tag(DelightfulItemTags.COOKIES_SOURCE_BERRY)
			.add(DelightfulItems.SOURCE_BERRY_COOKIE.get())
			.addOptional(Modid.AND.rl("source_berry_cookie"));
		this.tag(DelightfulItemTags.COOKIES)
			.addTag(DelightfulItemTags.COOKIES_SOURCE_BERRY)
			.add(Items.COOKIE)
			.add(ModItems.HONEY_COOKIE.get())
			.add(ModItems.SWEET_BERRY_COOKIE.get())
			.add(DelightfulItems.GLOW_JAM_COOKIE.get())
			.addOptional(Modid.FR.rl("green_tea_cookie"))
			.addOptional(Modid.AD.rl("mulberry_cookie"))
			.addOptional(Modid.AD.rl("maple_cookie"))
			.addOptional(Modid.AD.rl("cherry_cookie"))
			.addOptional(Modid.MD.rl("bat_cookie"))
			.addOptional(Modid.VD.rl("oatmeal_cookie"))
			.addOptional(Modid.HH.rl("peanut_butter_cookie"))
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
			.addOptional(Modid.EXQ.rl("ether_bulb_cookie"))
			.addOptional(Modid.EXQ.rl("nightshade_berry_cookie"))
			.addOptional(Modid.EXQ.rl("chorus_cookie"))
			.addOptional(Modid.END.rl("chorus_cookie"))
			.addOptional(Modid.ENDR.rl("uncanny_cookie"))
			.addOptional(Modid.SEED.rl("rose_cookie"))
			.addOptional(Modid.SS.rl("gingerbread_cookie"))
			.addOptional(Modid.WS.rl("gingerbread_cookie"))
			.addOptional(Modid.FORA.rl("acorn_cookie"))
			.addOptional(Modid.FORA.rl("rose_cookie"))
			.addOptional(Util.rl("sunflowerdelight", "shortbread_cookie"))
			.addOptional(Util.rl("vampiresdelight", "orchid_cookie"))
			.addOptional(Util.rl("ramadandelight", "date_stuffed_cookie"));
		this.tag(DelightfulItemTags.ICE_CUBES)
			.addOptional(Modid.WS.rl("icicles"))
			.addOptional(Modid.FORA.rl("crushed_ice"));
		this.tag(DelightfulItemTags.ROPES)
			.add(ModItems.ROPE.get())
			.addOptionalTag(Modid.SUP.rl("ropes"))
			.addOptional(Modid.AA.rl("rope"));
		this.tag(DelightfulItemTags.FEATHERS).addOptional(Modid.ECO.rl("penguin_feather"));
		this.tag(DelightfulItemTags.SNAIL_SHELLS)
			.addOptional(Modid.NA.rl("snail_shell"))
			.addOptional(Modid.AUT.rl("snail_shell_piece"));
		this.tag(DelightfulItemTags.MARSHMALLOW)
			.addOptional(Util.rl("create_confectionery", "marshmallow"));
		this.tag(DelightfulItemTags.JUICES_MELON)
			.add(ModItems.MELON_JUICE.get())
			.addOptional(Modid.FR.rl("strong_melon_juice"));
		this.tag(DelightfulItemTags.JUICES_PRICKLY_PEAR)
			.add(DelightfulItems.PRICKLY_PEAR_JUICE.get())
			.add(DelightfulItems.LONG_PRICKLY_PEAR_JUICE.get())
			.addOptional(Modid.SOB.rl("cactus_juice"));
		this.tag(DelightfulItemTags.JUICES_ORANGE).addOptional(Modid.SOB.rl("orange_juice"));
		this.tag(Modid.LOADER.it("juices/orange_juice")).addTag(DelightfulItemTags.JUICES_ORANGE);
		this.tag(DelightfulItemTags.JUICES_LEMON).addOptional(Modid.FRD.rl("lemon_juice"));
		this.tag(Modid.LOADER.it("juices/lemon_juice")).addTag(DelightfulItemTags.JUICES_LEMON);
		this.tag(DelightfulItemTags.JUICES_CITRUS)
			.addTag(DelightfulItemTags.JUICES_ORANGE)
			.addTag(DelightfulItemTags.JUICES_LEMON)
			.addOptionalTag(Modid.LOADER.rl("juices/lime"))
			.addOptionalTag(Modid.LOADER.rl("juices/lime_juice"));
		this.tag(Modid.LOADER.it("juices/citrus_juice")).addTag(DelightfulItemTags.JUICES_CITRUS);
		this.tag(DelightfulItemTags.JUICES)
			.addTag(DelightfulItemTags.JUICES_MELON)
			.addTag(DelightfulItemTags.JUICES_PRICKLY_PEAR)
			.addTag(DelightfulItemTags.JUICES_CITRUS)
			.addOptional(Modid.FRD.rl("hamimelon_juice"))
			.addOptional(Modid.FRD.rl("pear_juice"))
			.addOptional(Modid.FRD.rl("kiwi_juice"))
			.addOptional(Modid.HH.rl("blueberry_juice"))
			.addOptional(Modid.HH.rl("cherry_juice"))
			.addOptional(Modid.HH.rl("raspberry_juice"))
			.addOptional(Modid.HH.rl("red_grape_juice"))
			.addOptional(Modid.HH.rl("green_grape_juice"))
			.addOptional(Modid.VEG.rl("carrot_juice"))
			.addOptional(Modid.VEG.rl("dandelion_juice"))
			.addOptional(Modid.VEG.rl("turnip_water"))
			.addOptional(Modid.WSD.rl("wild_berry_juice"))
			.addOptional(Modid.AD.rl("pickerelweed_juice"));
		this.tag(DelightfulItemTags.DRINKS_TEQUILA)
			.addOptional(Modid.CTD.rl("tequila"))
			.addOptional(Modid.SOB.rl("tequila"))
			.addOptional(Modid.DOLT.rl("tequila"));
		this.tag(ModTags.Items.DRINKS)
			.addTag(DelightfulItemTags.DRINKS_TEQUILA)
			.addTag(DelightfulItemTags.JUICES)
			.add(DelightfulItems.ENDER_NECTAR.get())
			.add(DelightfulItems.MATCHA_LATTE.get())
			.add(DelightfulItems.BERRY_MATCHA_LATTE.get())
			.add(DelightfulItems.AZALEA_TEA.get())
			.add(DelightfulItems.LAVENDER_TEA.get())
			.addOptionalTag(BrewinChewinCompat.FERMENTED_DRINKS.location())
			.addOptional(Modid.FR.rl("green_tea"))
			.addOptional(Modid.FR.rl("yellow_tea"))
			.addOptional(Modid.FR.rl("black_tea"))
			.addOptional(Modid.FR.rl("rose_hip_tea"))
			.addOptional(Modid.FR.rl("dandelion_tea"))
			.addOptional(Modid.FR.rl("purulent_tea"))
			.addOptional(Modid.FR.rl("gamblers_tea"))
			.addOptional(Modid.WS.rl("lavender_tea"))
			.addOptional(Modid.WS.rl("ginger_tea"))
			.addOptional(Modid.SS.rl("eggnog"));
		this.tag(DelightfulItemTags.DRINKS).addTag(ModTags.Items.DRINKS);

		// Collector's Reap
		this.tag(Modid.CR.it("gummies"))
			.add(DelightfulItems.SALMONBERRY_GUMMY.get())
			.add(DelightfulItems.MATCHA_GUMMY.get())
			.add(DelightfulItems.CANTALOUPE_GUMMY.get())
			.add(DelightfulItems.SOURCE_BERRY_GUMMY.get())
			.addOptional(Modid.EXQ.rl("chorus_gummy"))
			.addOptional(Modid.EXQ.rl("ether_bulb_gummy"))
			.addOptional(Modid.EXQ.rl("nightshade_berry_gummy"))
			.addOptional(Modid.EXQ.rl("warzipan_gummy"));

		// Brewin and Chewin
		this.tag(BrewinChewinCompat.FERMENTED_DRINKS)
			.addTag(DelightfulItemTags.AGED_ROES)
			.add(DelightfulItems.ENDER_NECTAR.get());
		this.tag(BrewinChewinCompat.PIZZA_TOPPINGS).addTag(DelightfulItemTags.PATTIES);

		// Nether's Delight
		this.tag(Modid.ND.it("meal_item"))
			.add(DelightfulItems.STUFFED_CANTALOUPE.get())
			.addOptional(Modid.MD.rl("bowl_of_stuffed_squid"));

		// My Nether's Delight
		this.tag(Modid.MND.it("curry_meats"))
			.addTag(DelightfulItemTags.RAW_VENISON)
			.addTag(DelightfulItemTags.RAW_GOAT);

		// Cosmopolitan
		this.tag(Modid.COS.it("tracer_sources"))
			.addOptional(Modid.BG.rl("glowgurt"));

		// Salt
		this.tag(Modid.S.it("can_be_salted"))
			.addTag(CommonTags.Items.COOKED_EGGS)
			.addTag(DelightfulItemTags.CHEESE)
			.addTag(DelightfulItemTags.COOKED_CRAB)
			.addTag(DelightfulItemTags.COOKED_GOAT)
			.addTag(DelightfulItemTags.COOKED_NUTS)
			.add(DelightfulItems.SINIGANG.get())
			.add(DelightfulItems.SINIGANG_CUP.get())
			.add(DelightfulItems.VENISON_STEW.get())
			.add(DelightfulItems.VENISON_STEW_CUP.get())
			.add(DelightfulItems.CACTUS_SOUP.get())
			.add(DelightfulItems.CACTUS_SOUP_CUP.get())
			.add(DelightfulItems.CACTUS_CHILI.get())
			.add(DelightfulItems.COCONUT_CURRY.get())
			.add(DelightfulItems.CACTUS_STEAK.get())
			.add(DelightfulItems.CHUNKWICH.get())
			.add(DelightfulItems.CRAB_RANGOON.get())
			.add(DelightfulItems.STUFFED_CANTALOUPE.get());

		// Supplementaries
		this.tag(Modid.SUP.it("cookies"))
			.addTag(DelightfulItemTags.COOKIES)
			.addOptional(Modid.CD.rl("tortilla_chips"))
			.addOptional(Modid.CORN.rl("tortilla_chip"));
		this.tag(Modid.SUP.it("pancake_syrup")).addTag(DelightfulItemTags.SYRUP_BOTTLE);

		// Forbidden and Arcanus
		this.addSelf(DelightfulItemTags.DRACO_ARCANUS_STAFF);
		this.addSelf(DelightfulItemTags.DRAGON_SCALE);

		// Deeper and Darker
		this.addSelf(DelightfulItemTags.REINFORCED_ECHO_SHARD);
		this.addSelf(DelightfulItemTags.RESONARIUM);
		this.addSelf(DelightfulItemTags.RESONARIUM_PLATE);

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
		this.tag(CompatibilityTags.CREATE_UPRIGHT_ON_BELT)
			.addTag(DelightfulItemTags.DRINKS)
			.addTag(DelightfulItemTags.JUICES)
			.addTag(DelightfulItemTags.JAMS)
			.addTag(DelightfulItemTags.NUT_BUTTER)
			.addTag(DelightfulItemTags.AGED_ROES)
			.add(DelightfulItems.ANIMAL_OIL_BOTTLE.get())
			.add(DelightfulItems.SALMONBERRY_PIE.get())
			.add(DelightfulItems.BAKLAVA.get());
		this.addSelf(DelightfulItemTags.POLISHED_ROSE_QUARTZ);
		this.addSelf(DelightfulItemTags.ZINC_HANDLE);
		this.addSelf(DelightfulItemTags.HEAP_EXPERIENCE);

		// Serene Seasons
		this.tag(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS)
			.addTag(DelightfulItemTags.SEEDS_SALMONBERRY)
			.addTag(DelightfulItemTags.SEEDS_CANTALOUPE);

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
		this.tag(DelightfulItemTags.GEMS_SKYJADE).addOptional(Modid.DA.rl("skyjade"));
		this.tag(DelightfulItemTags.SKYJADE_REPAIRING).addTag(DelightfulItemTags.GEMS_SKYJADE);
		this.tag(DelightfulItemTags.INGOTS_STRATUS).addOptional(Modid.DA.rl("stratus_ingot"));
		this.tag(DelightfulItemTags.STRATUS_REPAIRING).addTag(DelightfulItemTags.INGOTS_STRATUS);
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
			.addTag(DelightfulItemTags.COOKIES_SOURCE_BERRY)
			.add(DelightfulItems.SOURCE_BERRY_PIE_SLICE.get())
			.add(DelightfulItems.SOURCE_BERRY_GUMMY.get())
			.add(DelightfulItems.SOURCE_BERRY_ICE_CREAM.get())
			.add(DelightfulItems.SOURCE_BERRY_MILKSHAKE.get())
			.addOptional(Modid.COS.rl("source_berry_ice_cream"))
			.addOptional(Modid.COS.rl("source_berry_milkshake"));

		// Sully's Mod
		this.tag(Modid.SM.it("tortoise_food"))
			.addTag(CommonTags.Items.BERRIES)
			.addTag(DelightfulItemTags.CROPS_CACTUS)
			.addTag(DelightfulItemTags.FRUITS_CANTALOUPE)
			.add(Items.BAMBOO)
			.add(ModItems.PUMPKIN_SLICE.get())
			.addOptional(Modid.FRD.rl("hamimelon_slice"))
			.addOptional(Modid.UGD.rl("gloomgourd_slice"));

		// Let Fish Love
		this.tag(DelightfulItemTags.FISH_FOOD_SALMON).add(DelightfulItems.SALMONBERRIES.get());
		this.tag(DelightfulItemTags.FISH_FOOD_STURGEON)
			.addOptional(Modid.TIDE.rl("incandescent_larva"))
			.addOptional(Modid.CR.rl("tiger_prawn"));
	}

	@Override
	public @NotNull String getName() {
		return Delightful.MODID;
	}

	private void addSelf(TagKey<Item> item) {
		this.tag(item).addOptional(item.location());
	}
}
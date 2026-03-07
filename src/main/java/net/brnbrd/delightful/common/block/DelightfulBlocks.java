package net.brnbrd.delightful.common.block;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.compat.UnusualEndCompat;
import net.brnbrd.delightful.compat.letfishlove.LetFishLoveCompat;
import net.brnbrd.delightful.compat.undergarden.SlicedGloomgourdBlock;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import java.util.function.Supplier;

public class DelightfulBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Delightful.MODID);

	public static final RegistryObject<Block> QUARTZ_CABINET = registerBlock("quartz_cabinet",
		() -> new DelightfulCabinetBlock(
			DelightfulItemTags.getGem("quartz"),
			Block.Properties.copy(Blocks.QUARTZ_BLOCK)
				.requiresCorrectToolForDrops()
				.strength(0.8F)
		));
	public static final RegistryObject<Block> BASALT_CABINET = registerBlock("basalt_cabinet",
		() -> new DelightfulCabinetBlock(
			Ingredient.of(Items.BASALT),
			Block.Properties.copy(Blocks.BASALT)
				.requiresCorrectToolForDrops()
				.strength(1.25F, 4.2F)
				.sound(SoundType.BASALT)
		));
	public static final RegistryObject<Block> WILD_SALMONBERRIES = BLOCKS.register("wild_salmonberries",
		WildSalmonberriesBlock::new);
	public static final RegistryObject<Block> SALMONBERRY_BUSH = BLOCKS.register("salmonberry_bush",
		() -> new SalmonberryBushBlock(
			Block.Properties.copy(Blocks.SWEET_BERRY_BUSH)
				.randomTicks()
				.noCollission()
				.sound(SoundType.SWEET_BERRY_BUSH)
		));
	public static final RegistryObject<Block> MINI_MELON = BLOCKS.register("mini_melon",
		() -> new MiniMelonBlock(Block.Properties.copy(Blocks.MELON).noOcclusion()));
	public static final RegistryObject<Block> SLICED_MINI_MELON = BLOCKS.register("sliced_mini_melon",
		() -> new SlicedMiniMelonBlock(Block.Properties.copy(MINI_MELON.get()).noOcclusion(), () -> Items.MELON_SLICE, ModItems.MELON_JUICE));
	public static final RegistryObject<Block> SLICED_CANTALOUPE = BLOCKS.register("sliced_cantaloupe",
		() -> new SlicedMiniMelonBlock(Block.Properties.copy(SLICED_MINI_MELON.get()).noOcclusion(), DelightfulItems.CANTALOUPE_SLICE, null));
	public static final RegistryObject<Block> CANTALOUPE = BLOCKS.register("cantaloupe",
		() -> new CantaloupeBlock(Block.Properties.copy(MINI_MELON.get()).noOcclusion()));
	public static final RegistryObject<FeastBlock> STUFFED_CANTALOUPE_BLOCK = BLOCKS.register("stuffed_cantaloupe_block",
		() -> new FeastBlock(
			BlockBehaviour.Properties.copy(DelightfulBlocks.CANTALOUPE.get()),
			DelightfulItems.STUFFED_CANTALOUPE, false
		));
	public static final RegistryObject<Block> CANTALOUPE_PLANT = BLOCKS.register("cantaloupe_plant",
		() -> new CantaloupePlantBlock(
			BlockBehaviour.Properties.of()
				.mapColor(MapColor.PLANT)
				.noCollission()
				.randomTicks()
				.instabreak()
				.sound(SoundType.CROP)
				.pushReaction(PushReaction.DESTROY)
		));
	public static final RegistryObject<Block> SLICED_MELON = BLOCKS.register("sliced_melon",
		() -> new SlicedMelonBlock(Block.Properties.copy(Blocks.MELON), () -> Items.MELON_SLICE, ModItems.MELON_JUICE));
	public static final RegistryObject<Block> SLICED_PUMPKIN = BLOCKS.register("sliced_pumpkin",
		() -> new SlicedGourdBlock(Block.Properties.copy(Blocks.PUMPKIN), ModItems.PUMPKIN_SLICE));
	public static final RegistryObject<Block> SLICED_GLOOMGOURD = BLOCKS.register("sliced_gloomgourd",
		Modid.UG.loaded() ?
		() -> new SlicedGloomgourdBlock(Block.Properties.copy(Modid.UG.block("carved_gloomgourd", Blocks.PUMPKIN))) :
		() -> new SlicedGourdBlock(Block.Properties.copy(Blocks.PUMPKIN), ModItems.PUMPKIN_SLICE)
	);
	public static final RegistryObject<Block> SALMONBERRY_SACK = BLOCKS.register("salmonberry_sack",
		() -> new Block(SACK(MapColor.COLOR_ORANGE)));
	public static final RegistryObject<Block> SALMONBERRY_PIE = BLOCKS.register("salmonberry_pie",
		() -> new PieBlock(Block.Properties.copy(ModBlocks.APPLE_PIE.get()), DelightfulItems.SALMONBERRY_PIE_SLICE));
	public static final RegistryObject<PumpkinPieBlock> PUMPKIN_PIE = BLOCKS.register("pumpkin_pie",
		() -> new PumpkinPieBlock(DelightfulItems.PUMPKIN_PIE_SLICE));
	public static final RegistryObject<Block> BAKLAVA = BLOCKS.register("baklava",
		() -> new BaklavaBlock(Block.Properties.copy(ModBlocks.APPLE_PIE.get()), DelightfulItems.BAKLAVA_SLICE));
	public static final RegistryObject<DPieBlock> SOURCE_BERRY_PIE = BLOCKS.register("source_berry_pie",
		() -> new DPieBlock(
			Modid.AND.loaded() ?
			() -> Modid.AND.item("source_berry_pie_slice") :
			DelightfulItems.SOURCE_BERRY_PIE_SLICE,
			Modid.AN.rl("source_berry_pie")
		));
	public static final RegistryObject<DPieBlock> CHORUS_PIE = BLOCKS.register(UnusualEndCompat.chorus_pie,
		() -> new DPieBlock(DelightfulItems.CHORUS_PIE_SLICE, Modid.UE.rl(UnusualEndCompat.chorus_pie)));
	public static final RegistryObject<DPieBlock> GLOOMGOURD_PIE = BLOCKS.register("gloomgourd_pie",
		() -> new DPieBlock(DelightfulItems.GLOOMGOURD_PIE_SLICE, Modid.UG.rl("gloomgourd_pie")));
	public static final RegistryObject<DPieBlock> BLUEBERRY_PIE = BLOCKS.register("blueberry_pie",
		() -> new DPieBlock(DelightfulItems.BLUEBERRY_PIE_SLICE, Modid.BWG.rl("blueberry_pie")));
	public static final RegistryObject<DPieBlock> GREEN_APPLE_PIE = BLOCKS.register("green_apple_pie",
		() -> new DPieBlock(DelightfulItems.GREEN_APPLE_PIE_SLICE, Modid.BWG.rl("green_apple_pie")));
	public static final RegistryObject<DPieBlock> MULBERRY_PIE = BLOCKS.register("mulberry_pie",
		() -> new DPieBlock(DelightfulItems.MULBERRY_PIE_SLICE, Modid.UA.rl("mulberry_pie")));
	public static final RegistryObject<DPieBlock> PASSION_FRUIT_TART = BLOCKS.register("passion_fruit_tart",
		() -> new DPieBlock(DelightfulItems.PASSION_FRUIT_TART_SLICE, Modid.AT.rl("passion_fruit_tart")));
	public static final RegistryObject<DPieBlock> MUTTON_PIE = BLOCKS.register("mutton_pie",
		() -> new DPieBlock(DelightfulItems.MUTTON_PIE_SLICE, Modid.WS.rl("mutton_pie")));
	public static final RegistryObject<Block> ACORN_SACK = BLOCKS.register("acorn_sack",
		() -> new Block(SACK(MapColor.COLOR_BROWN)));
	public static final RegistryObject<Block> SALMONBERRY_ICE_CREAM_BLOCK = BLOCKS.register("salmonberry_ice_cream_block",
		() -> new Block(Block.Properties.copy(Blocks.SNOW_BLOCK).mapColor(MapColor.COLOR_ORANGE).strength(0.2F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> MATCHA_ICE_CREAM_BLOCK = BLOCKS.register("matcha_ice_cream_block",
		() -> new Block(Block.Properties.copy(Blocks.SNOW_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.2F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> SOURCE_BERRY_ICE_CREAM_BLOCK = BLOCKS.register("source_berry_ice_cream_block",
		() -> new Block(Block.Properties.copy(Blocks.SNOW_BLOCK).mapColor(MapColor.COLOR_PURPLE).strength(0.2F).sound(SoundType.SNOW)));
	public static final RegistryObject<Block> SALMONBERRY_MILKSHAKE_CAULDRON = BLOCKS.register("salmonberry_milkshake_cauldron",
		() -> new DelightfulMilkshakeCauldronBlock(
			Modid.N.loaded() ?
			DelightfulCauldronInteractions.SALMONBERRY_MILKSHAKE.map() :
			CauldronInteraction.newInteractionMap())
	);
	public static final RegistryObject<Block> MATCHA_MILKSHAKE_CAULDRON = BLOCKS.register("matcha_milkshake_cauldron",
		() -> new DelightfulMilkshakeCauldronBlock(
			Modid.N.loaded() ?
			DelightfulCauldronInteractions.MATCHA_MILKSHAKE.map() :
			CauldronInteraction.newInteractionMap())
	);
	public static final RegistryObject<Block> SOURCE_BERRY_MILKSHAKE_CAULDRON = BLOCKS.register("source_berry_milkshake_cauldron",
		() -> new DelightfulMilkshakeCauldronBlock(
			Modid.N.loaded() ?
			DelightfulCauldronInteractions.SOURCE_BERRY_MILKSHAKE.map() :
			CauldronInteraction.newInteractionMap())
	);
	public static final RegistryObject<Block> BLUEBERRY_SACK = BLOCKS.register("blueberry_sack",
		() -> new Block(SACK(MapColor.TERRACOTTA_BLUE)));
	public static final RegistryObject<Block> MENDOSTEEN_CRATE = BLOCKS.register("mendosteen_crate",
		() -> new Block(CRATE(MapColor.COLOR_LIGHT_GREEN)));
	public static final RegistryObject<Block> BASTION_FRUIT_CRATE = BLOCKS.register("bastion_fruit_crate",
		() -> new Block(CRATE(MapColor.COLOR_PURPLE)));
	public static final RegistryObject<Block> FROSTAYA_CRATE = BLOCKS.register("frostaya_crate",
		() -> new Block(CRATE(MapColor.COLOR_LIGHT_BLUE)));
	public static final RegistryObject<Block> BOMBEGRANATE_CRATE = BLOCKS.register("bombegranate_crate",
		() -> new Block(CRATE(MapColor.COLOR_RED)));
	public static final RegistryObject<Block> GREEN_APPLE_CRATE = BLOCKS.register("green_apple_crate",
		() -> new Block(CRATE(MapColor.COLOR_LIGHT_GREEN)));
	public static final RegistryObject<Block> YUCCA_FRUIT_CRATE = BLOCKS.register("yucca_fruit_crate",
		() -> new Block(CRATE(MapColor.COLOR_LIGHT_GREEN)));
	public static final RegistryObject<Block> BAOBAB_FRUIT_CRATE = BLOCKS.register("baobab_fruit_crate",
		() -> new Block(CRATE(MapColor.COLOR_YELLOW)));
	public static final RegistryObject<Block> GLOW_JAM_COOKIE_TILES = BLOCKS.register("glow_jam_cookie_tiles",
		() -> new Block(COOKIE(MapColor.COLOR_YELLOW)));
	public static final RegistryObject<StairBlock> GLOW_JAM_COOKIE_TILE_STAIRS = BLOCKS.register("glow_jam_cookie_tile_stairs",
		() -> new StairBlock(
			GLOW_JAM_COOKIE_TILES.get()::defaultBlockState,
			COOKIE(MapColor.COLOR_YELLOW)
		));
	public static final RegistryObject<SlabBlock> GLOW_JAM_COOKIE_TILE_SLAB = BLOCKS.register("glow_jam_cookie_tile_slab",
		() -> new SlabBlock(COOKIE(MapColor.COLOR_YELLOW)));
	public static final RegistryObject<WallBlock> GLOW_JAM_COOKIE_TILE_WALL = BLOCKS.register("glow_jam_cookie_tile_wall",
		() -> new WallBlock(COOKIE(MapColor.COLOR_YELLOW)));
	public static final RegistryObject<Block> SOURCE_BERRY_COOKIE_TILES = BLOCKS.register("source_berry_cookie_tiles",
		() -> new Block(COOKIE(MapColor.COLOR_PURPLE)));
	public static final RegistryObject<StairBlock> SOURCE_BERRY_COOKIE_TILE_STAIRS = BLOCKS.register("source_berry_cookie_tile_stairs",
		() -> new StairBlock(
			SOURCE_BERRY_COOKIE_TILES.get()::defaultBlockState,
			COOKIE(MapColor.COLOR_PURPLE)
		));
	public static final RegistryObject<SlabBlock> SOURCE_BERRY_COOKIE_TILE_SLAB = BLOCKS.register("source_berry_cookie_tile_slab",
		() -> new SlabBlock(COOKIE(MapColor.COLOR_PURPLE)));
	public static final RegistryObject<WallBlock> SOURCE_BERRY_COOKIE_TILE_WALL = BLOCKS.register("source_berry_cookie_tile_wall",
		() -> new WallBlock(COOKIE(MapColor.COLOR_PURPLE)));

	public static final RegistryObject<Block> STURGEON_ROE = BLOCKS.register("sturgeon_roe_block", () ->
		Modid.LFL.loaded() && Modid.TIDE.loaded() ?
		LetFishLoveCompat.sturgeonRoeBlock() :
		new Block(BlockBehaviour.Properties.of())
	);

	public static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
		return BLOCKS.register(name, block);
	}

	public static void create(IEventBus bus) {
		BLOCKS.register(bus);
	}

	private static BlockBehaviour.Properties CRATE(MapColor color) {
		return Block.Properties
			.copy(Blocks.OAK_PLANKS)
			.strength(2F, 3F)
			.sound(SoundType.WOOD)
			.mapColor(color);
	}

	private static BlockBehaviour.Properties SACK(MapColor color) {
		return Block.Properties
			.copy(Blocks.WHITE_WOOL)
			.strength(0.5F)
			.sound(SoundType.WOOL)
			.mapColor(color);
	}

	private static BlockBehaviour.Properties COOKIE(MapColor color) {
		return Block.Properties.of()
			.strength(2F, 3F)
			.sound(SoundType.WOOD)
			.mapColor(color);
	}
}
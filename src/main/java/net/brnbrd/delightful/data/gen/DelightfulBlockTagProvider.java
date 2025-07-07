package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.compat.BWGCompat;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.common.tag.ModTags;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DelightfulBlockTagProvider extends BlockTagsProvider {
	protected DelightfulBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		// Farmer's Delight
		this.tag(ModTags.WILD_CROPS)
			.add(DelightfulBlocks.WILD_SALMONBERRIES.get());
		this.tag(ForgeTags.MINEABLE_WITH_KNIFE)
			.add(DelightfulBlocks.SLICED_PUMPKIN.get())
			.add(DelightfulBlocks.SLICED_MELON.get())
			.add(DelightfulBlocks.MINI_MELON.get())
			.add(DelightfulBlocks.SLICED_MINI_MELON.get())
			.add(DelightfulBlocks.CANTALOUPE.get())
			.add(DelightfulBlocks.SLICED_CANTALOUPE.get())
			.add(DelightfulBlocks.SLICED_GLOOMGOURD.get())
			.add(DelightfulBlocks.SALMONBERRY_PIE.get())
			.add(DelightfulBlocks.PUMPKIN_PIE.get())
			.add(DelightfulBlocks.BLUEBERRY_PIE.get())
			.add(DelightfulBlocks.GREEN_APPLE_PIE.get())
			.add(DelightfulBlocks.SOURCE_BERRY_PIE.get())
			.add(DelightfulBlocks.GLOOMGOURD_PIE.get())
			.add(DelightfulBlocks.CHORUS_PIE.get())
			.add(DelightfulBlocks.MULBERRY_PIE.get())
			.add(DelightfulBlocks.PASSION_FRUIT_TART.get())
			.add(DelightfulBlocks.BAKLAVA.get())
			.addOptional(Modid.UG.rl("gloomgourd"))
			.addOptional(Modid.UG.rl("carved_gloomgourd"))
			.addOptional(Modid.SEAS.rl("pumpkin_cake"))
			.addOptional(Modid.SEAS.rl("sweet_berry_cake"))
			.addOptional(Modid.SEAS.rl("beetroot_cake"))
			.addOptional(Modid.PEC.rl("aloe_cake"))
			.addOptional(Modid.PEC.rl("yucca_cake"))
			.addOptional(Modid.PEC.rl("passion_fruit_cake"))
			.addOptional(Modid.EXQ.rl("chorus_cake"))
			.addOptional(Modid.EXQ.rl("ether_bulb_cake"))
			.addOptional(Modid.EXQ.rl("nightshade_berry_cake"));

		// Delightful
		this.tag(DelightfulBlockTags.DROPS_STRAW)
			.addOptional(Modid.BWG.rl(BWGCompat.prairie_grass))
			.addOptional(Modid.BWG.rl(BWGCompat.tall_prairie_grass))
			.addOptional(Modid.BWG.rl(BWGCompat.beach_grass))
			.addOptional(Util.rl("sprout", "sprouts"));
		this.tag(DelightfulBlockTags.DROPS_ACORN)
			.add(Blocks.OAK_LEAVES)
			.add(Blocks.DARK_OAK_LEAVES);
		this.tag(DelightfulBlockTags.ADD_ACORN)
			.addOptional(Util.rl("natural_decoration", "oak_acorn"));
		this.tag(DelightfulBlockTags.DROPS_GREEN_TEA_LEAF)
			.addTag(BlockTags.LEAVES);
		this.tag(DelightfulBlockTags.CANTALOUPE_SPAWNS)
			.addTag(Tags.Blocks.SAND);

		// Forge
		this.tag(Tags.Blocks.SAND).addTag(BlockTags.SAND);

		// Minecraft
		this.tag(BlockTags.SWORD_EFFICIENT)
				.add(DelightfulBlocks.MINI_MELON.get())
				.add(DelightfulBlocks.SLICED_MINI_MELON.get())
				.add(DelightfulBlocks.CANTALOUPE.get())
				.add(DelightfulBlocks.SLICED_CANTALOUPE.get())
				.add(DelightfulBlocks.SLICED_MELON.get())
				.add(DelightfulBlocks.SLICED_PUMPKIN.get());
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
				.add(DelightfulBlocks.BASALT_CABINET.get())
				.add(DelightfulBlocks.QUARTZ_CABINET.get());
		this.tag(BlockTags.MINEABLE_WITH_AXE)
				.add(DelightfulBlocks.MINI_MELON.get())
				.add(DelightfulBlocks.SLICED_MINI_MELON.get())
				.add(DelightfulBlocks.CANTALOUPE.get())
				.add(DelightfulBlocks.SLICED_CANTALOUPE.get())
				.add(DelightfulBlocks.STUFFED_CANTALOUPE_BLOCK.get())
				.add(DelightfulBlocks.SLICED_MELON.get())
				.add(DelightfulBlocks.SLICED_PUMPKIN.get())
				.add(DelightfulBlocks.SLICED_GLOOMGOURD.get())
				.add(DelightfulBlocks.MENDOSTEEN_CRATE.get())
				.add(DelightfulBlocks.BASTION_FRUIT_CRATE.get())
				.add(DelightfulBlocks.FROSTAYA_CRATE.get())
				.add(DelightfulBlocks.BOMBEGRANATE_CRATE.get())
				.add(DelightfulBlocks.GREEN_APPLE_CRATE.get())
				.add(DelightfulBlocks.YUCCA_FRUIT_CRATE.get())
				.add(DelightfulBlocks.BAOBAB_FRUIT_CRATE.get())
				.addOptional(Modid.CD.rl("avocado_crate"))
				.addOptional(Modid.CD.rl("cucumber_crate"))
				.addOptional(Modid.CD.rl("pickle_crate"))
				.addOptional(Modid.CD.rl("corn_cob_crate"))
				.addOptional(Modid.CD.rl("eggplant_crate"))
				.addOptional(Modid.CD.rl("white_eggplant_crate"))
				.addOptional(Modid.MND.rl("powdery_cabinet"))
				.addOptional(Util.rl("fruittrees", "citrus_cabinet"))
				.addOptional(Util.rl("fruittrees", "cherry_cabinet"));
		this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
				.add(DelightfulBlocks.MATCHA_ICE_CREAM_BLOCK.get())
				.add(DelightfulBlocks.SALMONBERRY_ICE_CREAM_BLOCK.get())
				.add(DelightfulBlocks.SOURCE_BERRY_ICE_CREAM_BLOCK.get());
		this.tag(BlockTags.MINEABLE_WITH_HOE)
				.add(DelightfulBlocks.ACORN_SACK.get())
				.add(DelightfulBlocks.SALMONBERRY_SACK.get())
				.add(DelightfulBlocks.BLUEBERRY_SACK.get())
				.add(DelightfulBlocks.GLOW_JAM_COOKIE_TILES.get())
				.add(DelightfulBlocks.GLOW_JAM_COOKIE_TILE_STAIRS.get())
				.add(DelightfulBlocks.GLOW_JAM_COOKIE_TILE_SLAB.get())
				.add(DelightfulBlocks.GLOW_JAM_COOKIE_TILE_WALL.get())
				.add(DelightfulBlocks.SOURCE_BERRY_COOKIE_TILES.get())
				.add(DelightfulBlocks.SOURCE_BERRY_COOKIE_TILE_STAIRS.get())
				.add(DelightfulBlocks.SOURCE_BERRY_COOKIE_TILE_SLAB.get())
				.add(DelightfulBlocks.SOURCE_BERRY_COOKIE_TILE_WALL.get())
				.addOptional(Modid.AN.rl("sourceberry_sack"));
		this.tag(BlockTags.STAIRS)
				.add(DelightfulBlocks.GLOW_JAM_COOKIE_TILE_STAIRS.get())
				.add(DelightfulBlocks.SOURCE_BERRY_COOKIE_TILE_STAIRS.get());
		this.tag(BlockTags.SLABS)
				.add(DelightfulBlocks.GLOW_JAM_COOKIE_TILE_SLAB.get())
				.add(DelightfulBlocks.SOURCE_BERRY_COOKIE_TILE_SLAB.get());
		this.tag(BlockTags.WALLS)
				.add(DelightfulBlocks.GLOW_JAM_COOKIE_TILE_WALL.get())
				.add(DelightfulBlocks.SOURCE_BERRY_COOKIE_TILE_WALL.get());
		this.tag(BlockTags.SMALL_FLOWERS)
				.add(DelightfulBlocks.WILD_SALMONBERRIES.get())
				.addOptional(Modid.CD.rl("wild_cucumbers"))
				.addOptional(Modid.CD.rl("wild_corn"))
				.addOptional(Modid.CD.rl("wild_eggplants"));
		this.tag(BlockTags.CAULDRONS)
				.add(DelightfulBlocks.SALMONBERRY_MILKSHAKE_CAULDRON.get())
				.add(DelightfulBlocks.MATCHA_MILKSHAKE_CAULDRON.get());

		// Serene Seasons
		this.tag(DelightfulBlockTags.SUMMER_CROPS)
				.add(DelightfulBlocks.SALMONBERRY_BUSH.get());
	}
}
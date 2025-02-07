package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.compat.BWGCompat;
import net.brnbrd.delightful.compat.Mods;
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

		// Mineable
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
				.add(DelightfulBlocks.BAKLAVA.get())
				.addOptional(Util.rl(Mods.UG, "gloomgourd"))
				.addOptional(Util.rl(Mods.UG, "carved_gloomgourd"));

		// Delightful
		this.tag(DelightfulBlockTags.DROPS_STRAW)
				.addOptional(Util.rl(Mods.BWG, BWGCompat.prairie_grass))
				.addOptional(Util.rl(Mods.BWG, BWGCompat.tall_prairie_grass))
				.addOptional(Util.rl(Mods.BWG, BWGCompat.beach_grass))
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
		this.tag(Tags.Blocks.SAND)
				.addTag(BlockTags.SAND);

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
				.addOptional(Util.rl(Mods.CD, "avocado_crate"))
				.addOptional(Util.rl(Mods.CD, "cucumber_crate"))
				.addOptional(Util.rl(Mods.CD, "pickle_crate"))
				.addOptional(Util.rl(Mods.CD, "corn_cob_crate"))
				.addOptional(Util.rl(Mods.CD, "eggplant_crate"))
				.addOptional(Util.rl(Mods.CD, "white_eggplant_crate"))
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
				.addOptional(Util.rl(Mods.AN, "sourceberry_sack"));
		this.tag(BlockTags.SMALL_FLOWERS)
				.add(DelightfulBlocks.WILD_SALMONBERRIES.get())
				.addOptional(Util.rl(Mods.CD, "wild_cucumbers"))
				.addOptional(Util.rl(Mods.CD, "wild_corn"))
				.addOptional(Util.rl(Mods.CD, "wild_eggplants"));
		this.tag(BlockTags.CAULDRONS)
				.add(DelightfulBlocks.SALMONBERRY_MILKSHAKE_CAULDRON.get())
				.add(DelightfulBlocks.MATCHA_MILKSHAKE_CAULDRON.get());

		// Serene Seasons
		this.tag(DelightfulBlockTags.SUMMER_CROPS)
				.add(DelightfulBlocks.SALMONBERRY_BUSH.get());
	}
}
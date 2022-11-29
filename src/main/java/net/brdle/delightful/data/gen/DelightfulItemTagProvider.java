package net.brdle.delightful.data.gen;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.knife.DelightfulKnifeItem;
import net.brdle.delightful.compat.BYGCompat;
import net.brdle.delightful.data.DelightfulBlockTags;
import net.brdle.delightful.data.DelightfulItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.common.tag.ModTags;

public class DelightfulItemTagProvider extends ItemTagsProvider {
	public DelightfulItemTagProvider(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(gen, blockTagProvider, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {

		// Farmer's Delight
		// Farmer's Delight
		this.tag(ModTags.CABINETS)
			.addTag(DelightfulItemTags.CABINETS_STONE);
		this.tag(DelightfulItemTags.CABINETS_STONE)
			.add(DelightfulItems.BASALT_CABINET.get())
			.add(DelightfulItems.QUARTZ_CABINET.get());

		// Forge
		this.tag(DelightfulItemTags.SCAVENGING_TOOLS)
			.addTag(ForgeTags.TOOLS_KNIVES)
			.addOptional(Util.rl("nethersdelight", "scavenging_tools"))
			.addOptional(Util.rl("nethersdelight", "tools/machetes"))
			.addOptional(Util.rl("forge", "tools/machetes"));
		this.tag(DelightfulItemTags.FRUITS_APPLE).add(Items.APPLE);
		this.tag(DelightfulItemTags.FRUITS_MELON).add(Items.MELON_SLICE);
		this.tag(DelightfulItemTags.FRUITS_CHORUS).add(Items.CHORUS_FRUIT);
		this.tag(DelightfulItemTags.FRUITS_SWEET_BERRIES).add(Items.SWEET_BERRIES);
		this.tag(DelightfulItemTags.FRUITS_GLOW_BERRIES).add(Items.GLOW_BERRIES);
		this.tag(DelightfulItemTags.FRUITS_SALMONBERRIES).add(DelightfulItems.SALMONBERRIES.get());
		this.tag(DelightfulItemTags.FRUITS_KIWI).addOptional(Util.rl("hedgehog", "kiwi"));
		this.tag(DelightfulItemTags.FRUITS_PRICKLY_PEAR).addOptional(Util.rl("ecologics", "prickly_pear"));
		this.tag(DelightfulItemTags.FRUITS_TORCHBERRIES).addOptional(Util.rl("twilightforest", "torchberries"));
		this.tag(DelightfulItemTags.FRUITS_SOURCEBERRY).addOptional(Util.rl("ars_nouveau", "source_berry"));
		this.tag(DelightfulItemTags.FRUITS_ELDERBERRY).addOptional(Util.rl("rootsclassic", "elderberry"));
		this.tag(DelightfulItemTags.FRUITS_BLACKCURRANT).addOptional(Util.rl("rootsclassic", "blackcurrant"));
		this.tag(DelightfulItemTags.FRUITS_REDCURRANT).addOptional(Util.rl("rootsclassic", "redcurrant"));
		this.tag(DelightfulItemTags.FRUITS_WHITECURRANT).addOptional(Util.rl("rootsclassic", "whitecurrant"));
		this.tag(DelightfulItemTags.FRUITS_BLUEBERRIES)
			.addOptional(Util.rl("byg", "blueberries"))
			.addOptional(Util.rl("wildberries", "blueberries"));
		this.tag(DelightfulItemTags.FRUITS_RASPBERRIES)
			.addOptional(Util.rl("wildberries", "raspberry"));
		this.tag(DelightfulItemTags.FRUITS_BLACKBERRIES)
			.addOptional(Util.rl("wildberries", "blackberry"));
		this.tag(DelightfulItemTags.FRUITS_CRANBERRIES)
			.addOptional(Util.rl("wildberries", "cranberries"));
		this.tag(DelightfulItemTags.FRUITS_NIGHTSHADE_BERRIES).addOptional(Util.rl("byg", "nightshade_berries"));
		this.tag(DelightfulItemTags.FRUITS_CRIMSON_BERRIES).addOptional(Util.rl("byg", "crimson_berries"));
		this.tag(DelightfulItemTags.FRUITS_WILD_BERRIES)
			.addOptional(Util.rl("windswept", "wild_berries"));
		this.tag(DelightfulItemTags.FRUITS_STRAWBERRIES)
			.addOptional(Util.rl("neapolitan", "strawberries"))
			.addOptional(Util.rl("neapolitan", "white_strawberries"));
		this.tag(DelightfulItemTags.FRUITS_BANANA)
			.addOptional(Util.rl("neapolitan", "banana"));
		this.tag(DelightfulItemTags.FRUITS_CHERRY)
			.addOptional(Util.rl("forbidden_arcanus", "cherry_peach"));
		this.tag(DelightfulItemTags.FRUITS_BERRIES)
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
			.addTag(DelightfulItemTags.FRUITS_WILD_BERRIES);
		this.tag(ForgeTags.BERRIES)
			.addTag(DelightfulItemTags.FRUITS_BERRIES);
		this.tag(DelightfulItemTags.FRUITS)
			.addTag(DelightfulItemTags.FRUITS_APPLE)
			.addTag(DelightfulItemTags.FRUITS_KIWI)
			.addTag(DelightfulItemTags.FRUITS_MELON)
			.addTag(DelightfulItemTags.FRUITS_PRICKLY_PEAR)
			.addTag(DelightfulItemTags.FRUITS_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_CHORUS)
			.addTag(DelightfulItemTags.FRUITS_CITRON)
			.addTag(DelightfulItemTags.FRUITS_JABUTICABA)
			.addTag(DelightfulItemTags.FRUITS_RAMBUTAN)
			.addTag(DelightfulItemTags.FRUITS_PITAYA)
			.addTag(DelightfulItemTags.FRUITS_KIWANO)
			.addOptional(Util.rl("ars_nouveau", "mendosteen_pod"))
			.addOptional(Util.rl("ars_nouveau", "bastion_pod"));
		this.tag(DelightfulItemTags.FRUITS_SWEET)
			.addTag(DelightfulItemTags.FRUITS_APPLE)
			.addTag(DelightfulItemTags.FRUITS_KIWI)
			.addTag(DelightfulItemTags.FRUITS_MELON)
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
			.addOptionalTag(Util.rl("forge", "fruits/mandarin"))
			.addOptionalTag(Util.rl("forge", "fruits/orange"))
			.addOptionalTag(Util.rl("forge", "fruits/redlove"));
		this.tag(DelightfulItemTags.FRUITS_GREEN_APPLE)
			.addOptional(Util.rl(BYGCompat.modid, "green_apple"));
		this.tag(DelightfulItemTags.NUTS_WALNUT).addOptional(Util.rl("ecologics", "walnut"));
		this.tag(DelightfulItemTags.NUTS_PEANUT).addOptional(Util.rl("sprout", "peanut"));
		this.tag(DelightfulItemTags.NUTS_CHESTNUT).addOptional(Util.rl("windswept", "chestnuts"));
		this.tag(DelightfulItemTags.NUTS_ACORN)
			.add(DelightfulItems.ACORN.get());
		this.tag(DelightfulItemTags.NUTS)
			.addTag(DelightfulItemTags.NUTS_ACORN)
			.addTag(DelightfulItemTags.NUTS_WALNUT)
			.addTag(DelightfulItemTags.NUTS_PEANUT)
			.addTag(DelightfulItemTags.NUTS_CHESTNUT);
		this.tag(DelightfulItemTags.INGOTS_STEEL).addOptional(Util.rl("simplysteel", "steel_ingot"));
		this.tag(DelightfulItemTags.WATER).add(Items.WATER_BUCKET);
		this.tag(DelightfulItemTags.JELLY)
			.add(DelightfulItems.JELLY_BOTTLE.get())
			.add(DelightfulItems.GLOW_JELLY_BOTTLE.get());
		this.tag(DelightfulItemTags.JELLIES)
			.addTag(DelightfulItemTags.JELLY);
		this.tag(DelightfulItemTags.JAM)
			.addTag(DelightfulItemTags.JELLIES);
		this.tag(DelightfulItemTags.JAMS)
			.addTag(DelightfulItemTags.JAM)
			.addOptionalTag(Util.rl("wildberries", "berry_jams"));
		this.tag(DelightfulItemTags.SUGAR).add(Items.SUGAR);
		this.tag(DelightfulItemTags.COOKED_CRAB)
			.addOptional(Util.rl("ecologics", "crab_meat"))
			.addOptional(Util.rl("quark", "cooked_crab_leg"));
		this.tag(DelightfulItemTags.CHEESE)
			.addOptional(Util.rl("brewinandchewin", "flaxen_cheese_wedge"))
			.addOptional(Util.rl("farmlife", "tribull_cheese_wedge"))
			.addOptional(Util.rl("croptopia", "cheese"));
		this.tag(ForgeTags.MILK)
			.addOptional(Util.rl("dracovitadelight", "tribull_milk"));
		this.tag(DelightfulItemTags.CHEESE_OR_MILK)
			.addTag(DelightfulItemTags.CHEESE)
			.addTag(ForgeTags.MILK);
		this.tag(DelightfulItemTags.TEA_LEAVES)
			.addTag(DelightfulItemTags.TEA_LEAVES_GREEN)
			.addOptionalTag(Util.rl("farmersrespite", "tea_leaves"));
		this.tag(DelightfulItemTags.TEA_LEAVES_GREEN)
			.addOptional(Util.rl("farmersrespite", "green_tea_leaves"));
		// Not adding my tea leaf, because it is a single leaf and not leaves. Also so compat is more direct.
		this.tag(DelightfulItemTags.RAW_FISHES_KOI)
			.addOptional(Util.rl("environmental", "koi"))
			.addOptional(Util.rl("crittersandcompanions", "koi_fish"));
		this.tag(DelightfulItemTags.RAW_FISHES_GLOWFISH)
			.addOptional(Util.rl("biomemakeover", "glowfish"));
		this.tag(ForgeTags.RAW_FISHES)
			.addTag(DelightfulItemTags.RAW_FISHES_KOI)
			.addTag(DelightfulItemTags.RAW_FISHES_GLOWFISH);
		this.tag(ForgeTags.COOKED_FISHES)
			.addOptional(Util.rl("biomemakeover", "cooked_glowfish"));
		this.tag(DelightfulItemTags.RAW_VENISON)
			.addOptional(Util.rl("naturalist", "venison"))
			.addOptional(Util.rl("goodall", "raw_venison"))
			.addOptional(Util.rl("twilightforest", "raw_venison"));
		this.tag(DelightfulItemTags.COOKED_VENISON)
			.addOptional(Util.rl("naturalist", "cooked_venison"))
			.addOptional(Util.rl("goodall", "cooked_venison"))
			.addOptional(Util.rl("twilightforest", "cooked_venison"));
		this.tag(DelightfulItemTags.RAW_GOAT)
			.add(DelightfulItems.RAW_GOAT.get());
		this.tag(DelightfulItemTags.COOKED_GOAT)
			.add(DelightfulItems.COOKED_GOAT.get());
		this.tag(DelightfulItemTags.RAW_FROG)
			.addOptional(Util.rl("frog_legs", "frog_legs"));
		this.tag(DelightfulItemTags.COOKED_FROG)
			.addOptional(Util.rl("frog_legs", "cooked_frog_legs"));
		this.tag(DelightfulItemTags.FOOD_MEAT)
			.addTag(DelightfulItemTags.RAW_VENISON)
			.addTag(DelightfulItemTags.COOKED_VENISON)
			.addTag(DelightfulItemTags.RAW_GOAT)
			.addTag(DelightfulItemTags.COOKED_GOAT)
			.addTag(DelightfulItemTags.RAW_FROG)
			.addTag(DelightfulItemTags.COOKED_FROG);
		this.tag(DelightfulItemTags.MEAT)
			.addTag(DelightfulItemTags.FOOD_MEAT);
		this.tag(DelightfulItemTags.CATTAIL)
			.addOptional(Util.rl("sprout", "cattail"))
			.addOptional(Util.rl("biomesoplenty", "cattail"))
			.addOptional(Util.rl("biomemakeover", "cattail"));
		this.tag(DelightfulItemTags.GEMS_ROSE_QUARTZ)
			.addOptional(Util.rl("biomesoplenty", "rose_quartz_shard"))
			.addOptional(Util.rl("create", "rose_quartz"));
		this.tag(DelightfulItemTags.CHOCOLATE)
			.addOptional(Util.rl("neapolitan", "chocolate_bar"))
			.addOptional(Util.rl("create", "bar_of_chocolate"))
			.addOptionalTag(Util.rl("forge", "chocolatebar"));
		this.tag(ForgeTags.SALAD_INGREDIENTS)
			.add(DelightfulItems.CHOPPED_CLOVER.get())
			.addOptional(Util.rl("sprout", "sprouts"));
		this.tag(DelightfulItemTags.SEEDS_SALMONBERRY)
			.add(DelightfulItems.SALMONBERRY_PIPS.get());
		this.tag(ForgeTags.SEEDS)
			.addTag(DelightfulItemTags.SEEDS_SALMONBERRY)
			.addOptional(Util.rl("farmersrespite", "tea_seeds"));

		// Minecraft
		this.tag(ItemTags.PIGLIN_LOVED).add(DelightfulItems.REFINED_GLOWSTONE_KNIFE.get());
		this.tag(ForgeTags.TOOLS_KNIVES)
			.addTag(ModTags.KNIVES)
			.addOptional(Util.rl("ae2", "certus_quartz_cutting_knife"))
			.addOptional(Util.rl("ae2", "nether_quartz_cutting_knife"))
			.addOptional(Util.rl("occultism", "butcher_knife"));
		var build = this.tag(ModTags.KNIVES);
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
		this.tag(DelightfulItemTags.INGOTS_DEORUM).addOptional(Util.rl("forbidden_arcanus", "deorum_ingot"));

		// Byg
		this.tag(DelightfulItemTags.INGOTS_PENDORITE).addOptional(Util.rl(BYGCompat.modid, "pendorite_ingot"));

		this.addSelf(DelightfulItemTags.REINFORCED_ECHO_SHARD);

		// Create
		this.addSelf(DelightfulItemTags.POLISHED_ROSE_QUARTZ);
		this.addSelf(DelightfulItemTags.ZINC_HANDLE);
		this.addSelf(DelightfulItemTags.HEAP_EXPERIENCE);

		// Seeds
		this.addSelf(DelightfulItemTags.SHARP_LEAF);

		// Nethers Exoticism
		this.addSelf(DelightfulItemTags.KIWANO_PEEL);
		this.tag(DelightfulItemTags.FRUITS_CITRON).addOptional(Util.rl("nethers_exoticism", "bouddha_s_hand"));
		this.tag(DelightfulItemTags.FRUITS_JABUTICABA).addOptional(Util.rl("nethers_exoticism", "jaboticaba"));
		this.tag(DelightfulItemTags.FRUITS_PITAYA).addOptional(Util.rl("nethers_exoticism", "pitaya"));
		this.tag(DelightfulItemTags.FRUITS_RAMBUTAN).addOptional(Util.rl("nethers_exoticism", "ramboutan"));
		this.tag(DelightfulItemTags.FRUITS_KIWANO).addOptional(Util.rl("nethers_exoticism", "kiwano"));

		// RL
		this.addSelf(DelightfulItemTags.SWEETENED_CHUNK);

		// SAS
		this.addSelf(DelightfulItemTags.BURGER_BUN);
		this.addSelf(DelightfulItemTags.BREAD_SLICE);
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public @NotNull String getName() {
		return Delightful.MODID;
	}

	private TagsProvider.TagAppender<Item> addSelf(TagKey<Item> item) {
		return this.tag(item).addOptional(item.location());
	}
}

package net.brdle.delightful.data;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.knife.DelightfulKnifeItem;
import net.brdle.delightful.common.tag.DelightfulBlockTags;
import net.brdle.delightful.common.tag.DelightfulItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.common.tag.ModTags;

public class DelightfulItemTagProvider extends ItemTagsProvider {
	public DelightfulItemTagProvider(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(gen, blockTagProvider, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {

		// Farmer's Delight
		this.copy(DelightfulBlockTags.CABINETS_WOODEN, DelightfulItemTags.CABINETS_WOODEN);
		this.copy(DelightfulBlockTags.CABINETS_STONE, DelightfulItemTags.CABINETS_STONE);
		this.copy(DelightfulBlockTags.CABINETS, DelightfulItemTags.CABINETS);

		// Forge
		this.tag(DelightfulItemTags.FRUITS_APPLE).add(Items.APPLE);
		this.tag(DelightfulItemTags.FRUITS_MELON).add(Items.MELON_SLICE);
		this.tag(DelightfulItemTags.FRUITS_CHORUS).add(Items.CHORUS_FRUIT);
		this.tag(DelightfulItemTags.FRUITS_SWEET_BERRIES).add(Items.SWEET_BERRIES);
		this.tag(DelightfulItemTags.FRUITS_GLOW_BERRIES).add(Items.GLOW_BERRIES);
		this.tag(DelightfulItemTags.FRUITS_SALMONBERRIES).add(DelightfulItems.SALMONBERRIES.get());
		this.tag(DelightfulItemTags.FRUITS_KIWI).addOptional(new ResourceLocation("hedgehog", "kiwi"));
		this.tag(DelightfulItemTags.FRUITS_PRICKLY_PEAR).addOptional(new ResourceLocation("ecologics", "prickly_pear"));
		this.tag(DelightfulItemTags.FRUITS_TORCHBERRIES).addOptional(new ResourceLocation("twilightforest", "torchberries"));
		this.tag(DelightfulItemTags.FRUITS_SOURCEBERRY).addOptional(new ResourceLocation("ars_nouveau", "source_berry"));
		this.tag(DelightfulItemTags.FRUITS_STRAWBERRIES)
			.addOptional(new ResourceLocation("neapolitan", "strawberries"))
			.addOptional(new ResourceLocation("neapolitan", "white_strawberries"));
		this.tag(DelightfulItemTags.FRUITS_BANANA)
			.addOptional(new ResourceLocation("neapolitan", "banana"));
		this.tag(DelightfulItemTags.FRUITS_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_SWEET_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_GLOW_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.addTag(DelightfulItemTags.FRUITS_STRAWBERRIES)
			.addTag(DelightfulItemTags.FRUITS_TORCHBERRIES)
			.addTag(DelightfulItemTags.FRUITS_SOURCEBERRY);
		this.tag(DelightfulItemTags.FRUITS)
			.addTag(DelightfulItemTags.FRUITS_APPLE)
			.addTag(DelightfulItemTags.FRUITS_KIWI)
			.addTag(DelightfulItemTags.FRUITS_MELON)
			.addTag(DelightfulItemTags.FRUITS_PRICKLY_PEAR)
			.addTag(DelightfulItemTags.FRUITS_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_CHORUS)
			.addOptional(new ResourceLocation("ars_nouveau", "mendosteen_pod"))
			.addOptional(new ResourceLocation("ars_nouveau", "bastion_pod"));
		this.tag(DelightfulItemTags.FRUITS_SWEET)
			.addTag(DelightfulItemTags.FRUITS_APPLE)
			.addTag(DelightfulItemTags.FRUITS_KIWI)
			.addTag(DelightfulItemTags.FRUITS_MELON)
			.addTag(DelightfulItemTags.FRUITS_PRICKLY_PEAR)
			.addTag(DelightfulItemTags.FRUITS_SWEET_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.addTag(DelightfulItemTags.FRUITS_STRAWBERRIES)
			.addTag(DelightfulItemTags.FRUITS_BANANA);
		this.tag(DelightfulItemTags.NUTS_WALNUT).addOptional(new ResourceLocation("ecologics", "walnut"));
		this.tag(DelightfulItemTags.NUTS_PEANUT).addOptional(new ResourceLocation("sprout", "peanut"));
		this.tag(DelightfulItemTags.NUTS)
			.addTag(DelightfulItemTags.NUTS_WALNUT)
			.addTag(DelightfulItemTags.NUTS_PEANUT);
		this.tag(DelightfulItemTags.INGOTS_STEEL).addOptional(new ResourceLocation("simplysteel", "steel_ingot"));
		this.tag(DelightfulItemTags.WATER).add(Items.WATER_BUCKET);
		this.tag(DelightfulItemTags.JELLY)
			.add(DelightfulItems.JELLY_BOTTLE.get())
			.add(DelightfulItems.GLOW_JELLY_BOTTLE.get());
		this.tag(DelightfulItemTags.SUGAR).add(Items.SUGAR);
		this.tag(DelightfulItemTags.COOKED_CRAB)
			.addOptional(new ResourceLocation("ecologics", "crab_meat"))
			.addOptional(new ResourceLocation("quark", "cooked_crab_leg"));
		this.tag(DelightfulItemTags.CHEESE)
			.addOptional(new ResourceLocation("brewinandchewin", "flaxen_cheese_wedge"))
			.addOptional(new ResourceLocation("farmlife", "tribull_cheese_wedge"));
		this.tag(ForgeTags.MILK)
			.addOptional(new ResourceLocation("dracovitadelight", "tribull_milk"));
		this.tag(DelightfulItemTags.CHEESE_OR_MILK)
			.addTag(DelightfulItemTags.CHEESE)
			.addTag(ForgeTags.MILK);
		this.tag(DelightfulItemTags.TEA_LEAVES_GREEN)
			.add(DelightfulItems.GREEN_TEA_LEAF.get())
			.addOptional(new ResourceLocation("farmersrespite", "green_tea_leaves"));
		this.tag(DelightfulItemTags.RAW_FISHES_KOI)
			.addOptional(new ResourceLocation("environmental", "koi"))
			.addOptional(new ResourceLocation("crittersandcompanions", "koi_fish"));
		this.tag(DelightfulItemTags.RAW_FISHES_GLOWFISH)
			.addOptional(new ResourceLocation("biomemakeover", "glowfish"));
		this.tag(ForgeTags.RAW_FISHES)
			.addTag(DelightfulItemTags.RAW_FISHES_KOI)
			.addTag(DelightfulItemTags.RAW_FISHES_GLOWFISH);
		this.tag(ForgeTags.COOKED_FISHES)
			.addOptional(new ResourceLocation("biomemakeover", "cooked_glowfish"));
		this.tag(DelightfulItemTags.CATTAIL)
			.addOptional(new ResourceLocation("sprout", "cattail"))
			.addOptional(new ResourceLocation("biomesoplenty", "cattail"))
			.addOptional(new ResourceLocation("biomemakeover", "cattail"));
		this.tag(DelightfulItemTags.GEMS_ROSE_QUARTZ)
			.addOptional(new ResourceLocation("biomesoplenty", "rose_quartz_shard"))
			.addOptional(new ResourceLocation("create", "rose_quartz"));

		// Minecraft
		this.tag(ItemTags.PIGLIN_LOVED).add(DelightfulItems.REFINED_GLOWSTONE_KNIFE.get());
		this.tag(ForgeTags.TOOLS_KNIVES).addTag(ModTags.KNIVES);
		var build = this.tag(ModTags.KNIVES);
		DelightfulItems.ITEMS.getEntries().stream()
			.map(RegistryObject::get)
			.filter(item -> item instanceof DelightfulKnifeItem)
			.forEach(build::add);

		// Ecologics
		this.tag(DelightfulItemTags.COOKED_PRICKLY_PEAR).addOptional(new ResourceLocation("ecologics", "cooked_prickly_pear"));
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public @NotNull String getName() {
		return Delightful.MODID;
	}
}

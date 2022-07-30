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
		this.tag(DelightfulItemTags.FRUITS_SWEET_BERRIES).add(Items.SWEET_BERRIES);
		this.tag(DelightfulItemTags.FRUITS_GLOW_BERRIES).add(Items.GLOW_BERRIES);
		this.tag(DelightfulItemTags.FRUITS_SALMONBERRY).add(DelightfulItems.SALMONBERRY.get());
		this.tag(DelightfulItemTags.FRUITS_KIWI).addOptional(new ResourceLocation("hedgehog", "kiwi"));
		this.tag(DelightfulItemTags.FRUITS_PRICKLY_PEAR).addOptional(new ResourceLocation("ecologics", "prickly_pear"));
		this.tag(DelightfulItemTags.FRUITS_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_SWEET_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_GLOW_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_SALMONBERRY);
		this.tag(DelightfulItemTags.FRUITS)
			.addTag(DelightfulItemTags.FRUITS_APPLE)
			.addTag(DelightfulItemTags.FRUITS_KIWI)
			.addTag(DelightfulItemTags.FRUITS_MELON)
			.addTag(DelightfulItemTags.FRUITS_PRICKLY_PEAR)
			.addTag(DelightfulItemTags.FRUITS_BERRIES);
		this.tag(DelightfulItemTags.FRUITS_SWEET)
			.addTag(DelightfulItemTags.FRUITS_APPLE)
			.addTag(DelightfulItemTags.FRUITS_KIWI)
			.addTag(DelightfulItemTags.FRUITS_MELON)
			.addTag(DelightfulItemTags.FRUITS_PRICKLY_PEAR)
			.addTag(DelightfulItemTags.FRUITS_SWEET_BERRIES)
			.addTag(DelightfulItemTags.FRUITS_SALMONBERRY);

		this.tag(DelightfulItemTags.NUTS_WALNUT).addOptional(new ResourceLocation("ecologics", "walnut"));
		this.tag(DelightfulItemTags.NUTS).addTag(DelightfulItemTags.NUTS_WALNUT);
		this.tag(DelightfulItemTags.INGOTS_STEEL).addOptional(new ResourceLocation("simplysteel", "steel_ingot"));
		this.tag(DelightfulItemTags.WATER).add(Items.WATER_BUCKET);
		this.tag(DelightfulItemTags.JELLY)
			.add(DelightfulItems.JELLY_BOTTLE.get())
			.add(DelightfulItems.GLOW_JELLY_BOTTLE.get());
		this.tag(DelightfulItemTags.SUGAR).add(Items.SUGAR);
		this.tag(DelightfulItemTags.COOKED_CRAB)
			.addOptional(new ResourceLocation("ecologics", "crab_meat"))
			.addOptional(new ResourceLocation("quark", "cooked_crab_leg"));
		this.tag(DelightfulItemTags.CHEESE).addOptional(new ResourceLocation("brewinandchewin", "flaxen_cheese_wedge"));
		this.tag(ForgeTags.MILK).add(Items.MILK_BUCKET);
		this.tag(DelightfulItemTags.CHEESE_OR_MILK)
			.addTag(DelightfulItemTags.CHEESE)
			.addTag(ForgeTags.MILK);
		this.tag(DelightfulItemTags.TEA_LEAVES_GREEN)
			.add(DelightfulItems.GREEN_TEA_LEAF.get())
			.addOptional(new ResourceLocation("farmersrespite", "green_tea_leaves"));
		this.tag(ForgeTags.RAW_FISHES).addOptional(new ResourceLocation("biomemakeover", "glowfish"));
		this.tag(ForgeTags.COOKED_FISHES).addOptional(new ResourceLocation("biomemakeover", "cooked_glowfish"));

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
	public String getName() {
		return Delightful.MODID;
	}
}

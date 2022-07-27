package net.brdle.delightful.data;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.knife.DelightfulKnifeItem;
import net.brdle.delightful.common.tag.DelightfulBlockTags;
import net.brdle.delightful.common.tag.DelightfulEntityTags;
import net.brdle.delightful.common.tag.DelightfulItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.data.EntityTags;

public class DelightfulEntityTagProvider extends EntityTypeTagsProvider {
	public DelightfulEntityTagProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, Delightful.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(DelightfulEntityTags.FATTY_ANIMALS)
			.add(EntityType.CAT)
			.add(EntityType.WOLF)
			.add(EntityType.COD)
			.add(EntityType.SALMON)
			.add(EntityType.COW)
			.add(EntityType.DOLPHIN)
			.add(EntityType.DONKEY)
			.add(EntityType.FOX)
			.add(EntityType.GLOW_SQUID)
			.add(EntityType.SQUID)
			.add(EntityType.GOAT)
			.add(EntityType.HOGLIN)
			.add(EntityType.HORSE)
			.add(EntityType.LLAMA)
			.add(EntityType.MOOSHROOM)
			.add(EntityType.MULE)
			.add(EntityType.OCELOT)
			.add(EntityType.PANDA)
			.add(EntityType.PIG)
			.add(EntityType.PIGLIN)
			.add(EntityType.PIGLIN_BRUTE)
			.add(EntityType.POLAR_BEAR)
			.add(EntityType.PUFFERFISH)
			.add(EntityType.RABBIT)
			.add(EntityType.SHEEP)
			.add(EntityType.TROPICAL_FISH)
			.add(EntityType.TURTLE)
			.addOptional(mod("ecologics", "camel"))
			.addOptional(mod("ecologics", "penguin"))
			.addOptional(mod("ecologics", "squirrel"));
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public String getName() {
		return Delightful.MODID;
	}

	private ResourceLocation mod(String modid, String name) {
		return new ResourceLocation(modid, name);
	}
}

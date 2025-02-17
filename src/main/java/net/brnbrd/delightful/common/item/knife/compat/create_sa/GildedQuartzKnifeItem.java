package net.brnbrd.delightful.common.item.knife.compat.create_sa;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class GildedQuartzKnifeItem extends DKnifeItem {
	public GildedQuartzKnifeItem(Properties properties) {
		super(DelightfulItemTags.POLISHED_ROSE_QUARTZ, DelightfulTiers.GILDED_QUARTZ, properties, Modid.CSA);
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return null;
	}
}
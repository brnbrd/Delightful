package net.brnbrd.delightful.common.item.knife.compat.create_sa;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class GildedQuartzKnifeItem extends CompatKnifeItem {
	public GildedQuartzKnifeItem(Properties properties) {
		super("create_sa", DelightfulItemTags.POLISHED_ROSE_QUARTZ, DelightfulTiers.GILDED_QUARTZ, properties);
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return null;
	}
}

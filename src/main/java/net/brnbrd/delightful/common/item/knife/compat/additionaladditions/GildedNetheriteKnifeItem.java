package net.brnbrd.delightful.common.item.knife.compat.additionaladditions;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class GildedNetheriteKnifeItem extends DKnifeItem {

	public GildedNetheriteKnifeItem(Properties properties) {
		super(DelightfulItemTags.GOLD_RING, DelightfulTiers.GILDED_NETHERITE, properties, Mods.AA);
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return RecipeType.SMITHING;
	}
}
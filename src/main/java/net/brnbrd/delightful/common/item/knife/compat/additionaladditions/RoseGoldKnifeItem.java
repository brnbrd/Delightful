package net.brnbrd.delightful.common.item.knife.compat.additionaladditions;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class RoseGoldKnifeItem extends DKnifeItem {

	public RoseGoldKnifeItem(Properties properties) {
		super(DelightfulItemTags.ROSE_GOLD_ALLOY, DelightfulTiers.ROSE_GOLD, properties, Modid.AA);
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return RecipeType.SMITHING;
	}
}
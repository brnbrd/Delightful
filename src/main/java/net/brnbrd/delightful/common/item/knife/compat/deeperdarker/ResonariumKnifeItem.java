package net.brnbrd.delightful.common.item.knife.compat.deeperdarker;

import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.minecraft.world.item.crafting.RecipeType;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import org.jetbrains.annotations.Nullable;

public class ResonariumKnifeItem extends DKnifeItem {
	public ResonariumKnifeItem(Properties properties) {
		super(DelightfulItemTags.RESONARIUM_PLATE, DelightfulTiers.RESONARIUM, properties, Modid.DD);
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return RecipeType.SMITHING;
	}
}
package net.brnbrd.delightful.common.item.knife.forbidden_arcanus;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.crafting.Ingredient;

public class DracoArcanusKnifeItem extends CompatKnifeItem {
	public DracoArcanusKnifeItem(Properties properties) {
		super("forbidden_arcanus", DelightfulItemTags.DRAGON_SCALE, DelightfulTiers.DRACO_ARCANUS, properties, null);
	}

	@Override
	public Ingredient getRod() {
		return Ingredient.of(DelightfulItemTags.DRACO_ARCANUS_STAFF);
	}
}

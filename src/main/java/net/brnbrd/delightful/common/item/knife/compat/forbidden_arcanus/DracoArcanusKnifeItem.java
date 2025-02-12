package net.brnbrd.delightful.common.item.knife.compat.forbidden_arcanus;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.crafting.Ingredient;

public class DracoArcanusKnifeItem extends DKnifeItem {
	public DracoArcanusKnifeItem(Properties properties) {
		super(DelightfulItemTags.DRAGON_SCALE, DelightfulTiers.DRACO_ARCANUS, properties, "forbidden_arcanus");
	}

	@Override
	public Ingredient getRod() {
		return Ingredient.of(DelightfulItemTags.DRACO_ARCANUS_STAFF);
	}
}
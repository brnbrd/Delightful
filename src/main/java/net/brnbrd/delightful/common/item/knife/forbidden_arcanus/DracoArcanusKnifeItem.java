package net.brnbrd.delightful.common.item.knife.forbidden_arcanus;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.function.Supplier;

public class DracoArcanusKnifeItem extends CompatKnifeItem {
	public DracoArcanusKnifeItem(Properties properties) {
		super("forbidden_arcanus", DelightfulItemTags.DRAGON_SCALE, DelightfulTiers.DRACO_ARCANUS, properties, null);
	}

	@Override
	public Supplier<Ingredient> getRod() {
		return Util.ing(DelightfulItemTags.DRACO_ARCANUS_STAFF);
	}
}

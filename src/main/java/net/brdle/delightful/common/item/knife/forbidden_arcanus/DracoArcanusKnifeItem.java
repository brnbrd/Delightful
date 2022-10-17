package net.brdle.delightful.common.item.knife.forbidden_arcanus;

import net.brdle.delightful.Util;
import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.brdle.delightful.data.DelightfulItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.Optional;
import java.util.function.Supplier;

public class DracoArcanusKnifeItem extends CompatKnifeItem {
	public DracoArcanusKnifeItem(Properties properties) {
		super("forbidden_arcanus", DelightfulItemTags.DRAGON_SCALE, DelightfulTiers.DRACO_ARCANUS, 0.5F, -2.0F, properties, Optional.empty());
	}

	@Override
	public Supplier<Ingredient> getRod() {
		return Util.ing(DelightfulItemTags.DRACO_ARCANUS_STAFF);
	}
}

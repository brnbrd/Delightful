package net.brnbrd.delightful.common.item.knife.compat.forbidden_arcanus;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.function.Supplier;

public class ReinforcedDeorumKnifeItem extends CompatKnifeItem {
	public ReinforcedDeorumKnifeItem(Properties properties) {
		super(Mods.FA, DelightfulItemTags.STELLARITE_PIECE, DelightfulTiers.REINFORCED_DEORUM, properties);
	}

	@Override
	public Supplier<Ingredient> getSmithingBase() {
		return Util.ing(Knives.DEORUM);
	}
}

package net.brnbrd.delightful.common.item.knife.compat.lolenderite;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.world.item.crafting.Ingredient;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class ObsdianInfusedEnderiteKnifeItem extends CompatKnifeItem {
	public ObsdianInfusedEnderiteKnifeItem(Properties properties) {
		super(Mods.LE, DelightfulItems.ingot("obsidian_infused_enderite"), DelightfulTiers.OBSIDIAN_INFUSED_ENDERITE, properties);
	}

	@Override
	public ImmutablePair<Ingredient, Ingredient> getSmithing() {
		return new ImmutablePair<>(
			Ingredient.of(EnderiteKnifeItem.upgrade),
			Util.ing(Knives.ENDERITE)
		);
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}
}

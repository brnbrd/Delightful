package net.brnbrd.delightful.common.item.knife.compat.byg;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.world.item.crafting.Ingredient;
import org.apache.commons.lang3.tuple.ImmutablePair;
import vectorwing.farmersdelight.common.registry.ModItems;

public class PendoriteKnifeItem extends CompatKnifeItem {
	public PendoriteKnifeItem(Properties properties) {
		super(Mods.BYG, DelightfulItems.ingot("pendorite"), DelightfulTiers.PENDORITE, properties);
	}

	@Override
	public ImmutablePair<Ingredient, Ingredient> getSmithing() {
		return new ImmutablePair<>(
			Util.ing(ModItems.NETHERITE_KNIFE),
			Util.ing(ModItems.NETHERITE_KNIFE)
		);
	}
}

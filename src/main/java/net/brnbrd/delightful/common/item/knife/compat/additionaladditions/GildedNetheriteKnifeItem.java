package net.brnbrd.delightful.common.item.knife.compat.additionaladditions;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.registry.ModItems;
import java.util.function.Supplier;

public class GildedNetheriteKnifeItem extends CompatKnifeItem {
	public GildedNetheriteKnifeItem(Properties properties) {
		super("additionaladditions", DelightfulItemTags.GOLD_RING, DelightfulTiers.GILDED_NETHERITE, properties);
	}

	@Override
	public Supplier<Ingredient> getSmithingBase() {
		return Util.ing(ModItems.NETHERITE_KNIFE);
	}
}

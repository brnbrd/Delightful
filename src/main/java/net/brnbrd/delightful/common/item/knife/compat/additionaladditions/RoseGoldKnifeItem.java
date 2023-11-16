package net.brnbrd.delightful.common.item.knife.compat.additionaladditions;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.apache.commons.lang3.tuple.ImmutablePair;
import vectorwing.farmersdelight.common.registry.ModItems;

public class RoseGoldKnifeItem extends CompatKnifeItem {
	public final static TagKey<Item> upgrade = Util.it(Mods.AA, "rose_gold_upgrade");

	public RoseGoldKnifeItem(Properties properties) {
		super(Mods.AA, DelightfulItemTags.ROSE_GOLD_ALLOY, DelightfulTiers.ROSE_GOLD, properties);
	}

	@Override
	public ImmutablePair<Ingredient, Ingredient> getSmithing() {
		return new ImmutablePair<>(
			Ingredient.of(upgrade),
			Util.ing(ModItems.IRON_KNIFE)
		);
	}
}

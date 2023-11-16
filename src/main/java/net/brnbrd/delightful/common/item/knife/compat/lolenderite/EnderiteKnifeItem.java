package net.brnbrd.delightful.common.item.knife.compat.lolenderite;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.apache.commons.lang3.tuple.ImmutablePair;
import vectorwing.farmersdelight.common.registry.ModItems;

public class EnderiteKnifeItem extends CompatKnifeItem {
	public final static TagKey<Item> upgrade = Util.it(Mods.LE, "enderite_upgrade_smithing_template");

	public EnderiteKnifeItem(Properties properties) {
		super(Mods.LE, DelightfulItems.ingot("enderite"), DelightfulTiers.ENDERITE, properties);
	}

	@Override
	public ImmutablePair<Ingredient, Ingredient> getSmithing() {
		return new ImmutablePair<>(
			Ingredient.of(upgrade),
			Util.ing(ModItems.NETHERITE_KNIFE)
		);
	}
}

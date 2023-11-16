package net.brnbrd.delightful.common.item.knife.compat.oresabovediamonds;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class NetheriteOpalKnifeItem extends CompatKnifeItem {
	public NetheriteOpalKnifeItem(Properties properties) {
		super("oresabovediamonds", Tags.Items.INGOTS_NETHERITE, DelightfulTiers.NETHERITE_OPAL, properties, ChatFormatting.DARK_PURPLE);
	}

	@Override
	public ImmutablePair<Ingredient, Ingredient> getSmithing() {
		return new ImmutablePair<>(
			Util.ing(() -> Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
			Util.ing(Knives.BLACK_OPAL)
		);
	}
}
package net.brnbrd.delightful.common.item.knife.compat.oresabovediamonds;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import java.util.function.Supplier;

public class NetheriteOpalKnifeItem extends CompatKnifeItem {
	public NetheriteOpalKnifeItem(Properties properties) {
		super("oresabovediamonds", Tags.Items.INGOTS_NETHERITE, DelightfulTiers.NETHERITE_OPAL, properties);
	}

	@Override
	public Supplier<Ingredient> getSmithingBase() {
		return Util.ing(Knives.BLACK_OPAL);
	}
}
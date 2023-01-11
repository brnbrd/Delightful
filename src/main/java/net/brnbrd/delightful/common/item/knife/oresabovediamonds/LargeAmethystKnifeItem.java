package net.brnbrd.delightful.common.item.knife.oresabovediamonds;

import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;

public class LargeAmethystKnifeItem extends CompatKnifeItem {
	public LargeAmethystKnifeItem(Properties properties) {
		super("oresabovediamonds", DelightfulItems.gem("large_amethyst"), DelightfulTiers.LARGE_AMETHYST, properties, null);
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}
}
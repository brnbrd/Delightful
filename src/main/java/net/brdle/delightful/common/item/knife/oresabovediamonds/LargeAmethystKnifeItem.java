package net.brdle.delightful.common.item.knife.oresabovediamonds;

import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;

public class LargeAmethystKnifeItem extends CompatKnifeItem {
	public LargeAmethystKnifeItem(Properties properties) {
		super("oresabovediamonds", DelightfulItems.gem("large_amethyst"), DelightfulTiers.LARGE_AMETHYST, properties, null);
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}
}
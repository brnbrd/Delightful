package net.brdle.delightful.common.item.knife.oresabovediamonds;

import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import java.util.Optional;

public class LargeAmethystKnifeItem extends CompatKnifeItem {
	public LargeAmethystKnifeItem(Properties properties) {
		super("oresabovediamonds", DelightfulItems.gem("large_amethyst"), DelightfulTiers.LARGE_AMETHYST, 0.5F, -2.0F, properties, Optional.empty());
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}
}

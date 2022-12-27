package net.brdle.delightful.common.item.knife.create_sa;

import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.brdle.delightful.data.DelightfulItemTags;

public class GildedQuartzKnifeItem extends CompatKnifeItem {
	public GildedQuartzKnifeItem(Properties properties) {
		super("create_sa", DelightfulItemTags.POLISHED_ROSE_QUARTZ, DelightfulTiers.GILDED_QUARTZ, properties, null);
	}

	@Override
	public boolean genRecipe() {
		return false;
	}
}

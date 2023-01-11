package net.brnbrd.delightful.common.item.knife.create_sa;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.data.DelightfulItemTags;

public class GildedQuartzKnifeItem extends CompatKnifeItem {
	public GildedQuartzKnifeItem(Properties properties) {
		super("create_sa", DelightfulItemTags.POLISHED_ROSE_QUARTZ, DelightfulTiers.GILDED_QUARTZ, properties, null);
	}

	@Override
	public boolean genRecipe() {
		return false;
	}
}

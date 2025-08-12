package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PricklyPearJuiceItem extends CompatDrinkItem {
	public PricklyPearJuiceItem(Properties properties) {
		super(properties, true, false);
	}

	@Override
	public TagKey<Item> getDependencyTag() {
		return DelightfulItemTags.FRUITS_PRICKLY_PEAR;
	}
}
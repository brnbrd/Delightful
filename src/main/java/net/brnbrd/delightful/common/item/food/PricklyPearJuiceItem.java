package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PricklyPearJuiceItem extends CompatDrinkItem {
	public PricklyPearJuiceItem(Properties properties) {
		super(properties.stacksTo(16), true, false);
	}

	public PricklyPearJuiceItem(Properties properties, Modid... modid) {
		super(properties.stacksTo(16), true, false, modid);
	}

	@Override
	public TagKey<Item> getDependencyTag() {
		return DelightfulItemTags.FRUITS_PRICKLY_PEAR;
	}
}
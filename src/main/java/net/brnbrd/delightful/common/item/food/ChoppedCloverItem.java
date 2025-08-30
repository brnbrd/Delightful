package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;

public class ChoppedCloverItem extends DConsumableItem {
	public ChoppedCloverItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean enabled() {
		return super.enabled() && (
			Mods.loaded(Modid.BB) || // Buzzier Bees loaded (contains purposefully not-tagged clovers)
			Util.tagPopulated(DelightfulItemTags.CLOVER)
		);
	}
}
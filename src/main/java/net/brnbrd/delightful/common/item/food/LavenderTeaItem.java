package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;

public class LavenderTeaItem extends TeaItem {
	public LavenderTeaItem(Properties properties) {
		super(properties, DelightfulItemTags.LAVENDER, false);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.WS};
	}
}
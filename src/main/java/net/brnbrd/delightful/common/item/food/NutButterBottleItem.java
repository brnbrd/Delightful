package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;

public class NutButterBottleItem extends DConsumableItem {
	public NutButterBottleItem(Properties properties) {
		super(properties, false, false);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.VD};
	}
}
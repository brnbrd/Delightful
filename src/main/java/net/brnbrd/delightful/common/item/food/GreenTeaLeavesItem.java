package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;

public class GreenTeaLeavesItem extends DConsumableItem {
	public GreenTeaLeavesItem(Properties prop) {
		super(prop, false, false);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.FR, Modid.YH, Modid.CT};
	}
}
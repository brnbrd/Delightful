package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;

public class GoatMeatItem extends DConsumableItem {
	public GoatMeatItem(Properties prop) {
		super(prop);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.GO, Modid.WS, Modid.DTM};
	}
}
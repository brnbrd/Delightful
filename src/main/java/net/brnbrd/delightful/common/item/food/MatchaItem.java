package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;

public class MatchaItem extends DConsumableItem {
	public MatchaItem(Properties prop) {
		super(prop, true, false);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.YH};
	}
}
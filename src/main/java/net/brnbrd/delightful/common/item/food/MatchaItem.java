package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.common.item.DItem;
import net.brnbrd.delightful.compat.Modid;

public class MatchaItem extends DItem {
	public MatchaItem(Properties prop) {
		super(prop);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.YH};
	}
}
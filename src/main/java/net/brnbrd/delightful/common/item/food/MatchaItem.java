package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Mods;

public class MatchaItem extends DConsumableItem {
	public MatchaItem(Properties prop) {
		super(prop, true, false);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{Mods.YH};
	}
}

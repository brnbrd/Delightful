package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;

public class CheeseburgerItem extends DConsumableItem {
	public CheeseburgerItem(Properties properties) {
		super(properties, true, false);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.VD};
	}
}
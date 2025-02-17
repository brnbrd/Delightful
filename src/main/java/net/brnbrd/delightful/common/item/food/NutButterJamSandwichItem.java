package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;

public class NutButterJamSandwichItem extends DConsumableItem {
	public NutButterJamSandwichItem(Properties properties) {
		super(properties, true, false);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.CT};
	}
}
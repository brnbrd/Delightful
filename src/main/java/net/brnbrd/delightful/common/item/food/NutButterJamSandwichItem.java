package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Mods;

public class NutButterJamSandwichItem extends DConsumableItem {
	public NutButterJamSandwichItem(Properties properties) {
		super(properties, true, false);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{Mods.CT};
	}
}
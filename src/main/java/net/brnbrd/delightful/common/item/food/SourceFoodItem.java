package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Mods;

public class SourceFoodItem extends CompatConsumableItem {
	public SourceFoodItem(Properties properties) {
		super(properties, true, false, Mods.AN);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{Mods.AND};
	}
}
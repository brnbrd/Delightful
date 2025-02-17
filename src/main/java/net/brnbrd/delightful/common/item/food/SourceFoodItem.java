package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;

public class SourceFoodItem extends CompatConsumableItem {
	public SourceFoodItem(Properties properties) {
		super(properties, true, false, Modid.AN);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.AND};
	}
}
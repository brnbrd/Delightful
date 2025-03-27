package net.brnbrd.delightful.compat.ars_nouveau;

import net.brnbrd.delightful.common.item.food.CompatConsumableItem;
import net.brnbrd.delightful.compat.Modid;

public class SourceBerryCookieItem extends CompatConsumableItem {
	public SourceBerryCookieItem(Properties properties) {
		super(properties, true, false, Modid.AN);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.AND};
	}
}
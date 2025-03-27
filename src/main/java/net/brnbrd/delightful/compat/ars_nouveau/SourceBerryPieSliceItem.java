package net.brnbrd.delightful.compat.ars_nouveau;

import net.brnbrd.delightful.common.item.food.CompatPieSliceItem;
import net.brnbrd.delightful.compat.Modid;

public class SourceBerryPieSliceItem extends CompatPieSliceItem {
	public SourceBerryPieSliceItem(Properties properties) {
		super(properties, Modid.AN);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.AND};
	}
}
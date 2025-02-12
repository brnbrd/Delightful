package net.brnbrd.delightful.common.item.food;

import vectorwing.farmersdelight.FarmersDelight;

public class PumpkinPieSliceItem extends CompatPieSliceItem {
	public PumpkinPieSliceItem(Properties prop) {
		super(prop, FarmersDelight.MODID);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{"create_central_kitchen"};
	}
}
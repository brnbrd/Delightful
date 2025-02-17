package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Modid;

public class PumpkinPieSliceItem extends CompatPieSliceItem {
	public PumpkinPieSliceItem(Properties prop) {
		super(prop, Util.EMPTY);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.CCK};
	}
}
package net.brnbrd.delightful.common.item.food;

public class PumpkinPieSliceItem extends CompatPieSliceItem {
	public PumpkinPieSliceItem(Properties prop) {
		super(prop, null);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{"create_central_kitchen"};
	}
}
package net.brnbrd.delightful.common.item.food;

public class GoatMeatItem extends DConsumableItem {
	public GoatMeatItem(Properties prop) {
		super(prop);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{"goated", "dropthemeat"};
	}
}
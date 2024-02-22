package net.brnbrd.delightful.common.item.food;

public class NutButterBottleItem extends DConsumableItem {
	public NutButterBottleItem(Properties properties) {
		super(properties, false, false);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{"vintagedelight"};
	}
}

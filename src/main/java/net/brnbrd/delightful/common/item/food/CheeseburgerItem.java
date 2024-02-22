package net.brnbrd.delightful.common.item.food;

public class CheeseburgerItem extends DConsumableItem {
	public CheeseburgerItem(Properties properties) {
		super(properties, true, false);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{"vintagedelight"};
	}
}

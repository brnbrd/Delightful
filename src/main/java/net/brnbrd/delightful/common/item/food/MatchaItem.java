package net.brnbrd.delightful.common.item.food;

public class MatchaItem extends DConsumableItem {
	public MatchaItem(Properties prop) {
		super(prop, true, false);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{"youkaishomecoming"};
	}
}

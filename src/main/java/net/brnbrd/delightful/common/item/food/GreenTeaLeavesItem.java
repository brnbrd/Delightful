package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Mods;

public class GreenTeaLeavesItem extends DConsumableItem {
	public GreenTeaLeavesItem(Properties prop) {
		super(prop, false, false);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{Mods.FR, "croptopia", "youkaishomecoming"};
	}
}

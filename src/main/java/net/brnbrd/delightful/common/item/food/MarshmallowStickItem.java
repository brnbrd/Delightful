package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;

public class MarshmallowStickItem extends DConsumableItem {
	public MarshmallowStickItem(Properties properties, boolean hasFoodEffectTooltip) {
		super(properties, hasFoodEffectTooltip, false);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.HH};
	}
}
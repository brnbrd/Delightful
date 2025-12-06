package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;

public class AgedRoeItem extends CompatConsumableItem {
	public AgedRoeItem(Properties properties, boolean hasCustomTooltip) {
		super(properties, true, hasCustomTooltip);
	}

	@Override
	public Modid[] getModid() {
		return new Modid[]{Modid.LFL, Modid.BC};
	}
}
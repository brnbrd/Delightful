package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;

public class CompatConsumableItem extends DConsumableItem {
	private final Modid[] modid;

	public CompatConsumableItem(Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip, Modid... modid) {
		super(properties, hasPotionEffectTooltip, hasCustomTooltip);
		this.modid = modid;
	}

	@Override
	public Modid[] getModid() {
		return modid;
	}
}
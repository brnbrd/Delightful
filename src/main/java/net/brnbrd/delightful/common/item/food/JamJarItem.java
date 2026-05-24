package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;

public class JamJarItem extends DConsumableItem {
	public JamJarItem(Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
		super(properties.stacksTo(16), hasPotionEffectTooltip, hasCustomTooltip);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.BC, Modid.FRD, Modid.HH};
	}
}
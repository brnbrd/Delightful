package net.brnbrd.delightful.common.item.food;

public class JamJarItem extends DConsumableItem {
	public JamJarItem(Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
		super(properties, hasPotionEffectTooltip, hasCustomTooltip);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{"fruitsdelight"};
	}
}

package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Mods;

public class JamJarItem extends DConsumableItem {

	public JamJarItem(Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
		super(properties, hasPotionEffectTooltip, hasCustomTooltip);
	}

	@Override
	public boolean enabled() {
		return !Mods.loaded("fruitsdelight") && super.enabled();
	}
}

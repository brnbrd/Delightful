package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.compat.Mods;

public class CompatConsumableItem extends DConsumableItem {
	private final Modid[] modid;
	private final Mods.Strategy strategy;

	public CompatConsumableItem(Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip, Modid... modid) {
		super(properties, hasPotionEffectTooltip, hasCustomTooltip);
		this.modid = modid;
		this.strategy = Mods.Strategy.OR;
	}

	public CompatConsumableItem(Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip, Mods.Strategy strategy, Modid... modid) {
		super(properties, hasPotionEffectTooltip, hasCustomTooltip);
		this.modid = modid;
		this.strategy = Mods.Strategy.AND;
	}

	@Override
	public Modid[] getModid() {
		return modid;
	}

	@Override
	public Mods.Strategy getStrategy() {
		return strategy;
	}
}
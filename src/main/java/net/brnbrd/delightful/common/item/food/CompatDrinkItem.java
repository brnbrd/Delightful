package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.common.item.ICompat;
import net.brnbrd.delightful.compat.Modid;
import net.minecraft.world.item.Item;

public class CompatDrinkItem extends DrinkItem implements ICompat {
	private final Modid[] modid;

	public CompatDrinkItem(Item.Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip, Modid... modid) {
		super(properties, hasPotionEffectTooltip, hasCustomTooltip);
		this.modid = modid;
	}

	@Override
	public Modid[] getModid() {
		return modid;
	}
}
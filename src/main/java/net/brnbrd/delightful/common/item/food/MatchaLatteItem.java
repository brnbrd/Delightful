package net.brnbrd.delightful.common.item.food;

import net.minecraft.world.item.Items;

public class MatchaLatteItem extends DrinkItem {
	public MatchaLatteItem(Properties properties, boolean hasCustomTooltip) {
		super(
			properties.craftRemainder(Items.GLASS_BOTTLE).stacksTo(16),
			true,
			hasCustomTooltip
		);
	}

	@Override
	public float getHeal() {
		return 2F;
	}
}
package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;
import net.minecraft.world.item.Items;

public class CupItem extends CompatConsumableItem {
	public CupItem(Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
		super(
				properties.stacksTo(16).craftRemainder(Modid.MD.item("copper_cup", Items.BOWL)),
				hasFoodEffectTooltip,
				hasCustomTooltip,
				Modid.MD
		);
	}
}
package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.world.item.Items;

public class VenisonStewCupItem extends VenisonStewItem {
	public VenisonStewCupItem(Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
		super(
			properties
				.stacksTo(16)
				.craftRemainder(Util.item(Util.rl(Mods.MD, "copper_cup"), Items.BOWL)),
			hasPotionEffectTooltip,
			hasCustomTooltip
		);
	}

	@Override
	public boolean enabled() {
		return Mods.loaded(Mods.MD) && super.enabled();
	}
}
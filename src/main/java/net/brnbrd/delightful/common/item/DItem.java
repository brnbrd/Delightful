package net.brnbrd.delightful.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;
import java.util.List;

public class DItem extends Item implements IConfigured {
	private final boolean hasFoodEffectTooltip;

	public DItem(Item.Properties prop) {
		super(prop);
		this.hasFoodEffectTooltip = false;
	}

	public DItem(Item.Properties properties, boolean hasFoodEffectTooltip) {
		super(properties);
		this.hasFoodEffectTooltip = hasFoodEffectTooltip;
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		if (this.enabledText(comps)) {
			if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
				if (this.hasFoodEffectTooltip) {
					TextUtils.addFoodEffectTooltip(stack, comps, 1.0F);
				}
			}
			super.appendHoverText(stack, level, comps, pIsAdvanced);
		}
	}
}
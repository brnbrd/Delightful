package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.common.item.IConfigured;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DConsumableItem extends ConsumableItem implements IConfigured {
	public DConsumableItem(Item.Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
		super(properties, hasFoodEffectTooltip, hasCustomTooltip);
	}

	public DConsumableItem(Item.Properties properties) {
		super(properties, false, false);
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag isAdvanced) {
		this.enabledText(comps);
		super.appendHoverText(stack, level, comps, isAdvanced);
	}
}
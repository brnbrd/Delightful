package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.common.item.ICompat;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class CompatConsumableItem extends DConsumableItem implements ICompat {
	private final String[] modid;

	public CompatConsumableItem(Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip, String... modid) {
		super(properties, hasPotionEffectTooltip, hasCustomTooltip);
		this.modid = modid;
	}

	@Override
	public String[] getModid() {
		return modid;
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		if (this.enabledText(comps)) {
			super.appendHoverText(stack, level, comps, pIsAdvanced);
		}
	}
}

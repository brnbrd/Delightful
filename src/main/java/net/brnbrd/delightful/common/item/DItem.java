package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class DItem extends Item implements IConfigured {
	public DItem(Properties prop) {
		super(prop);
	}

	@Override
	public boolean isEnabled() {
		return Util.enabled(this);
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		if (!this.isEnabled()) {
			comps.add(Component.translatable("tooltip.config_disabled").withStyle(ChatFormatting.UNDERLINE));
			return;
		}
		super.appendHoverText(stack, level, comps, pIsAdvanced);
	}
}
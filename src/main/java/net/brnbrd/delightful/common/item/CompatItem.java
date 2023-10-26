package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.compat.Mods;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class CompatItem extends DItem {
	private final String modid;

	public CompatItem(Properties prop, String modid) {
		super(prop);
		this.modid = modid;
	}

	public boolean isLoaded() {
		return Mods.loaded(modid);
	}

	@Override
	public boolean isEnabled() {
		return this.isLoaded() && super.isEnabled();
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		if (!this.isLoaded()) {
			comps.add(Component.translatable("tooltip.requires_modid"));
			comps.add(Component.literal(this.modid).withStyle(ChatFormatting.UNDERLINE));
			return;
		}
		super.appendHoverText(stack, level, comps, pIsAdvanced);
	}
}
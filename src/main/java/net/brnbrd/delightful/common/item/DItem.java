package net.brnbrd.delightful.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DItem extends Item implements IConfigured {
	public DItem(Item.Properties prop) {
		super(prop);
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		this.enabledText(comps);
		super.appendHoverText(stack, level, comps, pIsAdvanced);
	}
}
package net.brdle.delightful.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class DescriptItem extends Item {
	Component desc;

	public DescriptItem(Properties prop, Component desc) {
		super(prop);
		this.desc = desc;
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tool, TooltipFlag pIsAdvanced) {
		super.appendHoverText(pStack, pLevel, tool, pIsAdvanced);
		tool.add(desc);
	}
}

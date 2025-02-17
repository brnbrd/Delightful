package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.compat.Modid;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class CompatBlockItem extends BlockItem implements ICompat {
	private final Modid[] modid;

	public CompatBlockItem(Block block, Item.Properties props, Modid... modid) {
		super(block, props);
		this.modid = modid;
	}

	@Override
	public Modid[] getModid() {
		return modid;
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		if (this.enabledText(comps)) {
			super.appendHoverText(stack, level, comps, pIsAdvanced);
		}
	}
}
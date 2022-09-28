package net.brdle.delightful.common.block;

import net.minecraft.world.item.ItemStack;

public interface ISliceable {
	public ItemStack getSliceItem();
	public int getMaxBites();
}

package net.brnbrd.delightful.common.block;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public interface ISliceable {
	IntegerProperty getBitesProperty();
	ItemStack getSliceItem();
	int getMaxBites();
	int getSliceSize();
	float getBaseHeight();
	default float getHeight(int bites) {
		return this.getBaseHeight() - ((bites - 1) * getSliceSize());
	}
}

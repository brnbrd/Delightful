package net.brnbrd.delightful.common.item.food;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GummyItem extends DConsumableItem {

	public GummyItem(Properties prop) {
		super(prop, true, false);
	}

	@Override
	public int getUseDuration(@NotNull ItemStack stack) {
		return 14;
	}
}
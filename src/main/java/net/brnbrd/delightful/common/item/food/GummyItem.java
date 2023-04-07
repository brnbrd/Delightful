package net.brnbrd.delightful.common.item.food;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GummyItem extends Item {

	public GummyItem(Properties prop) {
		super(prop);
	}

	@Override
	public int getUseDuration(@NotNull ItemStack stack) {
		return 14;
	}
}

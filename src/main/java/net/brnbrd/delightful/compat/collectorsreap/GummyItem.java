package net.brnbrd.delightful.compat.collectorsreap;

import net.brnbrd.delightful.common.item.food.DConsumableItem;
import net.brnbrd.delightful.compat.Modid;
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

	@Override
	public @NotNull Modid[] getModid() {
		return new Modid[]{ Modid.CR };
	}
}
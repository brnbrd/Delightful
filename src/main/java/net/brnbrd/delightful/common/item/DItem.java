package net.brnbrd.delightful.common.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;
import net.brnbrd.delightful.Util;

public class DItem extends Item implements IConfigured {
	public DItem(Properties prop) {
		super(prop.tab(FarmersDelight.CREATIVE_TAB));
	}

	@Override
	public boolean isEnabled() {
		return Util.enabled(this);
	}

	@Override
	protected boolean allowedIn(@NotNull CreativeModeTab cat) {
		return this.isEnabled() && super.allowedIn(cat);
	}
}
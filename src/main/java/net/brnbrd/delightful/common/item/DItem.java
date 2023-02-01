package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.common.DelightfulConfig;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;

public class DItem extends Item implements IConfigured {
	public DItem(Properties prop) {
		super(prop.tab(FarmersDelight.CREATIVE_TAB));
	}

	@Override
	public boolean isEnabled() {
		return DelightfulConfig.verify(this);
	}

	@Override
	protected boolean allowedIn(@NotNull CreativeModeTab cat) {
		return this.isEnabled() && super.allowedIn(cat);
	}
}
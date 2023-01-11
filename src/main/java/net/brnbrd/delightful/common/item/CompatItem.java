package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.common.config.DelightfulConfig;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;

public class CompatItem extends Item implements IConfigured {
	private final String modid;

	public CompatItem(Properties prop, String modid) {
		super(prop.tab(FarmersDelight.CREATIVE_TAB));
		this.modid = modid;
	}

	@Override
	public boolean isEnabled() {
		return Mods.loaded(modid) && DelightfulConfig.verify(this);
	}

	@Override
	protected boolean allowedIn(@NotNull CreativeModeTab cat) {
		return this.isEnabled() && super.allowedIn(cat);
	}
}

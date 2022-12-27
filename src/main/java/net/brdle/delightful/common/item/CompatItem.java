package net.brdle.delightful.common.item;

import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.compat.Mods;
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
	protected boolean allowdedIn(@NotNull CreativeModeTab pCategory) {
		return this.isEnabled() && super.allowdedIn(pCategory);
	}
}

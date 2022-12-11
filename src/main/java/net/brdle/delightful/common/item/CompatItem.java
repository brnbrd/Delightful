package net.brdle.delightful.common.item;

import net.brdle.delightful.common.config.DelightfulConfig;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import vectorwing.farmersdelight.FarmersDelight;

public class CompatItem extends Item implements IConfigured {
	private final String modid;

	public CompatItem(Properties prop, String modid) {
		super(prop.tab(FarmersDelight.CREATIVE_TAB));
		this.modid = modid;
	}

	@Override
	public boolean isEnabled() {
		return ModList.get().isLoaded(modid) && DelightfulConfig.verify(this);
	}

	@Override
	protected boolean allowedIn(CreativeModeTab cat) {
		return this.isEnabled() && super.allowedIn(cat);
	}
}

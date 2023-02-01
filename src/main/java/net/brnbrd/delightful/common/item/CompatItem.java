package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.compat.Mods;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.NotNull;

public class CompatItem extends DItem implements IConfigured {
	private final String modid;

	public CompatItem(Properties prop, String modid) {
		super(prop);
		this.modid = modid;
	}

	@Override
	public boolean isEnabled() {
		return Mods.loaded(modid) && super.isEnabled();
	}

	@Override
	protected boolean allowedIn(@NotNull CreativeModeTab cat) {
		return this.isEnabled() && super.allowedIn(cat);
	}
}
package net.brnbrd.delightful.common.item;

import net.minecraft.world.item.Item;

public class CompatItem extends DItem implements ICompat {
	private final String[] modid;

	public CompatItem(Item.Properties prop, String... modid) {
		super(prop, false);
		this.modid = modid;
	}

	public CompatItem(Item.Properties properties, boolean hasFoodEffectTooltip, String... modid) {
		super(properties, hasFoodEffectTooltip);
		this.modid = modid;
	}

	@Override
	public String[] getModid() {
		return modid;
	}
}
package net.brnbrd.delightful.common.item;

import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public class CompatItem extends DItem implements ICompat {
	private final String[] modid;
	@Nullable private String conflict = null;

	public CompatItem(Item.Properties prop, String... modid) {
		super(prop, false);
		this.modid = modid;
	}

	public CompatItem(Item.Properties prop, boolean hasFoodEffectTooltip, @Nullable String conflict, String... modid) {
		super(prop, hasFoodEffectTooltip);
		this.conflict = conflict;
		if (modid != null && modid.length > 0) {
			this.modid = modid.clone();
		} else {
			this.modid = new String[0];
		}
	}

	@Override
	public String[] getModid() {
		return this.modid;
	}

	@Override
	public String[] getConflicts() {
		if (this.conflict != null && !this.conflict.isEmpty()) {
			return new String[]{this.conflict};
		}
		return super.getConflicts();
	}

	@Override
	public boolean enabled() {
		return super.enabled() && ICompat.super.enabled();
	}
}
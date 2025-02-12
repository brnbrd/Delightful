package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public class VenisonChopsItem extends DConsumableItem {
	public VenisonChopsItem(Properties prop) {
		super(prop);
	}

	@Override
	public @Nullable TagKey<Item> getDependencyTag() {
		return DelightfulItemTags.RAW_VENISON_COMPAT;
	}

	@Override
	public @Nullable TagKey<Item> getEmptyTag() {
		return DelightfulItemTags.RAW_VENISON_CHOP_COMPAT;
	}

	@Override
	public String[] getConflicts() {
		return new String[]{Mods.AD, Mods.TFD};
	}
}
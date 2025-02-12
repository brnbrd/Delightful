package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public class CrabRangoonItem extends DConsumableItem {
	public CrabRangoonItem(Properties properties) {
		super(properties);
	}

	@Override
	public @Nullable TagKey<Item> getDependencyTag() {
		return DelightfulItemTags.CRAB_MEAT;
	}
}
package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public class RockCandyItem extends DConsumableItem {
	public RockCandyItem(Properties properties) {
		super(properties, true, false);
	}

	@Override
	public @Nullable TagKey<Item> getDependencyTag() {
		return DelightfulItemTags.GEMS_ROSE_QUARTZ;
	}
}
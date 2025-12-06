package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public class AgedFishRoeItem extends AgedRoeItem {
	public AgedFishRoeItem(Properties properties, boolean hasCustomTooltip) {
		super(properties, hasCustomTooltip);
	}

	@Override
	public @Nullable TagKey<Item> getDependencyTag() {
		return DelightfulItemTags.FISH_ROE;
	}
}
package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public class HoneyGlazedWalnutItem extends DConsumableItem {
	public HoneyGlazedWalnutItem(Properties properties) {
		super(properties);
	}

	@Override
	public @Nullable TagKey<Item> getDependencyTag() {
		return DelightfulItemTags.NUTS_WALNUT;
	}
}
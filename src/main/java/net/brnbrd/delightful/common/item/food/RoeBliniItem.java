package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public class RoeBliniItem extends DConsumableItem {
	public RoeBliniItem(Properties properties) {
		super(properties, true, false);
	}

	@Override
	public Modid[] getModid() {
		return new Modid[]{Modid.BC};
	}

	@Override
	public @Nullable TagKey<Item> getDependencyTag() {
		return DelightfulItemTags.ROE;
	}
}
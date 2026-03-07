package net.brnbrd.delightful.compat.brewinandchewin;

import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.Nullable;

public class CaviarItem extends AgedRoeItem {
	public CaviarItem(Fluid fluid, Item.Properties properties) {
		super(fluid, properties);
	}

	@Override
	public @Nullable TagKey<Item> getDependencyTag() {
		return DelightfulItemTags.RAW_FISHES_STURGEON;
	}

	@Override
	public Modid[] getModid() {
		return new Modid[]{Modid.LFL, Modid.BC, Modid.TIDE};
	}
}
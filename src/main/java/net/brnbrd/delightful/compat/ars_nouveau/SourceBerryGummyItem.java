package net.brnbrd.delightful.compat.ars_nouveau;

import net.brnbrd.delightful.common.item.ICompat;
import net.brnbrd.delightful.common.item.food.GummyItem;
import net.brnbrd.delightful.compat.Modid;

public class SourceBerryGummyItem extends GummyItem implements ICompat {
	public SourceBerryGummyItem(Properties prop) {
		super(prop);
	}

	@Override
	public Modid[] getModid() {
		return new Modid[]{Modid.AN};
	}

	@Override
	public boolean enabled() {
		return super.enabled() && ICompat.super.enabled();
	}
}
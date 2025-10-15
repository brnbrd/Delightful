package net.brnbrd.delightful.compat.ars_nouveau;

import net.brnbrd.delightful.common.item.food.GummyItem;
import net.brnbrd.delightful.compat.Modid;

public class SourceBerryGummyItem extends GummyItem {
	public SourceBerryGummyItem(Properties prop) {
		super(prop);
	}

	@Override
	public Modid[] getModid() {
		return new Modid[]{Modid.AN};
	}
}
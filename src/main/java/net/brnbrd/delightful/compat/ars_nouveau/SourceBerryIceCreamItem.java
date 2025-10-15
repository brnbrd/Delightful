package net.brnbrd.delightful.compat.ars_nouveau;

import net.brnbrd.delightful.common.item.food.IceCreamItem;
import net.brnbrd.delightful.compat.Modid;

public class SourceBerryIceCreamItem extends IceCreamItem {
	public SourceBerryIceCreamItem(Properties properties) {
		super(properties);
	}

	@Override
	public Modid[] getModid() {
		return new Modid[]{Modid.AN};
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.COS};
	}
}
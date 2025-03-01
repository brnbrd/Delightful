package net.brnbrd.delightful.common.item.knife.compat;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;

public class SilverKnifeItem extends DKnifeItem {
	public SilverKnifeItem(Properties properties) {
		super(DelightfulItemTags.ingot("silver"), DelightfulTiers.SILVER, properties);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.AD};
	}
}
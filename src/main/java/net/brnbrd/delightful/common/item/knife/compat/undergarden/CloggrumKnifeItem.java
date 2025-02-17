package net.brnbrd.delightful.common.item.knife.compat.undergarden;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;

public class CloggrumKnifeItem extends DKnifeItem {
	public CloggrumKnifeItem(Properties properties) {
		super(DelightfulItemTags.ingot("cloggrum"), DelightfulTiers.CLOGGRUM, properties, Modid.UG);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.UGD};
	}
}
package net.brnbrd.delightful.common.item.knife.compat;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;

public class ElectrumKnifeItem extends DKnifeItem {
	public ElectrumKnifeItem(Properties properties) {
		super(DelightfulItemTags.ingot("electrum"), DelightfulTiers.ELECTRUM, properties);
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.OG};
	}
}
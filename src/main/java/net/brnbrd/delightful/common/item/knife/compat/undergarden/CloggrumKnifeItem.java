package net.brnbrd.delightful.common.item.knife.compat.undergarden;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;

public class CloggrumKnifeItem extends DKnifeItem {
	public CloggrumKnifeItem(Properties properties) {
		super(DelightfulItemTags.ingot("cloggrum"), DelightfulTiers.CLOGGRUM, properties, Mods.UG);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{Mods.UGD};
	}
}
package net.brdle.delightful.common.item.knife.lolenderite;

import net.brdle.delightful.Util;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;

public class ObsdianInfusedEnderiteKnifeItem extends CompatKnifeItem {
	public ObsdianInfusedEnderiteKnifeItem(Properties properties) {
		super("lolenderite", DelightfulItems.ingot("obsidian_infused_enderite"), DelightfulTiers.OBSIDIAN_INFUSED_ENDERITE, properties, Util.ing(DelightfulItems.ENDERITE_KNIFE));
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}
}

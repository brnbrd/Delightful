package net.brdle.delightful.common.item.knife.lolenderite;

import net.brdle.delightful.Util;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.brdle.delightful.common.item.knife.Knives;
import net.brdle.delightful.compat.Mods;

public class ObsdianInfusedEnderiteKnifeItem extends CompatKnifeItem {
	public ObsdianInfusedEnderiteKnifeItem(Properties properties) {
		super(Mods.LE, DelightfulItems.ingot("obsidian_infused_enderite"), DelightfulTiers.OBSIDIAN_INFUSED_ENDERITE, properties, Util.ing(Knives.ENDERITE));
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}
}

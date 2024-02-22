package net.brnbrd.delightful.common.item.knife.compat.undergarden;

import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.compat.Mods;

public class CloggrumKnifeItem extends CompatKnifeItem {
	public CloggrumKnifeItem(Properties properties) {
		super(Mods.UG, DelightfulItems.ingot("cloggrum"), DelightfulTiers.CLOGGRUM, properties);
	}

	@Override
	public String[] getConflicts() {
		return new String[]{"undergardendelight"};
	}
}

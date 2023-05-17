package net.brnbrd.delightful.common.item.knife.phantasm;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;

public class StelliumKnifeItem extends CompatKnifeItem {
	public StelliumKnifeItem(Properties properties) {
		super(Mods.EP, DelightfulItemTags.STELLIUM_INGOT, DelightfulTiers.STELLIUM, properties, null);
	}
}

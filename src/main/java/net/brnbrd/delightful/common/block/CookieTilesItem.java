package net.brnbrd.delightful.common.block;

import net.brnbrd.delightful.common.item.CompatBlockItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.world.level.block.Block;

public class CookieTilesItem extends CompatBlockItem {
	public CookieTilesItem(Block block, Properties props, Modid modid) {
		super(block, props, Modid.COOK, modid);
	}

	public CookieTilesItem(Block block, Properties props) {
		super(block, props, Modid.COOK);
	}

	@Override
	public Mods.Strategy getStrategy() {
		return Mods.Strategy.AND;
	}
}
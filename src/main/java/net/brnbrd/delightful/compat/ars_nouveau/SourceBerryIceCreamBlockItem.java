package net.brnbrd.delightful.compat.ars_nouveau;

import net.brnbrd.delightful.common.item.CompatBlockItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.compat.Strategy;
import net.minecraft.world.level.block.Block;

public class SourceBerryIceCreamBlockItem extends CompatBlockItem {
	public SourceBerryIceCreamBlockItem(Block block, Properties props) {
		super(block, props, Mods.AN, Mods.N);
	}

	@Override
	public Strategy getStrategy() {
		return Strategy.AND;
	}
}
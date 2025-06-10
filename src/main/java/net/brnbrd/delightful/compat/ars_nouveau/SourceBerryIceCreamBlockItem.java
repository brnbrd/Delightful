package net.brnbrd.delightful.compat.ars_nouveau;

import net.brnbrd.delightful.common.item.CompatBlockItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.compat.Strategy;
import net.minecraft.world.level.block.Block;

public class SourceBerryIceCreamBlockItem extends CompatBlockItem {
	public SourceBerryIceCreamBlockItem(Block block, Properties props) {
		super(block, props, Modid.AN, Modid.N);
	}

	@Override
	public Strategy getStrategy() {
		return Strategy.AND;
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.COS};
	}
}
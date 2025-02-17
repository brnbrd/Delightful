package net.brnbrd.delightful.compat.ars_nouveau;

import net.brnbrd.delightful.common.item.CompatBlockItem;
import net.brnbrd.delightful.compat.Modid;
import net.minecraft.world.level.block.Block;

public class ArsCrateBlockItem extends CompatBlockItem {
	public ArsCrateBlockItem(Block block, Properties props) {
		super(block, props, Modid.AN);
	}

	@Override
	public boolean enabled() {
		return super.enabled() && !Modid.AND.loaded();
	}
}
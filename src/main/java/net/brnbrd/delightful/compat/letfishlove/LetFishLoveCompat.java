package net.brnbrd.delightful.compat.letfishlove;

import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.compat.Modid;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class LetFishLoveCompat {
	public static Block sturgeonRoeBlock() {
		return new DRoeBlock(Modid.TIDE.rl("sturgeon"));
	}

	public static Item sturgeonRoeItem() {
		return new DRoeItem(DelightfulBlocks.STURGEON_ROE);
	}
}
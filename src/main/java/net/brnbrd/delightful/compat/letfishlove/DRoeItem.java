package net.brnbrd.delightful.compat.letfishlove;

import com.uraneptus.letfishlove.common.items.RoeItem;
import net.minecraft.world.level.block.Block;
import java.util.function.Supplier;

public class DRoeItem extends RoeItem {
	public DRoeItem(Supplier<Block> roeBlock) {
		super(roeBlock);
	}
}
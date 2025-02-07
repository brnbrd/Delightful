package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.common.item.CompatItem;
import org.jetbrains.annotations.Nullable;

public class CompatPieSliceItem extends CompatItem {
	public CompatPieSliceItem(Properties prop, @Nullable String conflict, String... modid) {
		super(prop, true, conflict, modid);
	}
}
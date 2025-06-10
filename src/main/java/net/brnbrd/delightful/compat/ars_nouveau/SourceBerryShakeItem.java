package net.brnbrd.delightful.compat.ars_nouveau;

import net.brnbrd.delightful.common.item.ICompat;
import net.brnbrd.delightful.common.item.food.ShakeItem;
import net.brnbrd.delightful.compat.Modid;
import org.jetbrains.annotations.NotNull;

public class SourceBerryShakeItem extends ShakeItem implements ICompat {
	public SourceBerryShakeItem(Properties properties) {
		super(properties);
	}

	@Override
	public @NotNull Modid[] getModid() {
		return new Modid[]{Modid.AN};
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.COS};
	}
}
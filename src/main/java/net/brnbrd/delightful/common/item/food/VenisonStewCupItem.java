package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.ICompat;
import net.brnbrd.delightful.compat.Modid;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class VenisonStewCupItem extends VenisonStewItem implements ICompat {
	public VenisonStewCupItem(Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
		super(
			properties
				.stacksTo(16)
				.craftRemainder(Util.item(Modid.MD, "copper_cup", Items.BOWL)),
			hasPotionEffectTooltip,
			hasCustomTooltip
		);
	}

	@Override
	public @NotNull Modid[] getModid() {
		return new Modid[]{Modid.MD};
	}
}
package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Modid;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class VenisonStewCupItem extends VenisonStewItem {
	public VenisonStewCupItem(Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
		super(
			properties
				.stacksTo(16)
				.craftRemainder(Modid.MD.item("copper_cup", Items.BOWL)),
			hasPotionEffectTooltip,
			hasCustomTooltip
		);
	}

	@Override
	public @NotNull Modid[] getModid() {
		return new Modid[]{Modid.MD};
	}
}
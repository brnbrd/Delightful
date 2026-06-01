package net.brnbrd.delightful.common.events;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;
import javax.annotation.Nullable;

public class PieEvents {
	public static boolean isCompatPie(ItemStack pie) {
		return pie.is(DelightfulItemTags.COMPAT_PIES) && Util.configEnabled(Util.name(pie) + "_slice");
	}

	public static @Nullable BlockItem getPieBlockItem(ItemStack pie) {
		return Util.item(Util.delight("debug_" + Util.name(pie))) instanceof BlockItem blockItem ? blockItem : null;
	}

	// Adds "Placeable" tooltip to compat pies
	@SubscribeEvent(priority = EventPriority.NORMAL)
	void onPieTooltip(ItemTooltipEvent e) {
		if (isCompatPie(e.getItemStack())) e.getToolTip().add(
			Configuration.ENABLE_PUMPKIN_PIE_SNEAK_TO_PLACE.get() ?
			TextUtils.PLACEABLE_SNEAKING :
			TextUtils.PLACEABLE
		);
	}
}
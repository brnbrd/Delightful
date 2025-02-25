package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.common.events.PieEvents;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import squeek.appleskin.api.event.TooltipOverlayEvent;

public class AppleSkinEventHandler {
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPreTooltipEvent(TooltipOverlayEvent.Pre e) {
		e.setCanceled(PieEvents.enabled(e.itemStack));
	}
}
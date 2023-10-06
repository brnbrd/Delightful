package net.brnbrd.delightful.common.events;

import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KnifeEvents {

	// Twilight Forest, Nether's Exoticism, Create Stuff & Additions Compat
	@SubscribeEvent
	void onFireKnife(LivingAttackEvent e) {
		if (e.getSource().getEntity() instanceof LivingEntity living &&
			living.getMainHandItem().is(DelightfulItemTags.FIRE_KNIVES) &&
			!e.getEntity().fireImmune()) {
			e.getEntity().setSecondsOnFire(15);
		}
	}
}
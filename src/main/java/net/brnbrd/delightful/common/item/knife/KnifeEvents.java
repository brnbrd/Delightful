package net.brnbrd.delightful.common.item.knife;

import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KnifeEvents {

	// Twilight Forest Compat (and Nether's Exoticism)
	@SubscribeEvent
	void onFieryToolSetFire(LivingAttackEvent e) {
		if (e.getSource().getEntity() instanceof LivingEntity living &&
			(living.getMainHandItem().is(Knives.FIERY.get()) || living.getMainHandItem().is(Knives.KIWANO.get())) &&
			!e.getEntity().fireImmune()) {
			e.getEntity().setSecondsOnFire(1);
		}
	}

	// Twilight Forest Compat
	@SubscribeEvent
	void onKnightmetalToolDamage(LivingHurtEvent e) {
		LivingEntity target = e.getEntity();
		if (!target.getLevel().isClientSide() && e.getSource().getDirectEntity() instanceof LivingEntity living) {
			ItemStack weapon = living.getMainHandItem();
			if (!weapon.isEmpty()) {
				if (target.getArmorValue() > 0 && weapon.is(Knives.KNIGHTMETAL.get())) {
					if (target.getArmorCoverPercentage() > 0) {
						int moreBonus = (int) (2 * target.getArmorCoverPercentage());
						e.setAmount(e.getAmount() + moreBonus);
					} else {
						e.setAmount(e.getAmount() + 2);
					}
					((ServerLevel) target.getLevel()).getChunkSource().broadcastAndSend(target, new ClientboundAnimatePacket(target, 5));
				}
			}
		}
	}
}

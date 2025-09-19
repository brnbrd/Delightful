package net.brnbrd.delightful.compat.brewinandchewin;

import net.brnbrd.delightful.common.item.food.EnderNectarItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.Fluid;

public class FermentedEnderNectarItem extends DBoozeItem {
	public FermentedEnderNectarItem(Fluid fluid, Properties properties) {
		super(fluid, properties);
	}

	@Override
	public void affectConsumer(LivingEntity consumer, int duration, int potency) {
		super.affectConsumer(consumer, duration, potency);
		if (consumer instanceof ServerPlayer player) EnderNectarItem.openChest(player);
	}
}
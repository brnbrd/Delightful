package net.brnbrd.delightful.compat.brewinandchewin;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.food.EnderNectarItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import vectorwing.farmersdelight.common.Configuration;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FermentedEnderNectarItem extends DBoozeItem {
	public FermentedEnderNectarItem(Fluid fluid, Properties properties) {
		super(fluid, properties);
	}

	@Override
	public void affectConsumer(LivingEntity consumer, int duration, int potency) {
		super.affectConsumer(consumer, duration, potency);
		if (consumer instanceof ServerPlayer player) EnderNectarItem.openChest(player);
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		if (Configuration.ENABLE_FOOD_EFFECT_TOOLTIP.get()) {
			comps.add(Util.fdTooltip(this.toString()).withStyle(ChatFormatting.BLUE));
		}
		super.appendHoverText(stack, level, comps, pIsAdvanced);
	}
}
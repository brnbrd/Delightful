package net.brnbrd.delightful.common.item.knife.compat.lost_aether_content;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.compat.aether.AetherKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PhoenixKnifeItem extends AetherKnifeItem {
	public PhoenixKnifeItem(Properties properties) {
		super(null, DelightfulTiers.PHOENIX, properties, "lost_aether_content");
	}

	@Override
	public @NotNull Component getName(@NotNull ItemStack stack) {
		Component name = super.getName(stack);
		return this.enabled() ? name.copy().withStyle(ChatFormatting.GREEN) : name;
	}
}
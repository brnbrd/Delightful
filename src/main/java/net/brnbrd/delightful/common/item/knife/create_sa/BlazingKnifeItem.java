package net.brnbrd.delightful.common.item.knife.create_sa;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.Tags;

public class BlazingKnifeItem extends CompatKnifeItem {
	public BlazingKnifeItem(Properties properties) {
		super("create_sa", Tags.Items.INGOTS_GOLD, DelightfulTiers.BLAZING, properties, Component.literal("As hot as an authentic blaze!").withStyle(ChatFormatting.DARK_RED), null);
	}

	@Override
	public boolean genRecipe() {
		return false;
	}
}

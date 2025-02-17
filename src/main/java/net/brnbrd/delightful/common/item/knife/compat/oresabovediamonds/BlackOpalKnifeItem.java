package net.brnbrd.delightful.common.item.knife.compat.oresabovediamonds;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BlackOpalKnifeItem extends DKnifeItem {
	public BlackOpalKnifeItem(Properties properties) {
		super(DelightfulItemTags.gem("black_opal"), DelightfulTiers.BLACK_OPAL, properties, Modid.OAD);
	}

	@Override
	public @NotNull Component getName(@NotNull ItemStack stack) {
		Component name = super.getName(stack);
		return this.enabled() ? name.copy().withStyle(ChatFormatting.DARK_PURPLE) : name;
	}
}
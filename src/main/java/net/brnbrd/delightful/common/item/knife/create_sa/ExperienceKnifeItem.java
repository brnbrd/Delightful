package net.brnbrd.delightful.common.item.knife.create_sa;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.data.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;

public class ExperienceKnifeItem extends CompatKnifeItem {
	public ExperienceKnifeItem(Properties properties) {
		super("create_sa", DelightfulItemTags.HEAP_EXPERIENCE, DelightfulTiers.EXPERIENCE, properties,
			Component.literal("This tool gradually crumbles; if you are lucky it will spawn xp orbs").withStyle(ChatFormatting.RED),
			null, ChatFormatting.YELLOW);
	}

	@Override
	public Supplier<Ingredient> getRod() {
		return Util.ing(DelightfulItemTags.ZINC_HANDLE);
	}

	@Override
	public boolean isFoil(@NotNull ItemStack pStack) {
		return true;
	}

	@Override
	public boolean isRepairable(@NotNull ItemStack stack) {
		return false;
	}

	@Override
	public boolean isValidRepairItem(@NotNull ItemStack pToRepair, @NotNull ItemStack pRepair) {
		return false;
	}
}

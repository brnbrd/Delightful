package net.brdle.delightful.common.item.knife.create_sa;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.brdle.delightful.data.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import java.util.Optional;
import java.util.function.Supplier;

public class ExperienceKnifeItem extends CompatKnifeItem {
	public ExperienceKnifeItem(Properties properties) {
		super("create_sa", DelightfulItemTags.HEAP_EXPERIENCE, DelightfulTiers.EXPERIENCE, 0.5F, -2.0F, properties,
			Component.literal("This tool gradually crumbles; if you are lucky it will spawn xp orbs").withStyle(ChatFormatting.RED),
			Optional.empty(), ChatFormatting.YELLOW);
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
	public boolean isRepairable(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
		return false;
	}
}

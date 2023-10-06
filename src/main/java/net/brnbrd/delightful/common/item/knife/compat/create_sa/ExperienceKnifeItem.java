package net.brnbrd.delightful.common.item.knife.compat.create_sa;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class ExperienceKnifeItem extends CompatKnifeItem {
	public ExperienceKnifeItem(Properties properties) {
		super("create_sa", DelightfulItemTags.HEAP_EXPERIENCE, DelightfulTiers.EXPERIENCE, properties, ChatFormatting.YELLOW);
	}

	@Override
	public List<Component> getTools() {
		return List.of(
			Component.literal("This tool gradually crumbles; if you are lucky it will spawn xp orbs").withStyle(ChatFormatting.RED)
		);
	}

	@Override
	public Ingredient getRod() {
		return Ingredient.of(DelightfulItemTags.ZINC_HANDLE);
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

package net.brnbrd.delightful.common.item.knife.compat.create_sa;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class ExperienceKnifeItem extends CompatKnifeItem {
	public ExperienceKnifeItem(Properties properties) {
		super("create_sa", DelightfulItemTags.HEAP_EXPERIENCE, DelightfulTiers.EXPERIENCE, properties, ChatFormatting.YELLOW);
	}

	// Occasionally spawn experience orbs from breaking blocks
	@Override
	public boolean mineBlock(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockPos pPos, @NotNull LivingEntity pEntityLiving) {
		boolean sup = super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
		if (sup && pLevel instanceof ServerLevel server && server.getRandom().nextInt(15) == 0) {
			ExperienceOrb.award(server, pPos.getCenter(), 1);
		}
		return sup;
	}

	@Override
	public List<Component> getTools() {
		return List.of(
			Component.literal("This tool gradually crumbles;").withStyle(ChatFormatting.DARK_PURPLE),
			Component.literal("if you are lucky it will spawn xp orbs").withStyle(ChatFormatting.DARK_PURPLE)
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

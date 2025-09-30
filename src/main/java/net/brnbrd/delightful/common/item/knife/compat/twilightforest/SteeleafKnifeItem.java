package net.brnbrd.delightful.common.item.knife.compat.twilightforest;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SteeleafKnifeItem extends DKnifeItem {
	public SteeleafKnifeItem(Properties properties) {
		super(DelightfulItemTags.ingot("steeleaf"), DelightfulTiers.STEELEAF, properties, Modid.TF);
	}

	@Override
	public @NotNull Component getName(@NotNull ItemStack stack) {
		Component name = super.getName(stack);
		return this.enabled() ? name.copy().withStyle(ChatFormatting.AQUA) : name;
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.TFD, Modid.DUNG};
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return null;
	}

	@Override
	public @NotNull ItemStack getCreativeItem() {
		return Util.enchant(super.getCreativeItem(), Enchantments.SMITE, 2);
	}
}
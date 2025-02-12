package net.brnbrd.delightful.common.item.knife.compat.twilightforest;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModEnchantments;

public class IronwoodKnifeItem extends DKnifeItem {
	public IronwoodKnifeItem(Item.Properties properties) {
		super(DelightfulItemTags.ingot("ironwood"), DelightfulTiers.IRONWOOD, properties, Mods.TF);
	}

	@Override
	public @NotNull Component getName(@NotNull ItemStack stack) {
		Component name = super.getName(stack);
		return this.enabled() ? name.copy().withStyle(ChatFormatting.AQUA) : name;
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return null;
	}

	@Override
	public String[] getConflicts() {
		return new String[]{Mods.TFD};
	}

	@Override
	public @NotNull ItemStack getCreativeItem() {
		return Util.enchant(super.getCreativeItem(), ModEnchantments.BACKSTABBING.get(), 1);
	}
}
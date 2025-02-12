package net.brnbrd.delightful.common.item.knife.compat.oresabovediamonds;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NetheriteOpalKnifeItem extends DKnifeItem {
	public NetheriteOpalKnifeItem(Properties properties) {
		super(Tags.Items.INGOTS_NETHERITE, DelightfulTiers.NETHERITE_OPAL, properties, "oresabovediamonds");
	}

	@Override
	public @NotNull Component getName(@NotNull ItemStack stack) {
		Component name = super.getName(stack);
		return this.enabled() ? name.copy().withStyle(ChatFormatting.DARK_PURPLE) : name;
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return RecipeType.SMITHING;
	}
}
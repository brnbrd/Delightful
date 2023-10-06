package net.brnbrd.delightful.common.item.knife.compat.create_sa;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class BlazingKnifeItem extends CompatKnifeItem {
	public BlazingKnifeItem(Properties properties) {
		super("create_sa", Tags.Items.INGOTS_GOLD, DelightfulTiers.BLAZING, properties);
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return null;
	}

	@Override
	public List<Component> getTools() {
		return List.of(
			Component.literal("As hot as an authentic blaze!").withStyle(ChatFormatting.DARK_RED)
		);
	}
}

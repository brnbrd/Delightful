package net.brnbrd.delightful.common.item.knife.compat.create_sa;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class BlazingKnifeItem extends CompatKnifeItem {
	public BlazingKnifeItem(Properties properties) {
		super("create_sa", Tags.Items.INGOTS_GOLD, DelightfulTiers.BLAZING, properties);
	}

	// Tool takes no damage in Nether
	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		if (entity.level().dimension() == Level.NETHER) {
			return 0;
		}
		return super.damageItem(stack, amount, entity, onBroken);
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return null;
	}

	@Override
	public List<Component> getTools() {
		return List.of(
			Component.literal("As hot as an authentic blaze!").withStyle(ChatFormatting.DARK_PURPLE),
			Component.literal("Burns the mob the tool hits").withStyle(ChatFormatting.DARK_PURPLE)
		);
	}
}

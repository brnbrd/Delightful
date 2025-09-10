package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.common.item.IConfigured;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DrinkItem extends DrinkableItem implements IConfigured {
	public DrinkItem(Item.Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
		super(properties, hasPotionEffectTooltip, hasCustomTooltip);
	}

	@Override
	public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player player, @NotNull InteractionHand hand) {
		return ItemUtils.startUsingInstantly(world, player, hand);
	}

	public float getHeal() {
		return 0F;
	}

	@Override
	public void affectConsumer(@NotNull ItemStack stack, @NotNull Level worldIn, @NotNull LivingEntity consumer) {
		float heal = this.getHeal();
		if (heal > 0F) consumer.heal(heal);
		super.affectConsumer(stack, worldIn, consumer);
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		this.enabledText(comps);
		super.appendHoverText(stack, level, comps, pIsAdvanced);
	}
}
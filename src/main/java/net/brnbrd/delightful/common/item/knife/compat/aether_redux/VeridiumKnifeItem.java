package net.brnbrd.delightful.common.item.knife.compat.aether_redux;

import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.common.item.knife.compat.aether.AetherKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.zepalesque.redux.item.ReduxItems;
import net.zepalesque.redux.item.util.VeridiumItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class VeridiumKnifeItem extends AetherKnifeItem implements VeridiumItem {
	public VeridiumKnifeItem(Tier tier, Properties properties) {
		super("aether_redux", DelightfulItems.ingot("veridium"), tier, properties);
	}

	public boolean isCharged(Item item) {
		return item == Knives.INFUSED_VERIDIUM.get();
	}

	@Override
	public @NotNull ItemStack getDefaultInstance() {
		ItemStack stack = super.getDefaultInstance().copy();
		return isCharged(this) ?
			VeridiumItem.infuse(stack, VeridiumItem.MAXIUMUM_VERIDIUM_INFUSION)
			: stack;
	}

	@Override
	public Item getReplacementItem(ItemStack itemStack) {
		return this.isCharged(itemStack.getItem()) ? Knives.VERIDIUM.get() : Knives.INFUSED_VERIDIUM.get();
	}

	public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		if (this.enabled()) {
			MutableComponent component = Component.translatable("tooltip.aether_redux.ambrosium_charge",
				VeridiumItem.getInfusion(pStack)).withStyle(ChatFormatting.GRAY);
			comps.add(component);
			Component c = ReduxItems.TooltipUtils.SHIFT_OR_DEFAULT.apply(Component.translatable("gui.aether_redux.infusion_tooltip"));
			comps.add(c);
		}
		super.appendHoverText(pStack, pLevel, comps, pIsAdvanced);
	}

	public boolean hurtEnemy(@NotNull ItemStack pStack, @NotNull LivingEntity pTarget, @NotNull LivingEntity pAttacker) {
		if (this.isCharged(pStack.getItem()) && !pAttacker.level().isClientSide() && (!(pAttacker instanceof Player) || !((Player)pAttacker).getAbilities().instabuild)) {
			ItemStack stackReplacement = VeridiumItem.depleteInfusion(pStack, pAttacker);
			if (!stackReplacement.getItem().equals(pStack.getItem())) {
				pAttacker.setItemSlot(EquipmentSlot.MAINHAND, stackReplacement);
			} else {
				pStack.setTag(stackReplacement.getTag());
			}
		}
		return super.hurtEnemy(pStack, pTarget, pAttacker);
	}

	public boolean mineBlock(@NotNull ItemStack pStack, @NotNull Level pLevel, BlockState pState, @NotNull BlockPos pPos, @NotNull LivingEntity pEntityLiving) {
		if (pState.getDestroySpeed(pLevel, pPos) != 0.0F && this.isCharged(pStack.getItem()) && !pEntityLiving.level().isClientSide()) {
			if (pEntityLiving instanceof Player p) {
				if (p.getAbilities().instabuild) {
					return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
				}
			}
			ItemStack stackReplacement = VeridiumItem.depleteInfusion(pStack, pEntityLiving);
			if (!stackReplacement.getItem().equals(pStack.getItem())) {
				pEntityLiving.setItemSlot(EquipmentSlot.MAINHAND, stackReplacement);
			} else {
				pStack.setTag(stackReplacement.getTag());
			}
		}
		return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
	}

	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		return this.isCharged(stack.getItem()) ? amount * this.CHARGED_DAMAGE_MULTIPLIER : amount;
	}
}

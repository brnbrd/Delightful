package net.brnbrd.delightful.common.item.knife.compat.aether.aether_redux;

import net.brnbrd.delightful.common.item.knife.compat.aether.AetherKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.zepalesque.redux.item.tools.VeridiumItem;
import net.zepalesque.redux.item.util.TooltipUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class VeridiumKnifeItem extends AetherKnifeItem implements VeridiumItem {
	private final Supplier<? extends Item> uninfused;

	public VeridiumKnifeItem(Tier tier, Properties properties, Supplier<? extends Item> uninfused) {
		super(DelightfulItemTags.ingot("veridium"), tier, properties, Modid.AER);
		this.uninfused = uninfused;
	}

	@Override
	public Item getUninfusedItem(ItemStack stack) {
		return this.uninfused.get();
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag advanced) {
		MutableComponent infusion = Component.translatable("tooltip.aether_redux.infusion_charge", stack.getTag() == null ? 0 : stack.getTag().getByte(VeridiumItem.NBT_KEY)).withStyle(ChatFormatting.GRAY);
		
		tooltips.add(infusion);
		Component info = TooltipUtils.shiftForInfo(HOVER_TOOLTIP);
		tooltips.add(info);
		super.appendHoverText(stack, level, tooltips, advanced);
	}

	@Override
	public boolean mineBlock(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity user) {
		// Call the vanilla method do do things like tool damaging
		boolean bool = super.mineBlock(stack, level, state, pos, user);
		if (!user.level().isClientSide()) {
			boolean instaBreak = state.getDestroySpeed(level, pos) <= 0F;
			// Avoid decreasing infusion on insta-break blocks
			if (!instaBreak) {
				int amount = stack.isCorrectToolForDrops(state) ? 1 : 2;
				ItemStack transform = this.deplete(stack, user, amount);
				if (!user.level().isClientSide() && transform != null && transform != stack) {
					user.setItemSlot(EquipmentSlot.MAINHAND, transform);
					if (user instanceof ServerPlayer sp) {
						this.sendSound(sp);
					}
				}
			}
		}
		return bool;
	}

	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		return super.damageItem(stack, amount, entity, onBroken) * VeridiumItem.DURABILITY_DMG_MULTIPLIER;
	}

	public static class Uninfused extends AetherKnifeItem {
		public Uninfused(Tier tier, Properties properties) {
			super(DelightfulItemTags.ingot("veridium"), tier, properties, Modid.AER);
		}

		@Override
		public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltips, @NotNull TooltipFlag advanced) {
			Component info = TooltipUtils.shiftForInfo(HOVER_TOOLTIP);
			tooltips.add(info);
			super.appendHoverText(stack, level, tooltips, advanced);
		}
	}
}
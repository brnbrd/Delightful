package net.brnbrd.delightful.common.mixin;

// Code adapted from: https://github.com/vectorwing/FarmersDelight/blob/1.20/src/main/java/vectorwing/farmersdelight/common/mixin/PlacePumpkinPieMixin.java

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.events.PieEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.Configuration;
import javax.annotation.Nullable;

@Mixin(Item.class)
public class PlacePieMixin {
	@Inject(method = "useOn", at = @At("TAIL"), cancellable = true)
	private void usePie(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
		final ItemStack stack = context.getItemInHand();
		if (PieEvents.isCompatPie(stack)) {
			final @Nullable BlockItem pieBlock = PieEvents.getPieBlockItem(Util.name(stack));
			final Player player = context.getPlayer();
			if (pieBlock != null && player != null && (
				!Configuration.ENABLE_PUMPKIN_PIE_SNEAK_TO_PLACE.get() || player.isSecondaryUseActive()
			)) {
				cir.setReturnValue(pieBlock.useOn(context));
			}
		}
	}
}
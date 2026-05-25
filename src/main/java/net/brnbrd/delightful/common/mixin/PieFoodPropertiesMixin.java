package net.brnbrd.delightful.common.mixin;

import net.brnbrd.delightful.common.DelightfulConfig;
import net.brnbrd.delightful.common.events.PieEvents;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.tag.ModTags;

@Mixin(Item.class)
public abstract class PieFoodPropertiesMixin {
	@Shadow
	public abstract ItemStack getDefaultInstance();

	@Inject(method = "getFoodProperties", at = @At("TAIL"), cancellable = true)
	private void foodProperties(CallbackInfoReturnable<FoodProperties> cir) {
		final ItemStack stack = getDefaultInstance();
		if (
			stack.is(ModTags.Items.PIES) &&
			DelightfulConfig.PIE_EDIBLE_MIXIN.get() &&
			(!stack.is(DelightfulItemTags.COMPAT_PIES) || PieEvents.isCompatPie(stack))
		) {
			cir.setReturnValue(null);
		}
	}
}